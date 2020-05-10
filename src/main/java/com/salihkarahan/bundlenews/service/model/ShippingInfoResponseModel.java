package com.salihkarahan.bundlenews.service.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShippingInfoResponseModel {
	private long saleId;
	private boolean status;
	private ZonedDateTime createdAt;
}
