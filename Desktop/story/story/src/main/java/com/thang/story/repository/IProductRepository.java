package com.thang.story.repository;

import com.thang.story.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    //Lấy list danh sách rượu vang theo productID
    @Query(value = "select * from products where id = ?1", nativeQuery = true)
    List<Product> getProductById(Long id);
    //Lấy danh sách rượu vang.
    @Query(value = "select * from products where accessory_id = 0  order by name limit 12 offset ?1", nativeQuery = true)
    List<Product> findAllAlcohol(int offset);

    //Lấy danh sách rượu vang khong phan trang.
    @Query(value = "select * from products where accessory_id = 0", nativeQuery = true)
    List<Product> findAllAlcoholNoPagination();
    //Lấy danh sách phụ kiện rượu vang
    @Query(value = "select * from products where accessory_id != 0  order by name limit 12 offset ?1", nativeQuery = true)
    List<Product> findAllAccessory(int offset);
    //Lấy danh sách phụ kiện rượu vang khong phan trang
    @Query(value = "select * from products where accessory_id != 0", nativeQuery = true)
    List<Product> findAllAccessoryNoPagination();

    // Lay danh sach ruou vang chile
    @Query(value = "select * from products where origin_id = ?1 and accessory_id = 0 order by name limit 12 offset ?2", nativeQuery = true)
    List<Product> findProductsByOriginId(Long originId, int offset);
    // Lay danh sach ruou vang chile khong phan trang
    @Query(value = "select * from products where origin_id = ?1 and accessory_id = 0", nativeQuery = true)
    List<Product> findProductsByOriginIdNoPagination(Long originId);
    // Lay danh sach ruou vang trang
    @Query(value = "select * from products where type_id = ?1 and accessory_id = 0 order by name limit 12 offset ?2", nativeQuery = true)
    List<Product> findProductsByTypeId(Long originId, int offset);
    // Lay danh sach ruou vang trang
    @Query(value = "select * from products where type_id = ?1 and accessory_id = 0", nativeQuery = true)
    List<Product> findProductsByTypeIdNoPagination(Long originId);
    //Lay danh sach cu the tung phu kien
    @Query(value = "select * from products where accessory_id = ?1 and type_id = 0 and origin_id = 0  order by name limit 12 offset ?2", nativeQuery = true)
    List<Product> findProductsByAccessoryId(Long accessoryId, int offset);
    //Lay danh sach cu the tung phu kien
    @Query(value = "select * from products where accessory_id = ?1 and type_id = 0 and origin_id = 0", nativeQuery = true)
    List<Product> findProductsByAccessoryIdNoPagination(Long accessoryId);

}
