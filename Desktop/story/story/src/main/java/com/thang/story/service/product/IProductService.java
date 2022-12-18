package com.thang.story.service.product;

import com.thang.story.model.dto.ProductDTO;
import com.thang.story.model.entity.Product;
import com.thang.story.service.IGeneralService;

import java.util.List;

public interface IProductService extends IGeneralService<Product> {
    List<Product> findAllProducts(int offset);

    ProductDTO mappingProductToProductDTO(Product product);


    List<Product> findAllProductByCategory3(int offset);

    List<Product> findAllProductByCategory1and2(int offset);


}
