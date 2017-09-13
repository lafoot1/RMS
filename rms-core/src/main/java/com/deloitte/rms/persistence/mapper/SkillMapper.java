package com.deloitte.rms.persistence.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.deloitte.rms.api.Skill;
import com.deloitte.rms.persistence.core.entity.SkillEntity;

@Component
public class SkillMapper {
	public List<Skill> map(Collection<SkillEntity> entities) {
		List<Skill> skills = new ArrayList<>();
		for (SkillEntity skillEntity : entities) {
			skills.add(map(skillEntity));
		}
		
		return skills;
	}
	
	public Skill map(SkillEntity entity) {
		Skill skill = new Skill();
		BeanUtils.copyProperties(entity, skill);
		return skill;
	}
	
	public Set<SkillEntity> mapSkillEntities(Collection<Skill> skills) {
		Set<SkillEntity> entities = new HashSet<>();
		for (Skill skill : skills) {
			entities.add(map(skill));
		}
		
		return entities;
	}
	
	public SkillEntity map(Skill skill) {
		SkillEntity entity = new SkillEntity();
		BeanUtils.copyProperties(skill, entity);
		return entity;
	}
}
