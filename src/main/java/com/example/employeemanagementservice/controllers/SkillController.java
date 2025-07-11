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
    public List<Skill> getSkillsInCategory(@PathVariable Integer categoryId){
        return SkillService.getSkillsInCategory(categoryId);
    }
    @GetMapping("/{categoryId}/skills/{id}")
    public Skill getSkillById(@PathVariable Integer categoryId, @PathVariable Integer id){
        return SkillService.getSkillById(categoryId, id);
    }

    @PostMapping
    public ResponseEntity<Skill> createSkill(@PathVariable Integer categoryId, @RequestBody SkillRequest skillRequest){
        Skill skill = SkillService.createSkill(categoryId, skillRequest.name);
        return ResponseEntity.ok(skill);
    }
    @PutMapping("/{categoryId}/skills/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable Integer categoryId, @PathVariable Integer id, @RequestBody SkillRequest skillRequest){
        Skill skill = SkillService.updateSkillById(categoryId, id, skillRequest.name, skillRequest.categoryId);
        return ResponseEntity.ok(skill);
    }
    @DeleteMapping("/{categoryId}/skills/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Integer categoryId, @PathVariable Integer id){
        SkillService.deleteSkillById(categoryId, id);
        return ResponseEntity.noContent().build();
    }
    public record SkillRequest(String name, Integer categoryId){}
}
