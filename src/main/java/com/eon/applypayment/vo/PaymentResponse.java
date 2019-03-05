package com.eon.applypayment.vo;

public class PaymentResponse {

	public PaymentResponse(int code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	private int code;
	private String description;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
