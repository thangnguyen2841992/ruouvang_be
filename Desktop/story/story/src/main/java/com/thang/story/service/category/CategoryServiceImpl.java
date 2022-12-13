package com.thang.story.service.category;

import com.thang.story.model.dto.CategoryDTO;
import com.thang.story.model.entity.Category;
import com.thang.story.repository.ICategoryRepository;
import com.thang.story.service.brand.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IBrandService brandService;
    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return this.categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDTO mappingCategoryToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setBrandList(this.brandService.getBrandsByCategoryId(category.getId()));
        return categoryDTO;
    }
}
