package com.mobily.api.sms.entity;

public class CommonResponseData {
	private String MessageAr;
	private String MessageEn;
	private String result;

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

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
