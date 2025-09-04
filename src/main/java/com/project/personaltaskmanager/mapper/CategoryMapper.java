package com.project.personaltaskmanager.mapper;

import com.project.personaltaskmanager.dto.CategoryDto;
import com.project.personaltaskmanager.entity.Category;

public class CategoryMapper {
    public CategoryDto toDto(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDto dto = new CategoryDto();
        dto.setCategory_id(category.getCategory_id());
        dto.setCategory_name(category.getCategory_name());
        return dto;
    }

    public Category toEntity(CategoryDto dto) {
        if (dto == null) {
            return null;
        }
        Category category = new Category();
        category.setCategory_id(dto.getCategory_id());
        category.setCategory_name(dto.getCategory_name());
        return category;
    }
}