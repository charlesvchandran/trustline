package com.ripple.track.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrustLinePayment {

	@JsonProperty("credit")
    private Number credit;

	public Number getCredit() {
		return credit;
	}

	public void setCredit(Number credit) {
		this.credit = credit;
	}
}
