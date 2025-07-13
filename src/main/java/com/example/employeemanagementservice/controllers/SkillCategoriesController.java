package com.example.employeemanagementservice.controllers;

import com.example.employeemanagementservice.annotations.CommonSkillCategoryResponses;
import com.example.employeemanagementservice.models.Skill;
import com.example.employeemanagementservice.models.SkillCategory;
import com.example.employeemanagementservice.services.SkillCategoriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully returned list of Skill Categories")
    })
    @Operation(
            summary = "find all Skill Categories",
            description = "returns all available Skill Categories in memory"
    )
    public List<SkillCategory> getAllCategories(){
        return skillCategoriesService.getAllCategories();
    }
    @GetMapping("/{categoryId}")
    @CommonSkillCategoryResponses
    @Operation(
            summary = "find Skill Category with specified ID",
            description = "returns Skill Category or throws 404"
    )
    public SkillCategory getCategoryById(@PathVariable Integer categoryId){
        return skillCategoriesService.getCategoryById(categoryId);
    }

    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = " Successfully created new Skill category")
    })
    @Operation(summary = "create new Skill Category",
            description = "creates new Skill Category and returns it"
    )
    public ResponseEntity<SkillCategory> createCategory(@RequestBody CategoryRequest categoryRequest){
        SkillCategory skillCategory = skillCategoriesService
                .createCategory(categoryRequest.name, categoryRequest.area);
        return ResponseEntity.ok(skillCategory);
    }

    @PutMapping("/{categoryId}")
    @CommonSkillCategoryResponses
    @Operation(
            summary = "update Skill Category with specified ID",
            description = "returns updated Skill Category or throws 404 if not found category to update"
    )
    public ResponseEntity<SkillCategory> updateCategory(@PathVariable Integer categoryId, @RequestBody CategoryRequest categoryRequest){
        SkillCategory skillCategory = skillCategoriesService.updateCategory(categoryId,
                categoryRequest.name, categoryRequest.area);
        return ResponseEntity.ok(skillCategory);
    }
    @DeleteMapping("/{categoryId}")
    @CommonSkillCategoryResponses
    @Operation(
            summary = "delete Skill Category with specified ID",
            description = "deletes Skill Category with specified ID from memory if exist"
    )
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer categoryId){
        skillCategoriesService.delete(categoryId);
        return ResponseEntity.noContent().build();
    }

    public record CategoryRequest(String name, String area){}

}
