package com.thang.story.service.product;

import com.thang.story.model.dto.ProductDTO;
import com.thang.story.model.dto.TotalPage;
import com.thang.story.model.entity.Accessory;
import com.thang.story.model.entity.Origin;
import com.thang.story.model.entity.Product;
import com.thang.story.model.entity.Type;
import com.thang.story.repository.IProductRepository;
import com.thang.story.service.accessory.IAccessoryService;
import com.thang.story.service.origin.IOriginService;
import com.thang.story.service.type.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


@Service
public class ProductServiceImpl implements IProductService{
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IAccessoryService accessoryService;
    @Autowired
    private IOriginService originService;
    @Autowired
    private ITypeService typeService;
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
    public List<Product> findAllAlcohol(int offset) {
        return this.productRepository.findAllAlcohol(offset);
    }

    @Override
    public List<Product> findAllAccessory(int offset) {
        return this.productRepository.findAllAccessory(offset);
    }

    @Override
    public ProductDTO mappingProductToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setImage(product.getImage());
        productDTO.setDescription(product.getDescription());
        productDTO.setContent(product.getContent());
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(product.getPrice());
        productDTO.setPrice(str1);
        productDTO.setQuantity(product.getQuantity());
        Optional<Origin> origin = this.originService.findById(product.getOriginId());
        if (origin.isPresent()) {
            productDTO.setOrigin(origin.get().getName());
        }else {
            productDTO.setOrigin("");
        }
        Optional<Accessory> accessory = this.accessoryService.findById(product.getAccessoryId());
        if (accessory.isPresent()) {
            productDTO.setAccessory(accessory.get().getName());
        }else {
            productDTO.setAccessory("");
        }
        Optional<Type> type = this.typeService.findById(product.getTypeId());
        if (type.isPresent()) {
            productDTO.setType(type.get().getName());
        }else {
            productDTO.setType("");
        }
        return productDTO;
    }

    @Override
    public List<Product> findProductsByOriginId(Long originId, int offset) {
        return this.productRepository.findProductsByOriginId(originId, offset);
    }

    @Override
    public List<Product> findProductsByTypeId(Long originId, int offset) {
        return this.productRepository.findProductsByTypeId(originId, offset);
    }

    @Override
    public List<Product> findProductsByAccessoryId(Long accessoryId, int offset) {
        return this.productRepository.findProductsByAccessoryId(accessoryId, offset);
    }

    @Override
    public List<Product> findAllAlcoholNoPagination() {
        return this.productRepository.findAllAlcoholNoPagination();
    }

    @Override
    public List<Product> getProductById(Long id) {
        return this.productRepository.getProductById(id);
    }


}
