package com.salihkarahan.bundlenews.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.salihkarahan.bundlenews.service.model.ShippingInfoRequestModel;
import com.salihkarahan.bundlenews.service.model.ShippingInfoResponseModel;

@Service
public class ShippingService {
	
	@Async
	public CompletableFuture<ShippingInfoResponseModel> getShippingInfoBySaleId(ShippingInfoRequestModel requestModel){
		final String endpointString = "http://5e209e06e31c6e0014c60962.mockapi.io/api/shipping/{saleId}";
		Map<String, String> endpointParameters = new HashMap<>();
		endpointParameters.put("saleId", Long.toString(requestModel.getSaleId()));
		RestTemplate restTemplate = new RestTemplate();
		final ShippingInfoResponseModel response = restTemplate.getForObject(endpointString, ShippingInfoResponseModel.class, endpointParameters);
		CompletableFuture<ShippingInfoResponseModel> completableResponse = CompletableFuture.completedFuture(response);
		return completableResponse;
	}
	
	
}
