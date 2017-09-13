package com.deloitte.rms.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.rms.persistence.core.entity.InterviewEntity;

@Repository
public interface InterviewRepository extends JpaRepository<InterviewEntity, Long> { }
