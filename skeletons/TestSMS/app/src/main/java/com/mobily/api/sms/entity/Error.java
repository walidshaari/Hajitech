package com.mobily.api.sms.entity;

public class Error {
	public Error(String messageAr, String messageEn, int errorCode) {

		this.MessageAr = messageAr;
		this.MessageEn = messageEn;
		this.ErrorCode = errorCode;
	}

	private String MessageAr;
	private String MessageEn;
	private int ErrorCode;

	public String getMessageAr() {
		return this.MessageAr;
	}

	public void setMessageAr(String messageAr) {
		this.MessageAr = messageAr;
	}

	public String getMessageEn() {
		return this.MessageEn;
	}

	public void setMessageEn(String messageEn) {
		this.MessageEn = messageEn;
	}

	public int getErrorCode() {
		return this.ErrorCode;
	}

	public void setErrorCode(int ErrorCode) {
		this.ErrorCode = ErrorCode;
	}
}
