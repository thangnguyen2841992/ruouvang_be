package com.thang.story.controller;

import com.thang.story.model.dto.CategoryDTO;
import com.thang.story.model.dto.Message;
import com.thang.story.model.entity.Category;
import com.thang.story.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryRestController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAllCategory() {
        List<Category> categoryList = this.categoryService.findAll();
        if (categoryList.size() == 0) {
            return new ResponseEntity<>(new Message("Không có Category nào!"), HttpStatus.NO_CONTENT);
        }
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++) {
            categoryDTOS.add(this.categoryService.mappingCategoryToCategoryDTO(categoryList.get(i)));
        }
        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> finfCategoryById(@PathVariable Long id) {
        Optional<Category> categoryOptional = this.categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Category không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
    }
}
