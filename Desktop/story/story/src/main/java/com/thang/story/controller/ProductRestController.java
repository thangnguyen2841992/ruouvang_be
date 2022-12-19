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

    @GetMapping
    public ResponseEntity<?> getAllProducts(@RequestParam(value = "offset") int offset) {
        List<Product> products = this.productService.findAllProducts(offset);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productDTOList.add(this.productService.mappingProductToProductDTO(products.get(i)));
        }
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @GetMapping("/accessory")
    public ResponseEntity<?> getAllProductCategory3(@RequestParam(value = "offset") int offset) {
        List<Product> products = this.productService.findAllProductByCategory3(offset);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productDTOList.add(this.productService.mappingProductToProductDTO(products.get(i)));
        }
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @GetMapping("/alcohol")
    public ResponseEntity<?> getAllProductCategory1and2(@RequestParam(value = "offset") int offset) {
        List<Product> products = this.productService.findAllProductByCategory1and2(offset);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productDTOList.add(this.productService.mappingProductToProductDTO(products.get(i)));
        }
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewProduct(@RequestBody Product product) {
        Optional<Origin> categoryOptional = this.categoryService.findById(product.getOriginId());
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Origin không đúng"), HttpStatus.BAD_REQUEST);
        }
        Optional<Accessory> brandOptional = this.brandService.findById(product.getAccessoryId());
        if (!brandOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Accessory Không đúng"), HttpStatus.BAD_REQUEST);
        }
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
        product.setImage(productForm.getImage());
        this.productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
