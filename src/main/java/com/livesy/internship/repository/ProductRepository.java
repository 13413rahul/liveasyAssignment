package com.livesy.internship.repository;

import com.livesy.internship.entity.Product;
import com.livesy.internship.entity.Truck;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Modifying
    @Query(value = "insert into Product(product_type) values(:productType)",nativeQuery = true)
    @Transactional
    void insertIntoProductTable(@Param("productType") String productType);

    Product findByProductType(String productType);

    boolean existsByProductType(String productType);

    Product findByProductId(Long productId);

}
