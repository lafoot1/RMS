package com.deloitte.rms.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.rms.persistence.core.entity.RefererEntity;

public interface ReferrerRepository extends JpaRepository<RefererEntity, Long> {
	RefererEntity findByEmpNo(Long empNo);
}
