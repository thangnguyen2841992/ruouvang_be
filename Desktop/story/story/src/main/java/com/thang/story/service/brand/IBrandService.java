package com.thang.story.service.brand;

import com.thang.story.model.entity.Brand;
import com.thang.story.service.IGeneralService;

import java.util.List;

public interface IBrandService extends IGeneralService<Brand> {
    List<Brand> getBrandsByCategoryId(Long categoryId);

}
