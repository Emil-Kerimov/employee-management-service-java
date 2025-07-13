package com.example.employeemanagementservice.controllers;

import com.example.employeemanagementservice.annotations.*;
import com.example.employeemanagementservice.exceptions.GlobalExceptionHandler;
import com.example.employeemanagementservice.models.Skill;
import com.example.employeemanagementservice.services.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill-categories/{category_id}/skills")
@Tag(name = "Skill management", description = "API for viewing, adding, deleting, and updating Skills")
public class SkillController {
    private final com.example.employeemanagementservice.services.SkillService SkillService;

    public SkillController(SkillService SkillService) {
        this.SkillService = SkillService;
    }

    @GetMapping
    @CommonSkillCategoryResponses
    @Operation(
            summary = "find all Skills in category",
            description = "returns all available Skills in Category from memory"
    )
    public List<Skill> getSkillsInCategory(@PathVariable Integer category_id){
        return SkillService.getSkillsInCategory(category_id);
    }
    @GetMapping("/{id}")
    @CommonSkillInCategoryResponses
    @Operation(
            summary = "find Skill with specified ID in current category",
            description = "returns Skill with specified Id in selected Category from memory"
    )
    public Skill getSkillById(
            @Parameter(description = "ID of the category", example = "1")
            @PathVariable Integer category_id,
            @Parameter(description = "ID of the skill", example = "1")
            @PathVariable Integer id){
        return SkillService.getSkillById(category_id, id);
    }

    @PostMapping
    @CommonSkillInCategoryResponses
    @Operation(summary = "create new Skill",
            description = "creates new Skill and returns it"
    )
    public ResponseEntity<Skill> createSkill(@PathVariable Integer category_id, @RequestBody SkillCreateInCategoryRequest skillCreateInCategoryRequest){
        Skill skill = SkillService.createSkill(category_id, skillCreateInCategoryRequest.name);
        return ResponseEntity.ok(skill);
    }
    @PutMapping("/{id}")
    @CommonSkillInCategoryResponses
    @Operation(
            summary = "update Skill with specified ID in current category",
            description = "returns updated Skill"
    )
    public ResponseEntity<Skill> updateSkill(@PathVariable Integer category_id, @PathVariable Integer id, @RequestBody SkillPutRequest skillPutRequest){
        Skill skill = SkillService.updateSkillById(category_id, id, skillPutRequest.name, skillPutRequest.categoryId);
        return ResponseEntity.ok(skill);
    }
    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "No Skill with this ID in selected category was found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GlobalExceptionHandler.ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Not Found Error",
                                    summary = "Example of a 404 error response",
                                    value = "{\"error\": \"Skill with id 1 in category 1 not found\"}"
                            )))
    })
    @DeleteApiResponses
    @Operation(
            summary = "delete Skill with specified ID in selected category",
            description = "deletes Skill  with specified ID if exist, No content returns"
    )
    public ResponseEntity<Void> deleteSkill(@PathVariable Integer category_id, @PathVariable Integer id){
        SkillService.deleteSkillById(category_id, id);
        return ResponseEntity.noContent().build();
    }
    public record SkillPutRequest(String name, Integer categoryId){}
    public record SkillCreateInCategoryRequest(String name){}
}
