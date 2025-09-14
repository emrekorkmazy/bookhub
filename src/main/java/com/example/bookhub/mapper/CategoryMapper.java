package com.example.bookhub.mapper;

import com.example.bookhub.dto.CategoryDTO;
import com.example.bookhub.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "books" , ignore = true)
    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryDTO categoryDTO);

    List<CategoryDTO> toDTOList(List<Category> categoryList);

    List<Category> toEntityList(List<CategoryDTO> categoryDTOList);
}
