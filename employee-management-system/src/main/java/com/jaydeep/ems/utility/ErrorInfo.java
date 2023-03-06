package com.jaydeep.ems.utility;

import java.time.LocalDateTime;

public class ErrorInfo {
	private int code;
	private String msg;
	private LocalDateTime timeStamp;
	
	public ErrorInfo(int code, String msg, LocalDateTime timeStamp) {
		super();
		this.code = code;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}

	public ErrorInfo() {
		super();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "ErrorInfo [code=" + code + ", msg=" + msg + ", timeStamp=" + timeStamp + "]";
	}
	
	
	
}
