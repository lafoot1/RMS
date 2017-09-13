package com.deloitte.rms.persistence.mongo.entity;

import java.io.Serializable;

import javax.persistence.Version;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.deloitte.rms.api.ReferenceType;
import com.deloitte.rms.api.type.ContentType;

@Document(collection = "canditateAttachments")
public class AttachmentDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String _id;
	private Long referenceId;
	private ReferenceType referenceType;
	private String name;
	@Version private long version;
	private ContentType contentType;
	private String extn;
	private byte[] content;
	
	public String getId() {
		return _id;
	}
	public void setId(String id) {
		this._id = id;
	}
	public Long getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
	}
	public ReferenceType getReferenceType() {
		return referenceType;
	}
	public void setReferenceType(ReferenceType referenceType) {
		this.referenceType = referenceType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public ContentType getContentType() {
		return contentType;
	}
	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}
	public String getExtn() {
		return extn;
	}
	public void setExtn(String extn) {
		this.extn = extn;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
}
