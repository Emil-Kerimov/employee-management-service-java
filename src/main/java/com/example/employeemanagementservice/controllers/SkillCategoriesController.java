package com.example.employeemanagementservice.controllers;

import com.example.employeemanagementservice.models.Skill;
import com.example.employeemanagementservice.models.SkillCategory;
import com.example.employeemanagementservice.services.SkillCategoriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/skill-categories")
public class SkillCategoriesController {
    private final SkillCategoriesService skillCategoriesService;

    public SkillCategoriesController(SkillCategoriesService skillCategoriesService) {
        this.skillCategoriesService = skillCategoriesService;
    }
    @GetMapping
    public List<SkillCategory> getAllCategories(){
        return skillCategoriesService.getAllCategories();
    }
    @GetMapping("/{categoryId}")
    public SkillCategory getCategoryById(@PathVariable Integer categoryId){
        return skillCategoriesService.getCategoryById(categoryId);
    }

    @PostMapping
    public ResponseEntity<SkillCategory> createCategory(@RequestBody CategoryRequest categoryRequest){
        SkillCategory skillCategory = skillCategoriesService
                .createCategory(categoryRequest.name, categoryRequest.area);
        return ResponseEntity.ok(skillCategory);
    }
    @PutMapping("/{categoryId}")
    public ResponseEntity<SkillCategory> updateCategory(@PathVariable Integer categoryId, @RequestBody CategoryRequest categoryRequest){
        SkillCategory skillCategory = skillCategoriesService.updateCategory(categoryId,
                categoryRequest.name, categoryRequest.area);
        return ResponseEntity.ok(skillCategory);
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer categoryId){
        skillCategoriesService.delete(categoryId);
        return ResponseEntity.noContent().build();
    }

    public record CategoryRequest(String name, String area){}

}
