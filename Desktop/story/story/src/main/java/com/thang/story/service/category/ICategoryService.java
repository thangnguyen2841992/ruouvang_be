package com.thang.story.service.category;

import com.thang.story.model.dto.CategoryDTO;
import com.thang.story.model.entity.Category;
import com.thang.story.service.IGeneralService;

public interface ICategoryService extends IGeneralService<Category> {
    CategoryDTO mappingCategoryToCategoryDTO(Category category);
}
