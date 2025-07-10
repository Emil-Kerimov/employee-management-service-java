package com.example.employeemanagementservice.services;

import com.example.employeemanagementservice.exceptions.CategoryNotFoundException;
import com.example.employeemanagementservice.exceptions.SkillNotFoundException;
import com.example.employeemanagementservice.models.Skill;
import com.example.employeemanagementservice.models.SkillCategory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class SkillCategoriesService {
    private final Map<Integer, SkillCategory> categories = new HashMap<>();
    private final Map<Integer, Skill> skills = new HashMap<>();
    private final AtomicInteger idCategoriesCounter = new AtomicInteger(1);
    private final AtomicInteger idSkillCounter = new AtomicInteger(1);

    public List<SkillCategory> getAllCategories() {
        return new ArrayList<>(categories.values());
    }

    public SkillCategory getCategoryById(Integer id) {
        return Optional.ofNullable(categories.get(id))
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }


    public SkillCategory createCategory(String name, String area) {
        int newId = idCategoriesCounter.getAndIncrement();
        SkillCategory skillCategory = new SkillCategory(newId, name, area);
        categories.put(newId, skillCategory);
        return skillCategory;
    }

    public SkillCategory updateCategory(Integer id, String name, String area) {
        SkillCategory skillCategory = Optional.ofNullable(categories.get(id)).
                orElseThrow(() -> new CategoryNotFoundException(id));
            skillCategory.setName(name);
            skillCategory.setArea(area);
            categories.put(id, skillCategory);
        return skillCategory;
    }

    public void delete(Integer id) {
        if(categories.remove(id) == null){
            throw new CategoryNotFoundException(id);
        }
    }

    public List<Skill> getSkillsInCategory(Integer categoryId) {
        getCategoryById(categoryId);

        return skills.values().stream().filter(s -> s.getCategoryId() == categoryId).collect(Collectors.toList());
    }

    public Skill getSkillById(Integer categoryId, Integer id) {
        getCategoryById(categoryId);

        return Optional.ofNullable(skills.get(id))
                .orElseThrow(() -> new SkillNotFoundException(id));
    }

    public Skill createSkill(Integer CategoryId, String name) {
        getCategoryById(CategoryId);
        int newId = idSkillCounter.getAndIncrement();
        Skill skill = new Skill(newId, name, CategoryId);
        skills.put(newId, skill);
        return skill;
    }

    public Skill updateSkillById(Integer categoryId, Integer id, String name, Integer categoryId1) {
        getCategoryById(categoryId);
        Skill skill = Optional.ofNullable(skills.get(id))
                .orElse(new Skill());
        skill.setName(name);
        skill.setCategoryId(categoryId1);
        skills.put(id, skill);
        return skill;
    }

    public void deleteSkillById(Integer categoryId, Integer id) {
        getCategoryById(categoryId);
        if (skills.remove(id) == null) {
            throw new SkillNotFoundException(id);
        }
    }

}
