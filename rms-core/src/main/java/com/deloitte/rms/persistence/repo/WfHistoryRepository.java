package com.deloitte.rms.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.rms.persistence.core.entity.wf.WfHistoryEntity;

@Repository
public interface WfHistoryRepository extends JpaRepository<WfHistoryEntity, Long>{ }
