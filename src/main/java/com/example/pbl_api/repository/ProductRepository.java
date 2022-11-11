package com.example.pbl_api.repository;

import com.example.pbl_api.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductRepository extends CrudRepository<Product,Long> {
    Product findProductById(long id);

    List<Product> findProductsByCategoryId(int idCategory );

//    @Query(value = "select product.id ,product.name,product.price,product.description,product.status,\n" +
//            "product.rate, \n" +
//            "product.brand,product.category\n" +
//            " from product \n" +
//            " join product_attributes on product.id=product_attributes.product_id \n" +
//            " join attributes on product_attributes.attributes_id=attributes.id\n" +
//            " where product.category=?1 " +
//            "and (attributes.id =?2 or ?2 is null)" +
//            "and (attributes.id =?3 or ?3 is null)" +
//            "and (attributes.id =?4 or ?4 is null)" +
//            "and (attributes.id =?5 or ?5 is null)" +
//            "and (attributes.id =?6 or ?6 is null)",
//            nativeQuery = true)
    @Query(value = "select product.id ,product.name,product.price,product.description,product.status,\n" +
            "            product.rate, \n" +
            "            product.brand,product.category\n" +
            "            from product_attributes\n" +
            "            join product on product.id=product_attributes.product_id\n" +
            "            join attributes on product_attributes.attributes_id=attributes.id\n" +
            "            where product.category=?1 and product_attributes.attributes_id in (?2,?3,?4,?5,?6)\n" +
            "            group by product_attributes.product_id having count(product_attributes.attributes_id)>?7",
            nativeQuery = true)
    List<Product> findProductsByFilter(Integer idCategory,Integer f1,Integer f2,Integer f3,Integer f4,Integer f5,int size);

}
