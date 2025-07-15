package com.example.employeemanagementservice.controllers;

import com.example.employeemanagementservice.annotations.*;
import com.example.employeemanagementservice.exceptions.GlobalExceptionHandler;
import com.example.employeemanagementservice.models.Skill;
import com.example.employeemanagementservice.services.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill-categories/{category_id}/skills")
@Tag(name = "Skill management", description = "API for viewing, adding, deleting, and updating Skills")
@Validated
public class SkillController {
    private final SkillService SkillService;

    public SkillController(SkillService SkillService) {
        this.SkillService = SkillService;
    }

    @GetMapping
    @CommonSuccessfullyResponses
    @NumericIdNotFoundResponse
    @InternalServerErrorResponse
    @Operation(
            summary = "find all Skills in category",
            description = "returns all available Skills in Category from memory"
    )
    public List<Skill> getSkillsInCategory(@NumericIdParam @PathVariable Integer category_id){
        return SkillService.getSkillsInCategory(category_id);
    }
    @GetMapping("/{id}")
    @CommonSuccessfullyResponses
    @NumericIdNotFoundResponse
    @InternalServerErrorResponse
    @Operation(
            summary = "find Skill with specified ID in current category",
            description = "returns Skill with specified Id in selected Category from memory"
    )
    public Skill getSkillById(
            @NumericIdParam
            @PathVariable Integer category_id,
            @NumericIdParam
            @PathVariable Integer id){
        return SkillService.getSkillById(category_id, id);
    }

    @PostMapping
    @CommonSuccessfullyResponses
    @NumericIdNotFoundResponse
    @InternalServerErrorResponse
    @Operation(summary = "create new Skill",
            description = "creates new Skill and returns it"
    )
    public ResponseEntity<Skill> createSkill(@NumericIdParam @PathVariable Integer category_id,
                                             @Valid @RequestBody SkillCreateInCategoryRequest skillCreateInCategoryRequest){
        Skill skill = SkillService.createSkill(category_id, skillCreateInCategoryRequest.name);
        return ResponseEntity.ok(skill);
    }
    @PutMapping("/{id}")
    @CommonSuccessfullyResponses
    @NumericIdNotFoundResponse
    @InternalServerErrorResponse
    @Operation(
            summary = "update Skill with specified ID in current category",
            description = "returns updated Skill"
    )
    public ResponseEntity<Skill> updateSkill(@NumericIdParam @PathVariable Integer category_id,
                                             @NumericIdParam @PathVariable Integer id,
                                             @Valid @RequestBody SkillPutRequest skillPutRequest){
        Skill skill = SkillService.updateSkillById(category_id, id, skillPutRequest.name, skillPutRequest.categoryId);
        return ResponseEntity.ok(skill);
    }
    @DeleteMapping("/{id}")
    @NumericIdNotFoundResponse
    @InternalServerErrorResponse
    @DeleteApiResponses
    @Operation(
            summary = "delete Skill with specified ID in selected category",
            description = "deletes Skill  with specified ID if exist, No content returns"
    )
    public ResponseEntity<Void> deleteSkill(@NumericIdParam @PathVariable Integer category_id,
                                            @NumericIdParam @PathVariable Integer id){
        SkillService.deleteSkillById(category_id, id);
        return ResponseEntity.noContent().build();
    }
    @Schema(description = "Request body for updating Skill (its possible to transfer skill to another category)")
    public record SkillPutRequest(
            @NotBlank(message = "Skill name should not be blank")
            @Size(min = 3, max = 25, message = "Skill Name length should be 3 to 25 symbols long")
            @Schema(description = "Skill name", example = "database skills")
            String name,
            @NotNull(message = "Category ID should not be empty")
            @Positive
            @Schema(description = "Skill Category ID", example = "1")
            Integer categoryId){}
    @Schema(description = "Request body for creating Skill in selected category")
    public record SkillCreateInCategoryRequest(
            @NotBlank(message = "Skill name should not be blank")
            @Size(min = 3, max = 25, message = "Skill Name length should be 3 to 25 symbols long")
            @Schema(description = "Skill in category name", example = "database skills")
            String name){}
}
