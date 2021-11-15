package ru.online.shop.service;


import org.springframework.stereotype.Service;
import ru.online.shop.entity.Category;


import ru.online.shop.repositoriy.CategoryRepository;

@Service
public class CategoryService {
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private final CategoryRepository categoryRepository;

    public Category getCategoryByName(String categoryName) {
        return categoryRepository.getCategoryByName(categoryName);
    }
}
