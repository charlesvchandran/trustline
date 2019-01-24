package com.ripple.track.service;

import java.util.Map;

import com.ripple.track.exception.TrustLineException;

public interface TrustLineService {
	Map<String, Object> sendPayment(Number amount) throws TrustLineException;
}
