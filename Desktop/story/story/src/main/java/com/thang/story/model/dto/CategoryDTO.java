package com.thang.story.model.dto;

import com.thang.story.model.entity.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDTO {
    private Long id;

    private String name;

    private List<Brand> brandList;
}
