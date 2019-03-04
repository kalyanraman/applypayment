package com.eon.applypayment.camel;

import java.io.IOException;
import java.util.Random;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import com.eon.applypayment.util.ApplyPaymentStatus;
import com.eon.applypayment.util.Utilz;
import com.eon.applypayment.vo.ApplyPaymentStatusEvent;
import com.eon.applypayment.vo.ApplyPaymentStatusEvents;
import com.eon.applypayment.vo.PostBalanceRequest;
import com.eon.applypayment.vo.PostUtrnRequest;
import com.eon.applypayment.vo.TransactionDetail;
import com.eon.applypayment.vo.UtrnAppliedConfirmationRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Mapper {

	public String mapPaymentUtrnRequested(TransactionDetail transactionDetail)
			throws JsonParseException, JsonMappingException, IOException {
		ApplyPaymentStatusEvents applyPaymentStatusEvents = new ApplyPaymentStatusEvents();
		ApplyPaymentStatusEvent applyPaymentStatusEvent = new ApplyPaymentStatusEvent();
		applyPaymentStatusEvent.setEventDateTime(Utilz.getCurrentDate());
		applyPaymentStatusEvent.setEventType(ApplyPaymentStatus.PaymentUtrnRequested.toString());
		applyPaymentStatusEvent.setPaygProductId(transactionDetail.getPaygProductId());
		applyPaymentStatusEvent.setReason("Payment");
		applyPaymentStatusEvent.setTransactionId(getTransactionId());
		applyPaymentStatusEvent.setValue("" + transactionDetail.getValue());
		applyPaymentStatusEvents.setId(getId(transactionDetail.getPaygProductId()));
		applyPaymentStatusEvents.setApplyPaymentStatusEvent(applyPaymentStatusEvent);
		ObjectMapper Obj = new ObjectMapper();

		return Obj.writeValueAsString(applyPaymentStatusEvents);

	}

	public String mapUtrnReceived(PostUtrnRequest postUtrnRequest)
			throws JsonParseException, JsonMappingException, IOException {
		ApplyPaymentStatusEvents applyPaymentStatusEvents = new ApplyPaymentStatusEvents();
		ApplyPaymentStatusEvent applyPaymentStatusEvent = new ApplyPaymentStatusEvent();
		applyPaymentStatusEvent.setEventDateTime(Utilz.getCurrentDate());
		applyPaymentStatusEvent.setEventType(ApplyPaymentStatus.UtrnReceived.toString());
		applyPaymentStatusEvent.setPaygProductId(postUtrnRequest.getPaygProductId());
		applyPaymentStatusEvent.setReason("Payment");
		applyPaymentStatusEvent.setUtrn(postUtrnRequest.getUtrn());
		applyPaymentStatusEvent.setValue("" + postUtrnRequest.getValue());
		applyPaymentStatusEvents.setId(getId(postUtrnRequest.getPaygProductId()));
		applyPaymentStatusEvents.setApplyPaymentStatusEvent(applyPaymentStatusEvent);
		ObjectMapper Obj = new ObjectMapper();
		return Obj.writeValueAsString(applyPaymentStatusEvents);
	}

	public String mapApplyPaymentUtrnRequested(PostUtrnRequest postUtrnRequest)
			throws JsonParseException, JsonMappingException, IOException {
		ApplyPaymentStatusEvents applyPaymentStatusEvents = new ApplyPaymentStatusEvents();
		ApplyPaymentStatusEvent applyPaymentStatusEvent = new ApplyPaymentStatusEvent();
		applyPaymentStatusEvent.setEventDateTime(Utilz.getCurrentDate());
		applyPaymentStatusEvent.setEventType(ApplyPaymentStatus.ApplyUtrnRequested.toString());
		applyPaymentStatusEvent.setPaygProductId(postUtrnRequest.getPaygProductId());
		applyPaymentStatusEvent.setReason("Payment");
		applyPaymentStatusEvent.setUtrn(postUtrnRequest.getUtrn());
		applyPaymentStatusEvents.setId(getId(postUtrnRequest.getPaygProductId()));
		applyPaymentStatusEvents.setApplyPaymentStatusEvent(applyPaymentStatusEvent);
		ObjectMapper Obj = new ObjectMapper();
		return Obj.writeValueAsString(applyPaymentStatusEvents);
	}

	public String mapUtrnApplied(UtrnAppliedConfirmationRequest utrnAppliedConfirmation)
			throws JsonParseException, JsonMappingException, IOException {
		ApplyPaymentStatusEvents applyPaymentStatusEvents = new ApplyPaymentStatusEvents();
		ApplyPaymentStatusEvent applyPaymentStatusEvent = new ApplyPaymentStatusEvent();
		applyPaymentStatusEvent.setEventDateTime(Utilz.getCurrentDate());
		applyPaymentStatusEvent.setEventType(ApplyPaymentStatus.UtrnApplied.toString());
		applyPaymentStatusEvent.setPaygProductId(utrnAppliedConfirmation.getPaygProductId());
		applyPaymentStatusEvent.setReason("Payment");
		applyPaymentStatusEvent.setUtrn(utrnAppliedConfirmation.getUtrn());
		applyPaymentStatusEvent.setValue("" + utrnAppliedConfirmation.getValue());
		applyPaymentStatusEvents.setId(getId(utrnAppliedConfirmation.getPaygProductId()));
		applyPaymentStatusEvents.setApplyPaymentStatusEvent(applyPaymentStatusEvent);
		ObjectMapper Obj = new ObjectMapper();
		return Obj.writeValueAsString(applyPaymentStatusEvents);
	}

	public String mapPostMeterBalance(PostBalanceRequest postBalanceRequest)
			throws JsonParseException, JsonMappingException, IOException {
		ApplyPaymentStatusEvents applyPaymentStatusEvents = new ApplyPaymentStatusEvents();
		ApplyPaymentStatusEvent applyPaymentStatusEvent = new ApplyPaymentStatusEvent();
		applyPaymentStatusEvent.setEventDateTime(Utilz.getCurrentDate());
		applyPaymentStatusEvent.setEventType(ApplyPaymentStatus.MeterBalanceReceived.toString());
		applyPaymentStatusEvent.setPaygProductId(postBalanceRequest.getPaygProductId());
		applyPaymentStatusEvent.setReason("Payment");
		applyPaymentStatusEvent.setMeterBalanceDateTime(postBalanceRequest.getMeterBalanceDate());
		applyPaymentStatusEvent.setMeterBalance(postBalanceRequest.getMeterBalance());
		applyPaymentStatusEvent.setTransactionId(postBalanceRequest.getTransactionId());
		applyPaymentStatusEvents.setId(getId(postBalanceRequest.getPaygProductId()));
		applyPaymentStatusEvents.setApplyPaymentStatusEvent(applyPaymentStatusEvent);
		ObjectMapper Obj = new ObjectMapper();
		return Obj.writeValueAsString(applyPaymentStatusEvents);
	}

	private String getId(String paygProductId) {
		int year = LocalDate.now().getYear();
		int month = LocalDate.now().getMonthOfYear();
		String id = new StringBuffer().append(paygProductId).append("::").append("E").append("::").append(year)
				.append("::").append(month).toString();

		return id;
	}
	public static String getTransactionId() {
		Random rnd = new Random();
		int number = rnd.nextInt(9999999);
		return String.format("%07d", number);
	}

//	public mapGetUtrnResponseTo
}