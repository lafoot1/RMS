package com.deloitte.rms.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.rms.persistence.core.entity.AuditEntity;

public interface AuditRepository extends JpaRepository<AuditEntity, Long> { }
