package com.bw.web.todo.dto;

public class JsonResponse {
	private boolean success=true;
	private String message;
	private Object data;
	
	public JsonResponse() {
	}
	
	public JsonResponse(boolean success, Object data) {
		super();
		this.success = success;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
