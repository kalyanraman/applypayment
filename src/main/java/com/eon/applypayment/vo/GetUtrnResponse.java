package com.eon.applypayment.vo;

public class GetUtrnResponse {

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
	public Long getUtrn() {
		return utrn;
	}
	public void setUtrn(Long utrn) {
		this.utrn = utrn;
	}
	private String paygProductId;
	private float value;
	private Long utrn;

}
