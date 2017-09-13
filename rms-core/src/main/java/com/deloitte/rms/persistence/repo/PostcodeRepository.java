package com.deloitte.rms.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.rms.persistence.core.entity.wf.PostalCodeEntity;

@Repository
public interface PostcodeRepository extends JpaRepository<PostalCodeEntity, Long>{ 
	PostalCodeEntity findByPincode(Integer pincode);
}
