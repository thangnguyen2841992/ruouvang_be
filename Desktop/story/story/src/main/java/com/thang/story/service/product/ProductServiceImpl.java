package com.thang.story.service.product;

import com.thang.story.model.dto.ProductDTO;
import com.thang.story.model.entity.Accessory;
import com.thang.story.model.entity.Origin;
import com.thang.story.model.entity.Product;
import com.thang.story.repository.IProductRepository;
import com.thang.story.service.accessory.IAccessoryService;
import com.thang.story.service.origin.IOriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService{
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IAccessoryService brandService;
    @Autowired
    private IOriginService categoryService;
    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllProducts(int offset) {
        return this.productRepository.findAllProducts(offset);
    }

    @Override
    public ProductDTO mappingProductToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setImage(product.getImage());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        Origin origin = this.categoryService.findById(product.getOriginId()).get();
        productDTO.setOrigin(origin.getName());
        Accessory accessory = this.brandService.findById(product.getAccessoryId()).get();
        productDTO.setAccessory(accessory.getName());
        return productDTO;
    }

    @Override
    public List<Product> findAllProductByCategory3(int offset) {
        return this.productRepository.findAllProductByCategory3(offset);
    }

    @Override
    public List<Product> findAllProductByCategory1and2(int offset) {
        return this.productRepository.findAllProductByCategory1and2(offset);
    }
}
