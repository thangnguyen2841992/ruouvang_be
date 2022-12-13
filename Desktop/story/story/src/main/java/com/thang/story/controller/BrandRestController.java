package com.thang.story.controller;

import com.thang.story.model.dto.Message;
import com.thang.story.model.entity.Brand;
import com.thang.story.model.entity.Category;
import com.thang.story.service.brand.IBrandService;
import com.thang.story.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/brands")
public class BrandRestController {
    @Autowired
    private IBrandService brandService;
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/category/{categoryID}")
    public ResponseEntity<?> getAllBrandOfCategory(@PathVariable Long categoryID) {
        List<Brand> brandList = this.brandService.getBrandsByCategoryId(categoryID);
        if (brandList.size() == 0) {
         return new ResponseEntity<>(new Message("Không có brand nào!"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(brandList, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createNewBrand(@RequestBody Brand brand) {
        Optional<Category> categoryOptional = this.categoryService.findById(brand.getCategoryId());
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Category không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
       this.brandService.save(brand);
        return new ResponseEntity<>(brand, HttpStatus.CREATED);
    }
}
