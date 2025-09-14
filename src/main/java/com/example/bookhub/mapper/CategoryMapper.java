package com.example.bookhub.mapper;

import com.example.bookhub.dto.CategoryDTO;
import com.example.bookhub.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDTO(Category category);
    @Mapping(target = "books" , ignore = true)
    Category toEntity(CategoryDTO categoryDTO);

    List<CategoryDTO> toDTOList(List<Category> categoryList);

    List<Category> toEntityList(List<CategoryDTO> categoryDTOList);
}
