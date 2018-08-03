package com.mobily.api.sms.entity;

import java.util.List;

/**
 * @author Mobily.ws
 * 
 */
public class AlphasSenderResponseData {
	private List<String> active;
	private List<String> notActive;
	private List<String> pending;

	/**
	 * @return list of Active sender names
	 */
	public List<String> getActive() {
		return this.active;
	}

	public void setActive(List<String> active) {
		this.active = active;
	}

	/**
	 * @return list of notActive sender names
	 */
	public List<String> getNotActive() {
		return this.notActive;
	}

	public void setNotActive(List<String> notActive) {
		this.notActive = notActive;
	}

	/**
	 * @return list of pending sender names
	 */
	public List<String> getPending() {
		return this.pending;
	}

	public void setPending(List<String> pending) {
		this.pending = pending;
	}
}
