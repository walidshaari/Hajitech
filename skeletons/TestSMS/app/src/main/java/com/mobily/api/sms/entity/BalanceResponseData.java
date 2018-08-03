package com.mobily.api.sms.entity;

/**
 * @author Mobily.ws
 * 
 */
public class BalanceResponseData {

	private Balance balance;

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}

	public class Balance {
		private String current;
		private String total;

		public String getCurrentBalance() {
			return this.current;
		}

		public void setCurrentBalance(String messageAr) {
			this.current = messageAr;
		}

		public String getTotalBalance() {
			return this.total;
		}

		public void setTotalBalance(String messageEn) {
			this.total = messageEn;
		}

	}

}
