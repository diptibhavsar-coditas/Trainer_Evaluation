package com.example.SpringBootProject.response;

public class ApiResponse<T> {

	private int status;
	private String mess;
	private T data;
	private long timestamp;
	
	public ApiResponse(int status,String mess,T data) {
		this.status =status;
		this.mess=mess;
		this.data= data;
		this.timestamp=System.currentTimeMillis();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}
