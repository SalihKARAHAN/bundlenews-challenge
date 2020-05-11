package com.salihkarahan.bundlenews.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.salihkarahan.bundlenews.service.model.SaleInfoRequestModel;
import com.salihkarahan.bundlenews.service.model.SaleInfoResponseModel;

@Service
public class SaleService {

	private final RestTemplate restTemplate;
	
	public SaleService(
			RestTemplate restTemplate
			) {
		this.restTemplate = restTemplate;
	}
	
	@Async
	public CompletableFuture<SaleInfoResponseModel> getSaleInfoById(SaleInfoRequestModel requestModel) {
		final String endpointString = "http://5e209e06e31c6e0014c60962.mockapi.io/api/sales/{saleId}";
		Map<String, String> endpointParameters = new HashMap<>();
		endpointParameters.put("saleId", Long.toString(requestModel.getSaleId()));
		final SaleInfoResponseModel response = this.restTemplate.getForObject(endpointString, SaleInfoResponseModel.class, endpointParameters);
		CompletableFuture<SaleInfoResponseModel> completableResponse = CompletableFuture.completedFuture(response);
		return completableResponse;
	}
}
