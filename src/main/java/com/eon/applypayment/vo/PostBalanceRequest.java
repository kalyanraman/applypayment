package com.eon.applypayment.vo;

public class PostBalanceRequest {

	private String paygProductId;
	private String transactionId;
	private String meterBalanceDate;
	public String getPaygProductId() {
		return paygProductId;
	}
	public void setPaygProductId(String paygProductId) {
		this.paygProductId = paygProductId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getMeterBalanceDate() {
		return meterBalanceDate;
	}
	public void setMeterBalanceDate(String meterBalanceDate) {
		this.meterBalanceDate = meterBalanceDate;
	}
	public float getMeterBalance() {
		return meterBalance;
	}
	public void setMeterBalance(float meterBalance) {
		this.meterBalance = meterBalance;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	private float meterBalance;
	private String reason;

}
