package com.ripple.track.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ripple.track.exception.TrustLineException;
import com.ripple.track.vo.TrustLinePayment;

@Component
public class TrustLineServiceImpl implements TrustLineService {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	static String ROOT_URI = "http://localhost:8081";
    
    private RestTemplate restTemplate;
    private HttpHeaders headers;
    
    private Number trustLineBalance;
    
    public TrustLineServiceImpl() {
    	restTemplate = new RestTemplateBuilder().rootUri(ROOT_URI).build();

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        trustLineBalance = 0;
        
        logger.info("Welcome to the Trustline");
		logger.info("Trustline balance is: " + trustLineBalance);
	}

	@Override
	public Map<String, Object> sendPayment(Number amount) throws TrustLineException {
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("success", false);
		responseMap.put("balance", trustLineBalance);
		try {
			TrustLinePayment trustLinePayment = new TrustLinePayment();
			trustLinePayment.setCredit(amount);
			HttpEntity<TrustLinePayment> request = new HttpEntity<>(trustLinePayment, headers);

			StringBuilder strBuilder = new StringBuilder("Paying ").append(amount).append(" to Bob...");
			logger.info(strBuilder.toString());
			ResponseEntity<String> responseEntity = restTemplate.postForEntity("/bobtrustline/api/payment", request, String.class);
			boolean successTransaction = Boolean.FALSE;
			if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> map = new HashMap<String, Object>();
				
				map = mapper.readValue(responseEntity.getBody(), new TypeReference<Map<String, Object>>(){});
				
				successTransaction = (boolean) map.get("success");
				if (successTransaction) {
					logger.info("Sent");
					trustLineBalance = trustLineBalance.doubleValue() - amount.doubleValue();
					StringBuilder trustLineBalanceStrBuilder = new StringBuilder("Trustline balance is: ").append(trustLineBalance);
					logger.info(trustLineBalanceStrBuilder.toString());
					
					responseMap.put("success", successTransaction);
					responseMap.put("balance", trustLineBalance);
				}
			}
			return responseMap;
		} catch (IOException e) {
			logger.error("caught exception while send payment :: ", e);
			throw new TrustLineException("failed to send payment, please try again!");
		}
	}
}
