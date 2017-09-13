package com.deloitte.rms.persistence.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.rms.persistence.core.entity.CandidateEntity;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity, Long> {
	
	CandidateEntity findByEmail(String email);
	
	@EntityGraph(value = "CandidateEntity.detail", type = EntityGraphType.LOAD)
	Page<CandidateEntity> findByIdOrFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(Long id, String firstName, String lastName, Pageable pageable);
}
