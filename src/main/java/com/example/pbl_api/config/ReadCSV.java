package com.example.pbl_api.config;

import com.example.pbl_api.entity.*;
import com.example.pbl_api.repository.*;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

enum DataKey {
    Name("name"),
    Price("price"),
    Img("image"),
    Description("description"),
    AttributeName("description_detail__001"),
    AttributeValue("description_detail__002");
    private String value;

    private DataKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


//@Component
public class ReadCSV implements ApplicationRunner {

    static final String LAPTOP ="Laptop";

    @Autowired
    BrandRepository brandRepository;


    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    OptionGroupRepository optionGroupRepository;

    @Autowired
    AttributesRepository attributesRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductImageRepository productImageRepository;

    List<OptionGroup> optionGroupList=new ArrayList<>();
    List<Attributes> attributesList = new ArrayList<>();
    List<Product> productList = new ArrayList<>();
    List<ProductImage> productImageList = new ArrayList<>();


    @Override
    public void run(ApplicationArguments args) throws Exception {



        loadInitData();
        loadCategory();
        loadLaptopData();
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
                            "PVK",
                            null,
                            "DN",
                            "123456789",
                            true,
                            new UserAccount("admin@gmail.com",
                                    encoder.encode("123456"),
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
                            "PVK2",
                            null,
                            "DN",
                            "123456789",
                            true,
                            new UserAccount("member@gmail.com",
                                    "$2a$10$.amUH9PJ311zU8tHHdqYoOFp9dZm0suVxxIiGVwSi.ky9OeVvRpdK",
                                    new ArrayList<>(){
                                        {
                                            add(new Role(1));
                                        }
                                    }
                            )
                    )
            );
        }
    }


    void loadLaptopData() throws Exception {
        String path= System.getProperty("user.dir");
        System.out.println(path);
        String fileName =path+ "/src/main/java/com/example/pbl_api/Data/laptop.csv";
        List<String[]> r = getDataFromFileCsv(fileName);
        loadData(r,LAPTOP);
    }

    void loadCategory(){
        if(categoryRepository.findAll().spliterator().getExactSizeIfKnown()==0){
            categoryRepository.save(
                    new Category("Laptop"));
            categoryRepository.save(
                    new Category("PC"));
            categoryRepository.save(
                    new Category("CPU"));
            categoryRepository.save(
                    new Category("Ổ cứng"));
            categoryRepository.save(
                    new Category("VGA"));
            categoryRepository.save(
                    new Category("Ram"));
            categoryRepository.save(
                    new Category("Mainboard"));
            categoryRepository.save(
                    new Category("Phụ kiện"));
            categoryRepository.save(
                    new Category("Bàn phím"));
            categoryRepository.save(
                    new Category("Chuột"));

        }
    }

    private void loadData(List<String[]> r,String category){
        String[] labels = r.get(0);
        List<HashMap<String,Object>> mapDataList = new ArrayList<>();
        HashMap<String , Object> mapData = new HashMap<>();
        for (int i =1 ; i<30;i++){
            String[] dataList = r.get(i);
            if(dataList[0].isEmpty()==false){
                if(mapData.size()>0) mapDataList.add(mapData);
                mapData = new HashMap<>();
            }
//            System.out.println(Arrays.toString(dataList));
            for(int j=0;j<labels.length;j++){
                if(dataList[j].isEmpty()) continue;
                String label = labels[j];
                String data = dataList[j];
                mapDataToHashMap(label,data,mapData);
//                            System.out.println(mapData.get(DataKey.Img.getValue()));

            }
//            System.out.println(mapData.toString());
        }
            insertMapDataToDatabase(mapDataList,category);
        System.out.println("Initial database done");

    }


    void insertMapDataToDatabase(List<HashMap<String,Object>> mapDataList,String category){
        mapDataList.forEach(data ->{
            System.out.println("Initial database");
            String name =(String) data.values().stream().collect(Collectors.toList()).get(1);
            List<OptionGroup> optionGroupList= insertOptionGroup((List<String>) data.get(DataKey.AttributeName.getValue()),category);
            List<Object> rs= inserAttributes((List<String>) data.get(DataKey.AttributeValue.getValue()),optionGroupList,category);
            List<Attributes> attributesList = (List<Attributes>) rs.get(1);
            Brand brand =(Brand) rs.get(0);
            insertProduct(
                    name,
                    (String) data.get(DataKey.Price.getValue()),
                    (String) data.get(DataKey.Description.getValue()),
                    attributesList,
                    brand,
                    categoryRepository.findCategoryByName(category),
                    (List<String>) data.get(DataKey.Img.getValue())
                    );
        });
    }

    void insertProductImgs(List<String> listImg,Product product){
        listImg.forEach(data->{
            if(productImageRepository.findProductImageByUrlAndProductName(data,product.getName())==null){
                productImageRepository.save(new ProductImage(data,product));

            }
        });
    }
    void insertProduct(String name ,String price , String description
            , List<Attributes> attributesList,Brand brand,Category category,List<String> imgs){
        Set<Attributes> attributesSet = attributesList.stream().collect(Collectors.toSet());
        Product product = new Product(name,Long.parseLong(price.replace(".","").replace("₫","")),description
                ,attributesSet,brand,category);
        try{
            productRepository.save(product);
            insertProductImgs(imgs,product);
        }
        catch(Exception e){

        }
    }

    List<OptionGroup> insertOptionGroup(List<String> optionGroupSet,String category){
        List<OptionGroup> rs = new ArrayList<>();
        optionGroupSet.forEach(data ->{
                OptionGroup optionGroup =optionGroupRepository.findOptionGroupByNameAndCategoryName(data,category);
                if(optionGroup==null){
                    optionGroup = new OptionGroup(data,categoryRepository.findCategoryByName(category));
                    if(data.equals("Thương hiệu")==false) optionGroupRepository.save(optionGroup);
                }
                rs.add(optionGroup);

        });
        return rs;
    }

    List<Object> inserAttributes(List<String> attributesSet ,List<OptionGroup> optionGroupList,String category ){
        List<Attributes> listAttributes = new ArrayList<>();
        List<Object> rs = new ArrayList<>();
        for(int i =0;i<attributesSet.size();i++){
            if(optionGroupList.get(i).getName().equals("Thương hiệu")==true){
                Brand brand = brandRepository.findBrandByName(attributesSet.get(i));
                if(brand==null){
                    brand = new Brand(attributesSet.get(i));
                    brandRepository.save(brand);
                }
                rs.add(brand);
                continue;

            }
            String optionName = optionGroupList.get(i).getName();
            String attributeName = attributesSet.get(i);
            Attributes attributes = attributesRepository.findAttributesByNameAndOptionGroupNameAndCategoryName(
                    attributeName,optionName,category
            );
            if(attributes==null){
                attributes= new Attributes(attributeName,optionGroupList.get(i));
                attributesRepository.save(attributes);
            }
            listAttributes.add(attributes);
        }
        rs.add(listAttributes);
        return rs;
    }

    void mapDataToHashMap(String label,String data,HashMap<String , Object> mapData){
        if(label.contains(DataKey.Img.getValue())){
            if(mapData.get(DataKey.Img.getValue())==null){
                List<String> listImg = new ArrayList<>();
                mapData.put(DataKey.Img.getValue(), listImg);
            }
            ((List<String>) mapData.get(DataKey.Img.getValue())).add(data);
        }
        else if(label.equals(DataKey.AttributeName.getValue())||label.equals(DataKey.AttributeValue.getValue())){
            if(mapData.get(label)==null){
                List<String> list = new ArrayList<>();
                mapData.put(label,list);
            }
            ((List<String>) mapData.get(label)).add(data);
        }
        else {
            mapData.put(label,data);
        }
    }



    List<String[]> getDataFromFileCsv(String path) throws Exception{
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            List<String[]> r = reader.readAll();
            return  r;
        }
    }
}
