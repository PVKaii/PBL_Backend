package com.example.pbl_api.config;

import com.example.pbl_api.entity.*;
import com.example.pbl_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class DbInitializer implements ApplicationRunner {
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

    @Override
    public void run(ApplicationArguments args) throws Exception {


        if(roleRepository.findAll().spliterator().getExactSizeIfKnown()==0){
            roleRepository.save(new Role("ROLE_ADMIN"));
            roleRepository.save(new Role("ROLE_MEMBER"));
        }


//        if(userRepository.findAll().spliterator().getExactSizeIfKnown()==0){
//            userRepository.save(
//                    new User(
//                            "PVK",
//                            null,
//                            "DN",
//                            "123456789",
//                            true,
//                            new UserAccount("admin@gmail.com",
//                                    encoder.encode("123456"),
//                            new ArrayList<>(){
//                                {
//                                    add(new Role(1));
//                                    add(new Role(2));
//                                }
//                            }
//                            )
//                    )
//            );
//
//            userRepository.save(
//                    new User(
//                            "PVK2",
//                            null,
//                            "DN",
//                            "123456789",
//                            true,
//                            new UserAccount("member@gmail.com",
//                                    "$2a$10$.amUH9PJ311zU8tHHdqYoOFp9dZm0suVxxIiGVwSi.ky9OeVvRpdK",
//                                    new ArrayList<>(){
//                                        {
//                                            add(new Role(1));
//                                        }
//                                    }
//                            )
//                    )
//            );
//        }

        if(categoryRepository.findAll().spliterator().getExactSizeIfKnown()==0){
            categoryRepository.save(
                    new Category("Laptop"));
//            categoryRepository.save(
//                    new Category("Laptop theo thương hiệu",sellerCategoryRepository.findSellerCategoryByName("Laptop")));
//            categoryRepository.save(
//                    new Category("Laptop theo cấu hình chip",sellerCategoryRepository.findSellerCategoryByName("Laptop")));
//            categoryRepository.save(
//                    new Category("Laptop theo nhu cầu",sellerCategoryRepository.findSellerCategoryByName("Laptop")));
//            categoryRepository.save(
//                    new Category("Laptop theo giá",sellerCategoryRepository.findSellerCategoryByName("Laptop")));


            categoryRepository.save(
                    new Category("PC"));
//            categoryRepository.save(
//                    new Category("PC theo thương hiệu",sellerCategoryRepository.findSellerCategoryByName("PC")));
//            categoryRepository.save(
//                    new Category("PC theo nhu cầu",sellerCategoryRepository.findSellerCategoryByName("PC")));
//            categoryRepository.save(
//                    new Category("PC theo giá",sellerCategoryRepository.findSellerCategoryByName("PC")));

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

        if(optionGroupRepository.findAll().spliterator().getExactSizeIfKnown()==0){
            optionGroupRepository.save(
                    new OptionGroup(
                            "Tên thương hiệu",
                            categoryRepository.findCategoryByName("Laptop")));
            optionGroupRepository.save(
                    new OptionGroup(
                            "Màu sắc",
                            categoryRepository.findCategoryByName("Laptop")));
            optionGroupRepository.save(
                    new OptionGroup(
                            "Series CPU",
                            categoryRepository.findCategoryByName("Laptop")));
            optionGroupRepository.save(
                    new OptionGroup(
                            "Chip đồ họa rời",
                            categoryRepository.findCategoryByName("Laptop")));
            optionGroupRepository.save(
                    new OptionGroup(
                            "Dung lượng ram",
                            categoryRepository.findCategoryByName("Laptop")));

            optionGroupRepository.save(
                    new OptionGroup(
                            "Nhu cầu tiêu dùng",
                            categoryRepository.findCategoryByName("Laptop")));

            optionGroupRepository.save(
                    new OptionGroup(
                            "Giá",
                            categoryRepository.findCategoryByName("Laptop")));




            optionGroupRepository.save(
                    new OptionGroup(
                            "Tên thương hiệu",
                            categoryRepository.findCategoryByName("PC")));
            optionGroupRepository.save(
                    new OptionGroup(
                            "Series CPU",
                            categoryRepository.findCategoryByName("PC")));
            optionGroupRepository.save(
                    new OptionGroup(
                            "Chip đồ họa rời",
                            categoryRepository.findCategoryByName("PC")));
            optionGroupRepository.save(
                    new OptionGroup(
                            "Dung lượng ram",
                            categoryRepository.findCategoryByName("PC")));
            optionGroupRepository.save(
                    new OptionGroup(
                            "Dung lượng SSD",
                            categoryRepository.findCategoryByName("PC")));
            optionGroupRepository.save(
                    new OptionGroup(
                            "Dung lương HDD",
                            categoryRepository.findCategoryByName("PC")));

            optionGroupRepository.save(
                    new OptionGroup(
                            "Nhu cầu tiêu dùng",
                            categoryRepository.findCategoryByName("PC")));

            optionGroupRepository.save(
                    new OptionGroup(
                            "Giá",
                            categoryRepository.findCategoryByName("PC")));


            Map<String , String[]> map3 = new HashMap<>();
            String[] map3_1={"Thương hiệu", "Nhà sản xuất","Series CPU","Socket"};
            map3.put("CPU",map3_1);
            String[] map3_2={"Thương hiệu", "Kiểu ổ cứng","Dung lượng","Chuẩn kết nối"};
            map3.put("Ổ cứng",map3_2);
            String[] map3_3={"Thương hiệu", "Nhà sản xuất","Series chip đồ họa","Tên chip độ họa" , "Dung lượng bộ nhớ Vram"};
            map3.put("VGA",map3_3);
            String[] map3_4={"Thương hiệu","Series Mainboard", "Chipset","Socket"};
            map3.put("Mainboard",map3_4);
            String[] map3_5={"Thương hiệu", "Loại Ram","Dung lượng Ram","Thế hệ bộ nhớ"};
            map3.put("Ram",map3_5);

            for(Map.Entry<String, String[]> entry : map3.entrySet()) {
                String key = entry.getKey();
                String[] value = entry.getValue();
                for (int i = 0; i < value.length; i++) {
                    optionGroupRepository.save(new OptionGroup(value[i],categoryRepository.findCategoryByName(key) ));
                }
            }

            String[] map4_1={"Thương hiệu", "Màu sắc","Loại","Đèn"};
            for (int i = 0; i < map4_1.length; i++) {
                optionGroupRepository.save(new OptionGroup(map4_1[i],categoryRepository.findCategoryByName("Phụ kiện") ));
            }
        }


        if(attributesRepository.findAll().spliterator().getExactSizeIfKnown()==0){
            Map<String , String[]> map1 = new HashMap<>();
            String[] map1_1={"ACER","APPLE","ASUS","Dell","GIGABYTE","HP"};
            map1.put("Tên thương hiệu",map1_1);
            String[] map1_2={"Bạc","Hồng","Trắng","Vàng","Xanh","Vàng đồng"};
            map1.put("Màu sắc",map1_2);
            String[] map1_3={"Core i3","Core i5","Core i7","Core i9","Ryzen 3","Ryzen 5","Ryzen 7","Ryzen 9","Celeron","Pentium"};
            map1.put("Series CPU",map1_3);
            String[] map1_4={};
            map1.put("Chip đồ họa rời",map1_4);
            String[] map1_5={"16GB","32GB","4GB","64GB","8GB"};
            map1.put("Dung lượng ram",map1_5);

            OptionGroup b= optionGroupRepository.findOptionGroupByNameAndCategoryName("Màu sắc","Laptop");
            System.out.println(b.getId());
            for(Map.Entry<String, String[]> entry : map1.entrySet()) {
                String key = entry.getKey();
                String[] value = entry.getValue();
                for (int i = 0; i < value.length; i++) {
                    attributesRepository.save(new Attributes(value[i],optionGroupRepository.findOptionGroupByNameAndCategoryName(key,"Laptop")));
                }
            }

            String[] map1_6={"Văn Phòng","Gaming"};
            for (int i = 0; i < map1_6.length; i++) {
                attributesRepository.save(new Attributes(map1_6[i],optionGroupRepository.findOptionGroupByNameAndCategoryName("Nhu cầu tiêu dùng","Laptop")));
            }
            String[] map1_7={"dưới 1 triệu","1-5 triệu","trên 5 triệu"};
            for (int i = 0; i < map1_7.length; i++) {
                attributesRepository.save(new Attributes(map1_7[i],optionGroupRepository.findOptionGroupByNameAndCategoryName("Giá","Laptop")));
            }




            Map<String , String[]> map2 = new HashMap<>();
            String[] map2_1={"ACER","APPLE","ASUS","Dell","GIGABYTE","HP"};
            map2.put("Tên thương hiệu",map2_1);
            String[] map2_2={"Core i3","Core i5","Core i7","Core i9","Ryzen 3","Ryzen 5","Ryzen 7","Ryzen 9","Celeron","Pentium"};
            map2.put("Series CPU",map2_2);
            String[] map2_3={"16GB","32GB","4GB","64GB","8GB"};
            map2.put("Dung lượng ram",map2_3);
            String[] map2_4={"120GB","128GB","16GB","240GB","250GB","256GB","500GB","512GB"};
            map2.put("Dung lượng SSD",map2_4);
            String[] map2_5={"1TB","500GB"};
            map2.put("Dung lượng HDD",map2_5);

            for(Map.Entry<String, String[]> entry : map2.entrySet()) {
                String key = entry.getKey();
                String[] value = entry.getValue();
                for (int i = 0; i < value.length; i++) {
                    attributesRepository.save(new Attributes(value[i],optionGroupRepository.findOptionGroupByNameAndCategoryName(key,"PC")));
                }
            }

            String[] map2_6={"Văn Phòng","Gaming"};
            for (int i = 0; i < map2_6.length; i++) {
                attributesRepository.save(new Attributes(map2_6[i],optionGroupRepository.findOptionGroupByNameAndCategoryName("Nhu cầu tiêu dùng","PC")));
            }
            String[] map2_7={"dưới 1 triệu","1-5 triệu","trên 5 triệu"};
            for (int i = 0; i < map2_7.length; i++) {
                attributesRepository.save(new Attributes(map2_7[i],optionGroupRepository.findOptionGroupByNameAndCategoryName("Giá","PC")));
            }


            Map<String , String[]> map3 = new HashMap<>();
            String[] map3_1={"A4 TECH","AKKO","AOC","ASUS","DAREU","CORSAIR"};
            map3.put("Thương hiệu",map3_1);
            String[] map3_2={"Hồng","Trắng","ĐEN"};
            map3.put("Màu sắc",map3_2);
            String[] map3_3={"Chuột có dây","Chuột không dây"};
            map3.put("Loại",map3_3);
            String[] map3_4={"RBG","Xanh","Xanh lá","Đỏ","Đơn sắc"};
            map3.put("Đèn",map3_4);

            for(Map.Entry<String, String[]> entry : map3.entrySet()) {
                String key = entry.getKey();
                String[] value = entry.getValue();
                for (int i = 0; i < value.length; i++) {
                    attributesRepository.save(new Attributes(value[i],optionGroupRepository.findOptionGroupByNameAndCategoryName(key,"Phụ kiện")));
                }
            }


            Map<String , String[]> map4 = new HashMap<>();
            String[] map4_1={"AMD","INTEL"};
            map4.put("Thương hiệu",map4_1);
            String[] map4_2={"AMD","Intel"};
            map4.put("Nhà sản xuất",map4_2);
            String[] map4_3={"A Series","A9","Athlon","Core i3","Core i5","Core i7","Pentium","Ryzen 3","Ryzen 5"};
            map4.put("Series CPU",map4_3);
            String[] map4_4={"1150","1151","1151-v2","1200","1700","2066","AM4","TR4"};
            map4.put("Socket",map4_4);

            for(Map.Entry<String, String[]> entry : map4.entrySet()) {
                String key = entry.getKey();
                String[] value = entry.getValue();
                for (int i = 0; i < value.length; i++) {
                    attributesRepository.save(new Attributes(value[i],optionGroupRepository.findOptionGroupByNameAndCategoryName(key,"CPU")));
                }
            }



            Map<String , String[]> map5 = new HashMap<>();
            String[] map5_1={"ADATA","CORSAIR","CRUCIAL","GIGABYTE","INTEL","KINGMAX"};
            map5.put("Thương hiệu",map5_1);
            String[] map5_2={"HDD","SSD","di động HDD","di động SSD","Ổ gắng ngoài"};
            map5.put("Kiểu ổ cứng",map5_2);
            String[] map5_3={"1.5TB","10TB","120GB","128GB","12TB","14TB","16GB","16TB","18TB","1TB","240GB"};
            map5.put("Dung lượng",map5_3);
            String[] map5_4={"3.5mm","DisplayPort","Ethernet","M.2 NVme","SATA","SATA3"};
            map5.put("Chuẩn kết nối",map5_4);

            for(Map.Entry<String, String[]> entry : map5.entrySet()) {
                String key = entry.getKey();
                String[] value = entry.getValue();
                for (int i = 0; i < value.length; i++) {
                    attributesRepository.save(new Attributes(value[i],optionGroupRepository.findOptionGroupByNameAndCategoryName(key,"Ổ cứng")));
                }
            }


            Map<String , String[]> map6 = new HashMap<>();
            String[] map6_1={"ARSOCK","ASUS","Colorful","GIGABYTE","MSI","GALAX"};
            map6.put("Thương hiệu",map6_1);
            String[] map6_2={"AMD","INTEL","NVDIA"};
            map6.put("Nhà sản xuất",map6_2);
            String[] map6_3={"AMD Radeon 6900 series","Arc A","GeForce GTX 10 series","GeForce GTX 16 series","GeForce RTX 20 series","Radeon RX 500 series","Radion RX 5700 XT","khác"};
            map6.put("Series chip đồ họa",map6_3);
            String[] map6_4={};
            map6.put("Tên chip đồ họa",map6_4);
            String[] map6_5={"10GB","12GB","16GB","24GB","2GB","3GB"};
            map6.put("Dung lượng bố nhớ VRAM",map6_4);

            for(Map.Entry<String, String[]> entry : map6.entrySet()) {
                String key = entry.getKey();
                String[] value = entry.getValue();
                for (int i = 0; i < value.length; i++) {
                    attributesRepository.save(new Attributes(value[i],optionGroupRepository.findOptionGroupByNameAndCategoryName(key,"VGA")));
                }
            }


            Map<String , String[]> map7 = new HashMap<>();
            String[] map7_1={"ARSOCK","ASUS","Colorful","GIGABYTE","MSI","INTEL"};
            map7.put("Thương hiệu",map3_1);
            String[] map7_2={"AERO","AORUS","BAZOOKA","EXTREME","Gaming","MAG","ROG"};
            map7.put("Series Mainboard",map3_2);
            String[] map7_3={"A320","A520","B250","B360","B365","B450","B460","B550","b560"};
            map7.put("Chipset",map7_3);
            String[] map7_4={"1150","1151","1151-v2","1200","1700","AM4","sTRX4"};
            map7.put("Socket",map7_4);

            for(Map.Entry<String, String[]> entry : map7.entrySet()) {
                String key = entry.getKey();
                String[] value = entry.getValue();
                for (int i = 0; i < value.length; i++) {
                    attributesRepository.save(new Attributes(value[i],optionGroupRepository.findOptionGroupByNameAndCategoryName(key,"Mainboard")));
                }
            }


            Map<String , String[]> map8 = new HashMap<>();
            String[] map8_1={"ADATA","APACER","CORSAIR","CRUCIAL","Colorful","GEIL","GIGABYTE","HP","kINGMAX"};
            map8.put("Thương hiệu",map8_1);
            String[] map8_2={"destop","laptop","server"};
            map8.put("Loại ram",map8_2);
            String[] map8_3={"1 x 16GB","1 x 32GB","1 x 8GB","2 x 16GB","2 x 32GB","2 x 8GB","4 x 8GB","8 x 32GB"};
            map8.put("Dung lượng ram",map8_3);
            String[] map8_4={"DDR3","DDR4","DĐR5"};
            map8.put("Thế hệ bộ nhớ",map8_4);

            for(Map.Entry<String, String[]> entry : map8.entrySet()) {
                String key = entry.getKey();
                String[] value = entry.getValue();
                for (int i = 0; i < value.length; i++) {
                    attributesRepository.save(new Attributes(value[i],optionGroupRepository.findOptionGroupByNameAndCategoryName(key,"Ram")));
                }
            }


        }
    }
}
