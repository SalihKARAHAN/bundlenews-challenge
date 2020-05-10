package com.salihkarahan.bundlenews.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.salihkarahan.bundlenews.service.SaleService;
import com.salihkarahan.bundlenews.service.model.SaleInfoRequestModel;
import com.salihkarahan.bundlenews.service.model.SaleInfoResponseModel;

@SpringBootTest
public class SaleServiceUnitTest {

	private SaleService saleService;

	public SaleServiceUnitTest() {
		this.saleService = new SaleService();
	}

	@Test
	public void Call_GetSaleInfo_Than_GetCorrectResponse() throws InterruptedException, ExecutionException {
		SaleInfoRequestModel request = new SaleInfoRequestModel();
		request.setSaleId(3);
		
		CompletableFuture<SaleInfoResponseModel> responseTask = this.saleService.getSaleInfoById(request);
		CompletableFuture.allOf(responseTask);
		SaleInfoResponseModel response = responseTask.get();
		Assertions.assertThat(response.getId()).isEqualTo(request.getSaleId());
	}
	
	// ToDo@salih => servisten dönüş alınamadığında handle edillmeli!
	// RestTemplate mocklanabilir!
	// 

}
