package com.deloitte.rms.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.rms.persistence.core.entity.wf.WfMasterEntity;

@Repository
public interface WorkflowMasterRepo extends JpaRepository<WfMasterEntity, Long>{
	List<WfMasterEntity> findByWfStatusId(Long wfStatusId);
}
