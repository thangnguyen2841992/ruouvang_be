package com.thang.story.controller;

import com.thang.story.model.dto.Message;
import com.thang.story.model.dto.ProductDTO;
import com.thang.story.model.entity.Accessory;
import com.thang.story.model.entity.Origin;
import com.thang.story.model.entity.Product;
import com.thang.story.service.accessory.IAccessoryService;
import com.thang.story.service.origin.IOriginService;
import com.thang.story.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductRestController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IOriginService categoryService;
    @Autowired
    private IAccessoryService brandService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = this.productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Sản phẩm không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/accessory")
    public ResponseEntity<?> getAllProductCategory3(@RequestParam(value = "offset") int offset) {
        List<Product> products = this.productService.findAllAccessory(offset);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productDTOList.add(this.productService.mappingProductToProductDTO(products.get(i)));
        }
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @GetMapping("/alcohol")
    public ResponseEntity<?> getAllAlcohol(@RequestParam(value = "offset") int offset) {
        List<Product> products = this.productService.findAllAlcohol(offset);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productDTOList.add(this.productService.mappingProductToProductDTO(products.get(i)));
        }
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewProduct(@RequestBody Product product) {
        this.productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId, @RequestBody Product productForm) {
        Optional<Product> productOptional = this.productService.findById(productId);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Sản phẩm không tồn tại"), HttpStatus.BAD_REQUEST);
        }
        Product product = productOptional.get();
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        product.setQuantity(productForm.getQuantity());
        product.setDescription(productForm.getDescription());
        product.setOriginId(productForm.getOriginId());
        product.setAccessoryId(productForm.getAccessoryId());
        product.setTypeId(productForm.getTypeId());
        product.setImage(productForm.getImage());
        this.productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{productID}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productID) {
        Optional<Product> productOptional = this.productService.findById(productID);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Sản phẩm không tồn tại"), HttpStatus.BAD_REQUEST);
        }
        this.productService.delete(productID);
        return new ResponseEntity<>(new Message("Xoá thành công!"), HttpStatus.OK);
    }


}
