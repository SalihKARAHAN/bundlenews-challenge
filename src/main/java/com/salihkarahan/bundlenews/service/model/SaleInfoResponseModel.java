package com.salihkarahan.bundlenews.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleInfoResponseModel {
	private long id;
	private String saleCode;
	private long productId;
}
