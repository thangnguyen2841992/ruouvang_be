package com.thang.story.service.product;

import com.thang.story.model.dto.ProductDTO;
import com.thang.story.model.entity.Product;
import com.thang.story.service.IGeneralService;

import java.util.List;

public interface IProductService extends IGeneralService<Product> {
    List<Product> findAllAlcohol(int offset);
    List<Product> findAllAccessory(int offset);



    ProductDTO mappingProductToProductDTO(Product product);




}
