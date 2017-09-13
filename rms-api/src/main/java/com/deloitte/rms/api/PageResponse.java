package com.deloitte.rms.api;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.domain.Page;

public class PageResponse<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int pages;
	private int currentPage;
	private long totalRecords;
	private Collection<T> collection;
	
	public PageResponse(Collection<T> collection, int totalRecords, int pages, int currentPage) {
		this.collection = collection;
		this.totalRecords = totalRecords;
		this.pages = pages;
		this.currentPage = currentPage;
	}
	
	public PageResponse(Page<T> page) {
		this.collection = page.getContent();
		this.totalRecords = page.getTotalElements();
		this.pages = page.getTotalPages();
		this.currentPage = page.getNumber();
	}
	
	public PageResponse(Collection<T> collection, Page<?> page) {
		this.collection = collection;
		this.totalRecords = page.getTotalElements();
		this.pages = page.getTotalPages();
		this.currentPage = page.getNumber();
	}
	
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Collection<T> getCollection() {
		return collection;
	}
	public void setCollection(Collection<T> collection) {
		this.collection = collection;
	}
}
