package com.deloitte.rms.core.exception;

public class RmsException extends Exception {
	private static final long serialVersionUID = 1L;
	private Throwable throwable;
	private String code;

	public RmsException(Throwable t) {
		this.throwable = t;
	}

	public RmsException(String msg) {
		setCode(code);
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
