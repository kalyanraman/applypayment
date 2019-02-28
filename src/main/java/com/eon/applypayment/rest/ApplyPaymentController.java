package com.eon.applypayment.rest;

import javax.validation.Valid;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eon.applypayment.vo.GetUtrnRequest;
import com.eon.applypayment.vo.GetUtrnResponse;
import com.eon.applypayment.vo.PostBalanceRequest;
import com.eon.applypayment.vo.PostUtrnRequest;
import com.eon.applypayment.vo.TransactionDetail;
import com.eon.applypayment.vo.UtrnAppliedConfirmationRequest;

@RestController
//@RequestMapping("/payments")
public class ApplyPaymentController {

	@Autowired
	ProducerTemplate producerTemplate;

	@PostMapping(consumes = "application/json", path="/payments")
	public ResponseEntity<HttpStatus> applyPayment(
			@Valid @RequestBody TransactionDetail transactionDetail) {
		System.out.println(transactionDetail);
		producerTemplate.sendBody("direct:applyPayment", transactionDetail);
		return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);

	}

	@PostMapping(consumes = "application/json", path="/utrns")
	public ResponseEntity<HttpStatus> postUtrn(
			@Valid @RequestBody PostUtrnRequest postUtrnRequest) {
		producerTemplate.sendBody("direct:handleUtrnPosted", postUtrnRequest);
		return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);

	}

	@PostMapping(consumes = "application/json", path="/utrnapplied")
	public ResponseEntity<HttpStatus> handleUtrnAppliedConfirmation(
			@Valid @RequestBody UtrnAppliedConfirmationRequest utrnAppliedConfirmationRequest) {
		producerTemplate.sendBody("direct:handleUtrnApplied", utrnAppliedConfirmationRequest);
		return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);

	}
	
	@PostMapping(consumes = "application/json", path="/meterbalance")
	public ResponseEntity<HttpStatus> postMeterBalance(
			@Valid @RequestBody PostBalanceRequest postBalanceRequest) {
		producerTemplate.sendBody("direct:handleMeterBalancePosted", postBalanceRequest);
		return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);

	}
	
}
