package com.example.pbl_api.config;

import com.example.pbl_api.model.*;
import com.example.pbl_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DbInitializer implements ApplicationRunner {
    @Autowired
    BrandRepository brandRepository;

    @Autowired
    SellerCategoryRepository sellerCategoryRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    OptionGroupRepository optionGroupRepository;

    @Autowired
    AttributesRepository attributesRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(sellerCategoryRepository.findAll().spliterator().getExactSizeIfKnown()==0){
            sellerCategoryRepository.save(new SellerCategory("Laptop"));
            sellerCategoryRepository.save(new SellerCategory("PC"));
            sellerCategoryRepository.save(new SellerCategory("Linh kiện"));
            sellerCategoryRepository.save(new SellerCategory("Phụ kiện"));
        }


        if(categoryRepository.findAll().spliterator().getExactSizeIfKnown()==0){
            categoryRepository.save(
                    new Category("Laptop",sellerCategoryRepository.findSellerCategoryByName("Laptop")));
            categoryRepository.save(
                    new Category("Laptop theo thương hiệu",sellerCategoryRepository.findSellerCategoryByName("Laptop")));
            categoryRepository.save(
                    new Category("Laptop theo cấu hình chip",sellerCategoryRepository.findSellerCategoryByName("Laptop")));
            categoryRepository.save(
                    new Category("Laptop theo nhu cầu",sellerCategoryRepository.findSellerCategoryByName("Laptop")));
            categoryRepository.save(
                    new Category("Laptop theo giá",sellerCategoryRepository.findSellerCategoryByName("Laptop")));


            categoryRepository.save(
                    new Category("PC",sellerCategoryRepository.findSellerCategoryByName("PC")));
            categoryRepository.save(
                    new Category("PC theo thương hiệu",sellerCategoryRepository.findSellerCategoryByName("PC")));
            categoryRepository.save(
                    new Category("PC theo nhu cầu",sellerCategoryRepository.findSellerCategoryByName("PC")));
            categoryRepository.save(
                    new Category("PC theo giá",sellerCategoryRepository.findSellerCategoryByName("PC")));

            categoryRepository.save(
                    new Category("CPU",sellerCategoryRepository.findSellerCategoryByName("Linh kiện")));
            categoryRepository.save(
                    new Category("Ổ cứng",sellerCategoryRepository.findSellerCategoryByName("Linh kiện")));
            categoryRepository.save(
                    new Category("VGA",sellerCategoryRepository.findSellerCategoryByName("Linh kiện")));
            categoryRepository.save(
                    new Category("Ram",sellerCategoryRepository.findSellerCategoryByName("Linh kiện")));
            categoryRepository.save(
                    new Category("Mainboard",sellerCategoryRepository.findSellerCategoryByName("Linh kiện")));


            categoryRepository.save(
                    new Category("Phụ kiện",sellerCategoryRepository.findSellerCategoryByName("Phụ kiện")));
            categoryRepository.save(
                    new Category("Bàn phím",sellerCategoryRepository.findSellerCategoryByName("Phụ kiện")));
            categoryRepository.save(
                    new Category("Chuột",sellerCategoryRepository.findSellerCategoryByName("Phụ kiện")));

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
                            categoryRepository.findCategoryByName("Laptop theo nhu cầu")));

            optionGroupRepository.save(
                    new OptionGroup(
                            "Giá",
                            categoryRepository.findCategoryByName("Laptop theo giá")));




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
                            categoryRepository.findCategoryByName("PC theo nhu cầu")));

            optionGroupRepository.save(
                    new OptionGroup(
                            "Giá",
                            categoryRepository.findCategoryByName("PC theo giá")));


            Map<String , String[]> map3 = new HashMap<>();
            String[] map3_1={"Thương hiệu", "Nhà sản xuất","Series CPU","Socket"};
            map3.put("CPU",map3_1);
            String[] map3_2={"Thương hiệu", "Kiểu ổ cứng","Dung lượng","Chuẩn kết nối"};
            map3.put("Ổ cứng",map3_2);
            String[] map3_3={"Thương hiệu", "Nhà sản xuất","Series chip đồ họa","Tên chip độ họa" , "Dung lượng bộ nhớ Vram"};
            map3.put("VGA",map3_3);
            String[] map3_4={"Thương hiệu", "Series Mainboard","Chipset","Socket"};
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
                attributesRepository.save(new Attributes(map1_6[i],optionGroupRepository.findOptionGroupByNameAndCategoryName("Nhu cầu tiêu dùng","Laptop theo nhu cầu")));
            }
            String[] map1_7={"dưới 1 triệu","1-5 triệu","trên 5 triệu"};
            for (int i = 0; i < map1_7.length; i++) {
                attributesRepository.save(new Attributes(map1_7[i],optionGroupRepository.findOptionGroupByNameAndCategoryName("giá","Laptop theo giá")));
            }

        }
    }
}
