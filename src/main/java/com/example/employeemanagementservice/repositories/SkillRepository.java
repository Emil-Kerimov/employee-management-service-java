package com.example.employeemanagementservice.repositories;

import com.example.employeemanagementservice.models.Skill;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class SkillRepository {
    private final Map<Integer, Skill> skills = new HashMap<>();
    private final AtomicInteger idSkillCounter = new AtomicInteger(1);

    public List<Skill> getAllSkillsInCategory(Integer categoryId) {
        return skills.values().stream().filter(s -> s.getCategoryId() == categoryId).collect(Collectors.toList());
    }

    public Skill getSkillByIdOrNull(Integer id,Integer categoryId) {
        return Optional.ofNullable(skills.get(id))
                .filter(skill -> Objects.equals(skill.getCategoryId(), categoryId))
                .orElse(null);
    }

    public boolean delete(Integer id) {
        return skills.remove(id) != null;
    }

    public Skill save(String name, Integer categoryId) {
        int newId = idSkillCounter.getAndIncrement();

        Skill skill = new Skill(newId, name, categoryId);
        skills.put(newId, skill);
        return skill;
    }
    public void saveUpdatedCategory(Integer id, Skill skill) {
        skills.put(id, skill);
    }
}
