package com.eon.applypayment.rest;

import javax.validation.Valid;

import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eon.applypayment.vo.PostBalanceRequest;
import com.eon.applypayment.vo.PostUtrnRequest;
import com.eon.applypayment.vo.TransactionDetail;
import com.eon.applypayment.vo.UtrnAppliedConfirmationRequest;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApplyPaymentController {
	private static final Logger logger = LoggerFactory.getLogger(ApplyPaymentController.class);
	@Autowired
	ProducerTemplate producerTemplate;

	@PostMapping(consumes = "application/json", path = "/payments")
	public ResponseEntity<HttpStatus> applyPayment(@Valid @RequestBody TransactionDetail transactionDetail) {
		logger.info("ApplyPaymentController class of applyPayment method start :{}", transactionDetail.toString());
		producerTemplate.sendBody("direct:applyPayment", transactionDetail);
		return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);

	}

	@PostMapping(consumes = "application/json", path = "/utrns")
	public ResponseEntity<HttpStatus> postUtrn(@Valid @RequestBody PostUtrnRequest postUtrnRequest) {
		logger.info("ApplyPaymentController class of postUtrn method start :{}", postUtrnRequest.toString());
		producerTemplate.sendBody("direct:handleUtrnPosted", postUtrnRequest);
		return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);

	}

	@PostMapping(consumes = "application/json", path = "/utrnapplied")
	public ResponseEntity<HttpStatus> handleUtrnAppliedConfirmation(
			@Valid @RequestBody UtrnAppliedConfirmationRequest utrnAppliedConfirmationRequest) {
		logger.info("ApplyPaymentController class of handleUtrnAppliedConfirmation method start :{}",
				utrnAppliedConfirmationRequest.toString());
		producerTemplate.sendBody("direct:handleUtrnApplied", utrnAppliedConfirmationRequest);
		return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);

	}

	@PostMapping(consumes = "application/json", path = "/meterbalance")
	public ResponseEntity<HttpStatus> postMeterBalance(@Valid @RequestBody PostBalanceRequest postBalanceRequest) {
		logger.info("ApplyPaymentController class of handleUtrnAppliedConfirmation method start :{}",
				postBalanceRequest.toString());
		producerTemplate.sendBody("direct:handleMeterBalancePosted", postBalanceRequest);
		return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);

	}

}
