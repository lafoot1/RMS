package com.deloitte.rms.core.exception;

public class RmsWorkflowException extends RmsException {
	private static final long serialVersionUID = 1L;

	public RmsWorkflowException(Throwable t) {
		super(t);
	}

	public RmsWorkflowException(String code) {
		super(code);
	}
}
