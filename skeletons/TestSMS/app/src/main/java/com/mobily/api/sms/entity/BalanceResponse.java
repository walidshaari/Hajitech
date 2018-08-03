package com.mobily.api.sms.entity;

/**
 * @author Mobily.ws
 * 
 */
public class BalanceResponse {
	private BalanceResponseData Data;
	private Error Error;
	private String ResponseStatus;
	private int status;

	public BalanceResponseData getData() {
		return this.Data;
	}

	public void setData(BalanceResponseData data) {
		this.Data = data;
	}

	public Error getError() {
		return this.Error;
	}

	public void setError(Error error) {
		this.Error = error;
	}

	public String getResponseStatus() {
		return this.ResponseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.ResponseStatus = responseStatus;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
