package com.deloitte.rms.persistence.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deloitte.rms.persistence.core.entity.RequisitionEntity;

@Repository
public interface RequisitionRepository extends JpaRepository<RequisitionEntity, Long>{
	Page<RequisitionEntity> findByIdOrTitleIgnoreCaseLikeOrRequisitionNoIgnoreCaseLikeOrRecruiterNameIgnoreCaseLike(
			Long id, String title, String requisitionNo, String recruiterName, Pageable pageable);

	@Query("select r from RequisitionEntity r"
			+ " where r.id=:id "
			+ " or r.title like '%'||:query||'%' "
			+ " or UPPER(requisitionNo) like '%'||UPPER(:query)||'%' "
			+ " or UPPER(recruiterName) like '%'||UPPER(:query)||'%' ")
	Page<RequisitionEntity> globalSearch(@Param("id") Long id, @Param("query") String query, Pageable pageable);
}
