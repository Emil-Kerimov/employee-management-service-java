package com.example.employeemanagementservice.services;

import com.example.employeemanagementservice.exceptions.CategoryNotFoundException;
import com.example.employeemanagementservice.exceptions.SkillNotFoundException;
import com.example.employeemanagementservice.models.Skill;
import com.example.employeemanagementservice.models.SkillCategory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkillCategoriesService {
    private final Map<Integer, SkillCategory> categories = new HashMap<>();
    private final Map<Integer, Skill> skills = new HashMap<>();

    public List<SkillCategory> getAllCategories() {
        return new ArrayList<>(categories.values());
    }

    public SkillCategory getCategoryById(Integer id) {
        return Optional.ofNullable(categories.get(id))
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }


    public SkillCategory createCategory(Integer id, String name, String area) {
        SkillCategory skillCategory = new SkillCategory(id, name, area);
        categories.put(skillCategory.getId(), skillCategory);
        return skillCategory;
    }

    public SkillCategory updateCategory(Integer id, Integer id1, String name, String area) {
        SkillCategory skillCategory = Optional.ofNullable(categories.get(id)).
                orElseThrow(() -> new CategoryNotFoundException(id));
            skillCategory.setId(id1);
            skillCategory.setName(name);
            skillCategory.setArea(area);
        return skillCategory;
    }

    public void delete(Integer id) {
        if(categories.remove(id) == null){
            throw new CategoryNotFoundException(id);
        }
    }
}
