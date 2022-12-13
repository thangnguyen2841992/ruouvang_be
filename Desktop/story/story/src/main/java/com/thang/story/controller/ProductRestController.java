package com.thang.story.controller;

import com.thang.story.model.dto.Message;
import com.thang.story.model.entity.Brand;
import com.thang.story.model.entity.Category;
import com.thang.story.model.entity.Product;
import com.thang.story.service.brand.IBrandService;
import com.thang.story.service.category.ICategoryService;
import com.thang.story.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductRestController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IBrandService brandService;

    @PostMapping
    public ResponseEntity<?> createNewProduct(@RequestBody Product product) {
        Optional<Category> categoryOptional = this.categoryService.findById(product.getCategoryId());
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Category không đúng"), HttpStatus.BAD_REQUEST);
        }
        Optional<Brand> brandOptional = this.brandService.findById(product.getBrandId());
        if (!brandOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Brand Không đúng"), HttpStatus.BAD_REQUEST);
        }
        this.productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

}
