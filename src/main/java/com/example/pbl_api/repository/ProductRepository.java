package com.example.pbl_api.repository;

import com.example.pbl_api.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    Product findProductById(long id);

    List<Product> findProductsByCategoryId(int idCategory );

    @Query(value = "select product.id ,product.name,product.price,product.information,product.description,product.status,product.popular,\n" +
            "product.rate, \n" +
            "product.brand,product.warranty,product.category\n" +
            " from product \n" +
            " join product_attributes on product.id=product_attributes.product_id \n" +
            " join attributes on product_attributes.attributes_id=attributes.id\n" +
            " where product.category=?1 " +
            "and (attributes.id =?2 or ?2 is null)" +
            "and (attributes.id =?3 or ?3 is null)",
            nativeQuery = true)
    List<Product> findProductsByFillter(Integer idCategory,Integer f1,Integer f2);

}
