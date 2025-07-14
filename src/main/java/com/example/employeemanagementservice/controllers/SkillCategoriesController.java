package com.example.employeemanagementservice.controllers;

import com.example.employeemanagementservice.annotations.*;
import com.example.employeemanagementservice.models.SkillCategory;
import com.example.employeemanagementservice.services.SkillCategoriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/skill-categories")
@Tag(name = "Skill Categories management", description = "API for viewing, adding, deleting, and updating Skill Categories")
@Validated
public class SkillCategoriesController {
    private final SkillCategoriesService skillCategoriesService;

    public SkillCategoriesController(SkillCategoriesService skillCategoriesService) {
        this.skillCategoriesService = skillCategoriesService;
    }
    @GetMapping
    @CommonSuccessfullyResponses
    @InternalServerErrorResponse
    @Operation(
            summary = "find all Skill Categories",
            description = "returns all available Skill Categories in memory"
    )
    public List<SkillCategory> getAllCategories(){
        return skillCategoriesService.getAllCategories();
    }
    @GetMapping("/{categoryId}")
    @CommonSuccessfullyResponses
    @NumericIdNotFoundResponse
    @InternalServerErrorResponse
    @Operation(
            summary = "find Skill Category with specified ID",
            description = "returns Skill Category or throws 404"
    )
    public SkillCategory getCategoryById(
            @NumericIdParam @PathVariable Integer categoryId){
        return skillCategoriesService.getCategoryById(categoryId);
    }

    @PostMapping
    @CommonSuccessfullyResponses
    @InternalServerErrorResponse
    @Operation(summary = "create new Skill Category",
            description = "creates new Skill Category and returns it"
    )
    public ResponseEntity<SkillCategory> createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        SkillCategory skillCategory = skillCategoriesService
                .createCategory(categoryRequest.name, categoryRequest.area);
        return ResponseEntity.ok(skillCategory);
    }

    @PutMapping("/{categoryId}")
    @CommonSuccessfullyResponses
    @NumericIdNotFoundResponse
    @InternalServerErrorResponse
    @Operation(
            summary = "update Skill Category with specified ID",
            description = "returns updated Skill Category or throws 404 if not found category to update"
    )
    public ResponseEntity<SkillCategory> updateCategory(@NumericIdParam @PathVariable Integer categoryId,
                                                        @Valid @RequestBody CategoryRequest categoryRequest){
        SkillCategory skillCategory = skillCategoriesService.updateCategory(categoryId,
                categoryRequest.name, categoryRequest.area);
        return ResponseEntity.ok(skillCategory);
    }
    @DeleteMapping("/{categoryId}")
    @DeleteApiResponses
    @NumericIdNotFoundResponse
    @InternalServerErrorResponse
    @Operation(
            summary = "delete Skill Category with specified ID",
            description = "deletes Skill Category with specified ID from memory if exist"
    )
    public ResponseEntity<Void> deleteCategory(@NumericIdParam @PathVariable Integer categoryId){
        skillCategoriesService.delete(categoryId);
        return ResponseEntity.noContent().build();
    }

    public record CategoryRequest(
            @NotBlank(message = "Category name should not be blank")
            @Size(min = 3, max = 25, message = "Category Name length should be 3 to 25 symbols long")
            @Schema(description = "Skill Category name", example = "Programming")
            String name,
            @NotBlank(message = "area name should not be blank")
            @Size(min = 3, max = 25, message = "area Name length should be 3 to 25 symbols long")
            @Schema(description = "Area name", example = "Engineering")
            String area){}

}
