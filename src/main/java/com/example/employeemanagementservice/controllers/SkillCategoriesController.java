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
    @GetMapping("/{categoryId}/skills")
    public List<Skill> getSkillsInCategory(@PathVariable Integer categoryId){
        return skillCategoriesService.getSkillsInCategory(categoryId);
    }
    @GetMapping("/{categoryId}/skills/{id}")
    public Skill getSkillById(@PathVariable Integer categoryId, @PathVariable Integer id){
        return skillCategoriesService.getSkillById(categoryId, id);
    }

    @PostMapping("/{categoryId}/skills")
    public ResponseEntity<Skill> createSkill(@PathVariable Integer categoryId, @RequestBody SkillRequest skillRequest){
        Skill skill = skillCategoriesService.createSkill(categoryId, skillRequest.name);
        return ResponseEntity.ok(skill);
    }
    @PutMapping("/{categoryId}/skills/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable Integer categoryId, @PathVariable Integer id, @RequestBody SkillRequest skillRequest){
        Skill skill = skillCategoriesService.updateSkillById(categoryId, id, skillRequest.name, skillRequest.categoryId);
        return ResponseEntity.ok(skill);
    }
    @DeleteMapping("/{categoryId}/skills/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Integer categoryId, @PathVariable Integer id){
        skillCategoriesService.deleteSkillById(categoryId, id);
        return ResponseEntity.noContent().build();
    }
    public record CategoryRequest(String name, String area){}
    public record SkillRequest(String name, Integer categoryId){}

}
