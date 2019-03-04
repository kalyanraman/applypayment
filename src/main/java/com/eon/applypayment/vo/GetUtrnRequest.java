package com.eon.applypayment.vo;

public class GetUtrnRequest {

	public String getPaygProductId() {
		return paygProductId;
	}
	public void setPaygProductId(String paygProductId) {
		this.paygProductId = paygProductId;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	private String paygProductId;
	private float value;

	
}
