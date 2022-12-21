package com.thang.story.repository;

import com.thang.story.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    //Lấy danh sách rượu vang.
    @Query(value = "select * from products where accessory_id = 0  order by name limit 10 offset ?1", nativeQuery = true)
    List<Product> findAllAlcohol(int offset);
    //Lấy danh sách phụ kiện rượu vang
    @Query(value = "select * from products where accessory_id != 0  order by name limit 10 offset ?1", nativeQuery = true)
    List<Product> findAllAccessory(int offset);

    //Xoá sản phẩm

}
