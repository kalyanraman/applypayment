package com.eon.applypayment.camel;

import java.io.IOException;
import java.util.Random;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import com.eon.applypayment.util.ApplyPaymentStatus;
import com.eon.applypayment.util.Utilz;
import com.eon.applypayment.vo.ApplyPaymentStatusEvent;
import com.eon.applypayment.vo.PostBalanceRequest;
import com.eon.applypayment.vo.PostUtrnRequest;
import com.eon.applypayment.vo.TransactionDetail;
import com.eon.applypayment.vo.UtrnAppliedConfirmationRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Mapper {

	public ApplyPaymentStatusEvent mapPaymentUtrnRequested(TransactionDetail transactionDetail)
			throws JsonParseException, JsonMappingException, IOException {

		ApplyPaymentStatusEvent applyPaymentStatusEvent = new ApplyPaymentStatusEvent();
		applyPaymentStatusEvent.setEventDateTime(Utilz.getCurrentDate());
		applyPaymentStatusEvent.setEventType(ApplyPaymentStatus.PaymentUtrnRequested.toString());
		applyPaymentStatusEvent.setPaygProductId(transactionDetail.getPaygProductId());
		applyPaymentStatusEvent.setReason("Payment");
		applyPaymentStatusEvent.setTransactionId(getTransactionId());
		applyPaymentStatusEvent.setValue("" + transactionDetail.getValue());
		return applyPaymentStatusEvent;

	}

	public ApplyPaymentStatusEvent mapUtrnReceived(PostUtrnRequest postUtrnRequest)
			throws JsonParseException, JsonMappingException, IOException {
		ApplyPaymentStatusEvent applyPaymentStatusEvent = new ApplyPaymentStatusEvent();
		applyPaymentStatusEvent.setEventDateTime(Utilz.getCurrentDate());
		applyPaymentStatusEvent.setEventType(ApplyPaymentStatus.UtrnReceived.toString());
		applyPaymentStatusEvent.setPaygProductId(postUtrnRequest.getPaygProductId());
		applyPaymentStatusEvent.setReason("Payment");
		applyPaymentStatusEvent.setUtrn(postUtrnRequest.getUtrn());
		applyPaymentStatusEvent.setTransactionId(postUtrnRequest.getTransactionId());
		applyPaymentStatusEvent.setValue("" + postUtrnRequest.getValue());
		return applyPaymentStatusEvent;
	}

	public ApplyPaymentStatusEvent mapApplyPaymentUtrnRequested(PostUtrnRequest postUtrnRequest)
			throws JsonParseException, JsonMappingException, IOException {
		ApplyPaymentStatusEvent applyPaymentStatusEvent = new ApplyPaymentStatusEvent();
		applyPaymentStatusEvent.setEventDateTime(Utilz.getCurrentDate());
		applyPaymentStatusEvent.setEventType(ApplyPaymentStatus.ApplyUtrnRequested.toString());
		applyPaymentStatusEvent.setPaygProductId(postUtrnRequest.getPaygProductId());
		applyPaymentStatusEvent.setReason("Payment");
		applyPaymentStatusEvent.setTransactionId(postUtrnRequest.getTransactionId());
		applyPaymentStatusEvent.setUtrn(postUtrnRequest.getUtrn());
		applyPaymentStatusEvent.setValue(String.valueOf(postUtrnRequest.getValue()));
		return applyPaymentStatusEvent;
	}

	public ApplyPaymentStatusEvent mapUtrnApplied(UtrnAppliedConfirmationRequest utrnAppliedConfirmation)
			throws JsonParseException, JsonMappingException, IOException {
		ApplyPaymentStatusEvent applyPaymentStatusEvent = new ApplyPaymentStatusEvent();
		applyPaymentStatusEvent.setEventDateTime(Utilz.getCurrentDate());
		applyPaymentStatusEvent.setEventType(ApplyPaymentStatus.UtrnApplied.toString());
		applyPaymentStatusEvent.setPaygProductId(utrnAppliedConfirmation.getPaygProductId());
		applyPaymentStatusEvent.setReason("Payment");
		applyPaymentStatusEvent.setUtrn(utrnAppliedConfirmation.getUtrn());
		applyPaymentStatusEvent.setTransactionId(utrnAppliedConfirmation.getTransactionId());
		applyPaymentStatusEvent.setValue("" + utrnAppliedConfirmation.getValue());
		return applyPaymentStatusEvent;
	}

	public ApplyPaymentStatusEvent mapPostMeterBalance(PostBalanceRequest postBalanceRequest)
			throws JsonParseException, JsonMappingException, IOException {
		ApplyPaymentStatusEvent applyPaymentStatusEvent = new ApplyPaymentStatusEvent();
		applyPaymentStatusEvent.setEventDateTime(Utilz.getCurrentDate());
		applyPaymentStatusEvent.setEventType(ApplyPaymentStatus.MeterBalanceReceived.toString());
		applyPaymentStatusEvent.setPaygProductId(postBalanceRequest.getPaygProductId());
		applyPaymentStatusEvent.setReason("Payment");
		applyPaymentStatusEvent.setMeterBalanceDateTime(Utilz.getCurrentDate());
		applyPaymentStatusEvent.setMeterBalance(postBalanceRequest.getMeterBalance());
		applyPaymentStatusEvent.setTransactionId(postBalanceRequest.getTransactionId());
		return applyPaymentStatusEvent;
	}

	public String getPaygProductId(TransactionDetail transactionDetail) {
		int year = LocalDate.now().getYear();
		int month = LocalDate.now().getMonthOfYear();
		String id = new StringBuffer().append(transactionDetail.getPaygProductId()).append("::").append("E")
				.append("::").append(year).append("::").append(month).toString();

		return id;
	}

	public static String getTransactionId() {
		Random rnd = new Random();
		int number = rnd.nextInt(9999999);
		return String.format("%07d", number);
	}

//	public mapGetUtrnResponseTo
}