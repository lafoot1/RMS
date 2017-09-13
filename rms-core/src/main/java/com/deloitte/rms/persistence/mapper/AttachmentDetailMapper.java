package com.deloitte.rms.persistence.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.deloitte.rms.api.Attachment;
import com.deloitte.rms.persistence.mongo.entity.AttachmentDetailEntity;

@Component
public class AttachmentDetailMapper {
	public List<Attachment> map(Collection<AttachmentDetailEntity> entities, boolean mapContent) {
		List<Attachment> attachments = new ArrayList<>();
		entities.forEach(entity -> {
			Attachment attachment = new Attachment();
			if (!mapContent) {
				entity.setContent(null);
			}
			BeanUtils.copyProperties(entity, attachment);
			attachment.setId(entity.getId());
			attachments.add(attachment);
		});
		return attachments;
	}
	
	public List<AttachmentDetailEntity> mapList(Collection<Attachment> attachments) {
		List<AttachmentDetailEntity> entities = new ArrayList<>();
		attachments.forEach(attachment -> {
			AttachmentDetailEntity entity = new AttachmentDetailEntity();
			BeanUtils.copyProperties(attachment, entity);
			entities.add(entity);
		});
		return entities;
	}
	
	public Attachment map(AttachmentDetailEntity entity) {
		Attachment attachment = new Attachment();
		BeanUtils.copyProperties(entity, attachment);
		attachment.setMongoId(entity.getId());
		return attachment;
	}
	
	public AttachmentDetailEntity map(Attachment attachment) {
		AttachmentDetailEntity entity = new AttachmentDetailEntity();
		BeanUtils.copyProperties(attachment, entity);
		entity.setReferenceType(attachment.getReferenceType());
		return entity;
	}
}
