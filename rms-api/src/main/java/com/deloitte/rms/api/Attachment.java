package com.deloitte.rms.api;

import java.io.Serializable;

import com.deloitte.rms.api.type.ContentType;

public class Attachment implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String mongoId;
	private Long referenceId;
	private ReferenceType referenceType;
	private String name;
	private long version;
	private ContentType contentType;
	private String extn;
	private byte[] content;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMongoId() {
		return mongoId;
	}
	public void setMongoId(String mongoId) {
		this.mongoId = mongoId;
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
