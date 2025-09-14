package com.example.bookhub.service;

import com.example.bookhub.dto.CategoryDTO;
import com.example.bookhub.entity.Category;
import com.example.bookhub.mapper.CategoryMapper;
import com.example.bookhub.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CategoryDTO> getCategoryById(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::toDTO);
    }

    public CategoryDTO createCategory(CategoryDTO dto) {
        Category category = categoryMapper.toEntity(dto);
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO dto) {
        return categoryRepository.findById(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    return categoryMapper.toDTO(categoryRepository.save(existing));
                })
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
