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
    private final AtomicInteger idCategoriesCounter = new AtomicInteger(1);

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

//TODO: look at exceptions, at the end rename, clear all, replace, delete
    //
    // TODO: exception handling in controller layer - no

    //employee controler logic approved,

    // put categoryby id  returns 404 if noy found(update), POST skills rewrites, PUT SKILL CATEGORIES BY ID CHANGES OBJECT BUT NOT MAP

    //if posts will create new id
    //then put should return true or false?
    // only get should throw 404 ??
}
