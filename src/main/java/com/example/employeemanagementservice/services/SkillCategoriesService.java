package com.example.employeemanagementservice.services;

import com.example.employeemanagementservice.exceptions.CategoryNotFoundException;
import com.example.employeemanagementservice.models.SkillCategory;
import com.example.employeemanagementservice.repositories.SkillCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkillCategoriesService {
    SkillCategoryRepository skillCategoryRepository;

    public SkillCategoriesService(SkillCategoryRepository skillCategoryRepository) {
        this.skillCategoryRepository = skillCategoryRepository;
    }

    public List<SkillCategory> getAllCategories() {
        return new ArrayList<>(skillCategoryRepository.getAll());
    }

    public SkillCategory getCategoryById(Integer id) {
        return Optional.ofNullable(skillCategoryRepository.getCategoryById(id))
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }


    public SkillCategory createCategory(String name, String area) {
        return skillCategoryRepository.save(name, area);
    }

    public SkillCategory updateCategory(Integer id, String name, String area) {
        SkillCategory skillCategory = Optional.ofNullable(skillCategoryRepository.getCategoryById(id)).
                orElseThrow(() -> new CategoryNotFoundException(id));
            skillCategory.setName(name);
            skillCategory.setArea(area);
            skillCategoryRepository.saveUpdatedCategory(id, skillCategory);
        return skillCategory;
    }

    public void delete(Integer id) {
        if(!skillCategoryRepository.delete(id)){
            throw new CategoryNotFoundException(id);
        }
    }

}
