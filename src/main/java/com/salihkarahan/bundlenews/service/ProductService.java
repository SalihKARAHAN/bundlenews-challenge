package com.salihkarahan.bundlenews.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.salihkarahan.bundlenews.service.model.ProductInfoRequestModel;
import com.salihkarahan.bundlenews.service.model.ProductInfoResponseModel;

@Service
public class ProductService {

	public ProductInfoResponseModel getProductInfoById(ProductInfoRequestModel requestModel) {
		final String endpointString = "http://5e209e06e31c6e0014c60962.mockapi.io/api/products/{productId}";
		Map<String, String> endpointParameters = new HashMap<>();
		endpointParameters.put("productId", Long.toString(requestModel.getProductId()));
		RestTemplate restTemplate = new RestTemplate();
		ProductInfoResponseModel response = restTemplate.getForObject(endpointString, ProductInfoResponseModel.class, endpointParameters);
		return response;
	}

}
