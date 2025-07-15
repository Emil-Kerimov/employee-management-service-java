package com.example.employeemanagementservice.services;

import com.example.employeemanagementservice.exceptions.CategoryNotFoundException;
import com.example.employeemanagementservice.exceptions.SkillNotFoundException;
import com.example.employeemanagementservice.models.Skill;
import com.example.employeemanagementservice.repositories.SkillCategoryRepository;
import com.example.employeemanagementservice.repositories.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {
    private final SkillCategoryRepository skillCategoryRepository;
    private final SkillRepository skillRepository;

    public SkillService(SkillCategoryRepository skillCategoryRepository, SkillRepository skillRepository) {
        this.skillCategoryRepository = skillCategoryRepository;
        this.skillRepository = skillRepository;
    }
    public void checkCategoryAvailability(Integer categoryId){
        if(skillCategoryRepository.getCategoryById(categoryId) == null){
            throw new CategoryNotFoundException(categoryId);
        }
    }

    public List<Skill> getSkillsInCategory(Integer categoryId) {
        checkCategoryAvailability(categoryId);

        return skillRepository.getAllSkillsInCategory(categoryId);
    }

    public Skill getSkillById(Integer categoryId, Integer id) {
        checkCategoryAvailability(categoryId);

        return Optional.ofNullable(skillRepository.getSkillByIdOrNull(id, categoryId))
                .orElseThrow(() -> new SkillNotFoundException(id));
    }

    public Skill createSkill(Integer categoryId, String name) {
        checkCategoryAvailability(categoryId);
        return skillRepository.save(name, categoryId);
    }

    public Skill updateSkillById(Integer categoryId, Integer id, String name, Integer categoryId1) {
        checkCategoryAvailability(categoryId);
        checkCategoryAvailability(categoryId1);
        Skill skill = Optional.ofNullable(skillRepository.getSkillByIdOrNull(id, categoryId))
                .orElseThrow(() -> new SkillNotFoundException(id));
        skill.setName(name);
        skill.setCategoryId(categoryId1);
        skillRepository.saveUpdatedCategory(id, skill);
        return skill;
    }

    public void deleteSkillById(Integer categoryId, Integer id) {
        checkCategoryAvailability(categoryId);
        if (!skillRepository.delete(id)) {
            throw new SkillNotFoundException(id);
        }
    }
}
