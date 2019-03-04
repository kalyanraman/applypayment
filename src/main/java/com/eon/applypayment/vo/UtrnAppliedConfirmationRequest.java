package com.eon.applypayment.vo;

public class UtrnAppliedConfirmationRequest {

	private String paygProductId;
	private String transactionId;
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
	public String getUtrn() {
		return utrn;
	}
	public void setUtrn(String utrn) {
		this.utrn = utrn;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	private String utrn;
	private float value;

}
