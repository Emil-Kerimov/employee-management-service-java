package com.example.employeemanagementservice.controllers;

import com.example.employeemanagementservice.models.Skill;
import com.example.employeemanagementservice.services.EmployeeSkillService;
import com.example.employeemanagementservice.services.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill-categories/{category_id}/skills")
public class SkillController {
    private final com.example.employeemanagementservice.services.SkillService SkillService;

    public SkillController(SkillService SkillService) {
        this.SkillService = SkillService;
    }

    @GetMapping
    public List<Skill> getSkillsInCategory(@PathVariable Integer category_id){
        return SkillService.getSkillsInCategory(category_id);
    }
    @GetMapping("/{id}")
    public Skill getSkillById(@PathVariable Integer category_id, @PathVariable Integer id){
        return SkillService.getSkillById(category_id, id);
    }

    @PostMapping
    public ResponseEntity<Skill> createSkill(@PathVariable Integer category_id, @RequestBody SkillCreateInCategoryRequest skillCreateInCategoryRequest){
        Skill skill = SkillService.createSkill(category_id, skillCreateInCategoryRequest.name);
        return ResponseEntity.ok(skill);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable Integer category_id, @PathVariable Integer id, @RequestBody SkillPutRequest skillPutRequest){
        Skill skill = SkillService.updateSkillById(category_id, id, skillPutRequest.name, skillPutRequest.categoryId);
        return ResponseEntity.ok(skill);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Integer category_id, @PathVariable Integer id){
        SkillService.deleteSkillById(category_id, id);
        return ResponseEntity.noContent().build();
    }
    public record SkillPutRequest(String name, Integer categoryId){}
    public record SkillCreateInCategoryRequest(String name){}
}
