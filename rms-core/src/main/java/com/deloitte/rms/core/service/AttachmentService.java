package com.deloitte.rms.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.rms.api.Attachment;
import com.deloitte.rms.persistence.mapper.AttachmentDetailMapper;
import com.deloitte.rms.persistence.mongo.entity.AttachmentDetailEntity;
import com.deloitte.rms.persistence.repo.AttachmentDetailRepository;

@Service
public class AttachmentService {
	@Autowired AttachmentDetailRepository attachmentDetailRepository;
	@Autowired AttachmentDetailMapper attachmentDetailMapper;
	
	public Attachment findById(String id) {
		return attachmentDetailMapper.map(attachmentDetailRepository.findOne(id));
	}
	
	public List<Attachment> findByReferenceId(Long referenceId) {
		return attachmentDetailMapper.map(attachmentDetailRepository.findByReferenceId(referenceId), false);
	}
	
	public List<Attachment> saveAttachmentDetails(Collection<Attachment> attachments) {
		if (attachments.isEmpty()) {
			return Collections.emptyList();
		}
		
		List<AttachmentDetailEntity> entities = new ArrayList<>();
		attachments.forEach(attachment -> entities.add(attachmentDetailMapper.map(attachment)));
		
		return attachmentDetailMapper.map(attachmentDetailRepository.save(entities), false);
	} 
}
