package com.example.listam.controller;

import com.example.listam.entity.Category;
import com.example.listam.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/categories")
@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String categoriesPage(ModelMap map) {
        List<Category> all = categoryRepository.findAll();
        map.addAttribute("categories", all);
        return "/categories";
    }

    @GetMapping("/add")
    public String addCategoryPage() {
        return "/addCategory";
    }

    @PostMapping("/add")
    public String addCategory(@RequestParam("name") String name) {
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/remove")
    public String removeCategory(@RequestParam("id") int id) {
        categoryRepository.deleteById(id);
        return "redirect:/categories";
    }
}
