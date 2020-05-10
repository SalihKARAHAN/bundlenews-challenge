package com.salihkarahan.bundlenews.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.salihkarahan.bundlenews.service.ShippingService;
import com.salihkarahan.bundlenews.service.model.ShippingInfoRequestModel;
import com.salihkarahan.bundlenews.service.model.ShippingInfoResponseModel;

@SpringBootTest
public class ShippingServiceUnitTest {

	private ShippingService shippingService;
	
	public ShippingServiceUnitTest() {
		this.shippingService = new ShippingService();
	}
	
	@Test
	public void whenCall_getShippingInfoBySaleId_then_returnSuccessedInfo() throws InterruptedException, ExecutionException {
		ShippingInfoRequestModel request = new ShippingInfoRequestModel();
		request.setSaleId(3);
		
		CompletableFuture<ShippingInfoResponseModel> responseTask = this.shippingService.getShippingInfoBySaleId(request);
		CompletableFuture.allOf(responseTask);
		ShippingInfoResponseModel response = responseTask.get();
		Assertions.assertThat(response.getSaleId()).isEqualTo(request.getSaleId());
	}
	
}
