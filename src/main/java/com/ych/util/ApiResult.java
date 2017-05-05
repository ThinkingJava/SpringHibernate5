package com.ych.util;


public class ApiResult<T> {

	private int code;
	private String msg;
	private T resultContent;
	private int number;
	
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
	public T getResultContent() {
		return resultContent;
	}
	public void setResultContent(T resultContent) {
		this.resultContent = resultContent;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	

	
}
