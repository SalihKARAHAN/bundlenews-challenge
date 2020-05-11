package com.salihkarahan.bundlenews.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.salihkarahan.bundlenews.service.SaleService;
import com.salihkarahan.bundlenews.service.model.SaleInfoRequestModel;
import com.salihkarahan.bundlenews.service.model.SaleInfoResponseModel;

@SpringBootTest
public class SaleServiceUnitTest {

	private SaleService saleService;
	private RestTemplate restTemplate;

	public SaleServiceUnitTest() {
		this.restTemplate = Mockito.mock(RestTemplate.class);		
		this.saleService = new SaleService(this.restTemplate);
	}

	@Test
	public void Call_GetSaleInfo_Than_GetCorrectResponse() throws InterruptedException, ExecutionException {
		SaleInfoRequestModel request = new SaleInfoRequestModel();
		request.setSaleId(3);
		
		SaleInfoResponseModel expectedResponse = new SaleInfoResponseModel();
		expectedResponse.setId(3);
		
		Map<String, String> mockRequestParameter = new HashMap<>();
		mockRequestParameter.put("saleId", "3");
		Mockito
			.when(this.restTemplate.getForObject("http://5e209e06e31c6e0014c60962.mockapi.io/api/sales/{saleId}", SaleInfoResponseModel.class, mockRequestParameter))
			.thenReturn(expectedResponse);
		
		
		CompletableFuture<SaleInfoResponseModel> responseTask = this.saleService.getSaleInfoById(request);
		CompletableFuture.allOf(responseTask);
		SaleInfoResponseModel response = responseTask.get();
		Assertions.assertThat(response.getId()).isEqualTo(request.getSaleId());
	}
	
	// ToDo@salih => servisten dönüş alınamadığında handle edillmeli!
	// RestTemplate mocklanabilir!
	// 

}
