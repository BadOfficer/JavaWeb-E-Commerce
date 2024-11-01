package com.tb.javaecommerce.service.impl;

import com.tb.javaecommerce.domain.Category;
import com.tb.javaecommerce.service.CategoryService;
import com.tb.javaecommerce.service.exception.CategoryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    List<Category> categoryList = new ArrayList<>(List.of(
            Category.builder().id(1L).title("Cosmic cats").description("Some description for cosmic cats").build(),
            Category.builder().id(2L).title("Star cats").description("Some description for star cats").build()
    ));

    @Override
    public List<Category> findAllCategories() {
        return categoryList;
    }

    @Override
    public Category findCategoryById(int id) {
        return categoryList.stream().filter(category -> category.getId() == id).findFirst().orElseThrow(() -> new CategoryNotFoundException(id));
    }
}
