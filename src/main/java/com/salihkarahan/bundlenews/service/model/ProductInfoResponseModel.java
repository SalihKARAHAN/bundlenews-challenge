package com.salihkarahan.bundlenews.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductInfoResponseModel {
	private long id;
	private String category;
	private String name;
	private double price;
	private String image;
}
