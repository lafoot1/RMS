package com.deloitte.rms.api.type;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = EnumDeserializer.ContentTypeDeserializer.class)
public enum ContentType implements Serializable {
	PDF("pdf", ""),
	WORD2003("doc", "2003"),
	WORD("docx", "2008"),
	UNKNOWN("", "");
	
	private String contentType;
	private String version;
	
	private ContentType(String contentType, String version) {
		this.contentType = contentType;
		this.version = version;
	}
	
	public String getContentType() {
		return contentType;
	}

	public String getVersion() {
		return version;
	}

	public static ContentType getByType(String type) {
		for (ContentType contentType : ContentType.values()) {
			if (type.equalsIgnoreCase(contentType.getContentType())) {
				return contentType;
			}
		}
		
		return ContentType.UNKNOWN;
	}
}
