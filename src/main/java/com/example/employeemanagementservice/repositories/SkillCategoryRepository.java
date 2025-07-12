package com.example.employeemanagementservice.repositories;

import com.example.employeemanagementservice.models.SkillCategory;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class SkillCategoryRepository {
    private final Map<Integer, SkillCategory> categories = new HashMap<>();
    private final AtomicInteger idCategoriesCounter = new AtomicInteger(1);

    public List<SkillCategory> getAll() {
        return new ArrayList<>(categories.values());
    }

    public SkillCategory getCategoryById(Integer id) {
        return categories.get(id);
    }

    public SkillCategory save(String name, String area) {
        int newId = idCategoriesCounter.getAndIncrement();
        SkillCategory skillCategory = new SkillCategory(newId, name, area);
        categories.put(newId, skillCategory);
        return skillCategory;
    }

    public boolean delete(Integer id) {
        return categories.remove(id) != null;
    }

    public void saveUpdatedCategory(Integer id, SkillCategory skillCategory) {
        categories.put(id, skillCategory);
    }
}
