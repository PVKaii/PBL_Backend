package com.example.pbl_api.config;

import com.example.pbl_api.Data.JsonDetailObject;
import com.example.pbl_api.Data.JsonObject;
import com.example.pbl_api.entity.*;
import com.example.pbl_api.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DataInitializer  implements ApplicationRunner {
    private static final int productAmount=10;

    Set<Attributes> setAtt = new HashSet<>();

    Set<Brand> setBr = new HashSet<>();

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AttributesRepository attributesRepository;

    @Autowired
    OptionGroupRepository optionGroupRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    BrandRepository brandRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadInitData();
        if(productRepository.findAll().spliterator().getExactSizeIfKnown()==0){
            String path= System.getProperty("user.dir");
            String filePath =path+ "/src/main/java/com/example/pbl_api/Data/data.json";
            JsonObject[] data=  readFileJson(filePath);
            loadData(data);
        }

    }


    JsonObject[] readFileJson(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper .findAndRegisterModules();
        JsonObject[] data = objectMapper.readValue(new File(path), JsonObject[].class);
        return data;
    }

    void loadData(JsonObject[] data){
        for (JsonObject object:
                data) {
//            if(object.getName_product_type().equals("Laptop")){
//                for (int i = 0; i < productAmount; i++) {
//                    JsonDetailObject product = object.getProducts().get(i);
//                    addProduct(product);
//                }
//            }
            String categoryName=object.getName_product_type();
            if(categoryName.equals("Máy ảnh - Máy quay phim")
            ||categoryName.equals("Thiết bị văn phòng")||categoryName.equals("Điện máy - Điện gia dụng")
                    ||categoryName.equals("Điện thoại & phụ kiện")||categoryName.equals("Thiết bị âm thanh")
                    ||categoryName.equals("Thiết bị thông minh")
                    ||categoryName.equals("PC - Phụ kiện")
                    ||categoryName.equals("PC - Màn hình máy tính")
            ) continue;
            int size = object.getProducts().size()<productAmount?object.getProducts().size():productAmount;
            Category category = categoryRepository.save(new Category(object.getName_product_type()));
            for (int i = 0; i < size; i++) {
                    JsonDetailObject product = object.getProducts().get(i);
                    addProduct(product,category);
                }
            setAtt.clear();
            System.out.println("Init data");
        }
        System.out.println("Init data done");
    }

    void addProduct(JsonDetailObject productJson,Category category){
        List<Attributes> attributesList = new ArrayList<>();
        Brand brand=null ;
        for (List<String> productAttributesJson:
            productJson.getDescription_detail() ) {
            if(productAttributesJson.get(0).equals("Thương hiệu")){
                List<Brand> oldBrand = setBr.stream().filter(brand1 -> brand1.getName()
                                .equals(productAttributesJson.get(1)))
                        .collect(Collectors.toList());
                if(oldBrand.size()!=0){
                    brand=oldBrand.get(0);
                }
                else{
                    brand = brandRepository.save(new Brand(productAttributesJson.get(1)));
                    setBr.add(brand);
                }
                continue;
            }

            List<Attributes> oldAttributes = setAtt.stream()
                    .filter(attributes -> attributes.getOptionGroup().getName().equals(productAttributesJson.get(0)))
                    .filter(attributes -> attributes.getName().equals(productAttributesJson.get(1)))
                    .collect(Collectors.toList());
            if(oldAttributes.size()!=0){
                attributesList.add(oldAttributes.get(0));
            }
            else{

                Attributes attribute;
                List<Attributes> oldOp = setAtt.stream()
                            .filter(attributes ->attributes.getOptionGroup().getName().equals(productAttributesJson.get(0)))
                            .collect(Collectors.toList());
                    if(oldOp.size()!=0){
                        attribute = new Attributes(
                                productAttributesJson.get(1),
                                null
                        );
                        attribute.setOptionGroup(oldOp.get(0).getOptionGroup());
                    }
                    else{
                        OptionGroup optionGroup = optionGroupRepository.save(
                                new OptionGroup(productAttributesJson.get(0),
                                        category
                                )
                        );
                        attribute = new Attributes(
                                productAttributesJson.get(1),
                                optionGroup
                        );
                    }

            attributesList.add(attribute);
            setAtt.addAll(attributesList);
            }

        }

        Set<Attributes> attributes= ((ArrayList<Attributes>) attributesRepository.saveAll(attributesList)).stream().collect(Collectors.toSet());

//        Set<ProductImage> images = ((ArrayList<ProductImage>) productImageRepository.saveAll(productJson
//                .getImage().stream().map(s -> new ProductImage(s))
//                .collect(Collectors.toSet()))).stream().collect(Collectors.toSet());
        Product product = new Product(
                productJson.getName(),
                Long.parseLong(productJson.getPrice().replace(".", "").replace("₫", "")),
                productJson.getDescription(),
                attributes,
                brand,
                category,
                null
        );


        try{
            Product rs= productRepository.save(product);
            productImageRepository.saveAll(productJson
                    .getImage().stream().map(s -> new ProductImage(s,new Product(rs.getId())))
                    .collect(Collectors.toSet()));
        }
        catch (Exception e){

        }

    }

        void loadInitData(){
        if(roleRepository.findAll().spliterator().getExactSizeIfKnown()==0){
            roleRepository.save(new Role("ROLE_ADMIN"));
            roleRepository.save(new Role("ROLE_MEMBER"));
        }

        if(orderStatusRepository.findAll().spliterator().getExactSizeIfKnown()==0){
            orderStatusRepository.save(new OrderStatus("Chưa xác nhận"));
            orderStatusRepository.save(new OrderStatus("Đã xác nhận"));
            orderStatusRepository.save(new OrderStatus("Đã từ chối"));


        }




        if(userRepository.findAll().spliterator().getExactSizeIfKnown()==0){
            userRepository.save(
                    new User(
                            "admin",
                            LocalDate.now(),
                            "DN",
                            "123456789",
                            true,
                            "lvkn.pbl6@gmail.com",
                            new UserAccount("admin",
                                    encoder.encode("123456"),true,true,
                                    new ArrayList<>(){
                                        {
                                            add(new Role(1));
                                            add(new Role(2));
                                        }
                                    }
                            )
                    )
            );

            userRepository.save(
                    new User(
                            "member",
                            LocalDate.now(),
                            "DN",
                            "123456789",
                            true,
                            "taratla123@gmail.com",
                            new UserAccount("member",
                                    encoder.encode("123456"),true,true,
                                    new ArrayList<>(){
                                        {
                                            add(new Role(2));
                                        }
                                    }
                            )
                    )
            );
        }
    }
}
