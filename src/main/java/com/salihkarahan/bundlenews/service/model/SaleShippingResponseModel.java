package com.salihkarahan.bundlenews.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleShippingResponseModel {
	
	private String status;
	private SaleResponseModel sale;
	private ProductResponseModel product;

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class SaleResponseModel {
		private long id;
		private String code;
	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class ProductResponseModel {
		private long id;
		private String name;
		private double price;
	}

}
