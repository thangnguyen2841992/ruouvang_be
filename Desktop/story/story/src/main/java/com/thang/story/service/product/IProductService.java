package com.thang.story.service.product;

import com.thang.story.model.dto.ProductDTO;
import com.thang.story.model.dto.TotalPage;
import com.thang.story.model.entity.Product;
import com.thang.story.service.IGeneralService;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductService extends IGeneralService<Product> {
    List<Product> findAllAlcohol(int offset);
    List<Product> findAllAccessory(int offset);



    ProductDTO mappingProductToProductDTO(Product product);

    // Lay danh sach ruou vang chile
    List<Product> findProductsByOriginId(Long originId, int offset);

    // Lay danh sach ruou vang trang
    List<Product> findProductsByTypeId(Long originId, int offset);

    List<Product> findProductsByAccessoryId(Long accessoryId, int offset);

    List<Product> findAllAlcoholNoPagination();







}
