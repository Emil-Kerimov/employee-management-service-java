package com.example.employeemanagementservice.services;

import com.example.employeemanagementservice.exceptions.SkillNotFoundException;
import com.example.employeemanagementservice.models.Skill;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class SkillService {
    private final Map<Integer, Skill> skills = new HashMap<>();
    private final AtomicInteger idSkillCounter = new AtomicInteger(1);
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
