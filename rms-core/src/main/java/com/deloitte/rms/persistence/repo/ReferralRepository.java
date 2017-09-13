package com.deloitte.rms.persistence.repo;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deloitte.rms.persistence.core.entity.ReferralEntity;

@Repository
public interface ReferralRepository extends JpaRepository<ReferralEntity, Long> {
	@Query("select r from ReferralEntity r"
			+ " where r.id=:id "
			+ " or r.candidate.rmsId=:id "
			+ " or r.wfStatus.wfStatus in (:statuses) "
			+ " or r.wfStatus.wfSubstatus in (:subStatuses) "
			+ " or UPPER(r.candidate.firstName) like '%'||UPPER(:query)||'%' "
			+ " or UPPER(r.candidate.lastName) like '%'||UPPER(:query)||'%' "
			+ " or UPPER(r.requisition.recruiterName) like '%'||UPPER(:query)||'%' "
			+ " or UPPER(r.requisition.requisitionNo) like '%'||UPPER(:query)||'%' ")
	Page<ReferralEntity> globalSearch(
			@Param("id") Long id,
			@Param("statuses") Collection<String> statuses,
			@Param("subStatuses") Collection<String> subStatuses,
			@Param("query") String query,
			Pageable pageable);
}
