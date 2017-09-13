package com.deloitte.rms.persistence.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.deloitte.rms.persistence.mongo.entity.AttachmentDetailEntity;

public interface AttachmentDetailRepository extends MongoRepository<AttachmentDetailEntity, String> {
	List<AttachmentDetailEntity> findByReferenceId(Long referenceId);	
}
