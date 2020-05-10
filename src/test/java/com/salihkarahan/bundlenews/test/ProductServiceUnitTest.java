package com.salihkarahan.bundlenews.test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.salihkarahan.bundlenews.service.ProductService;
import com.salihkarahan.bundlenews.service.model.ProductInfoRequestModel;
import com.salihkarahan.bundlenews.service.model.ProductInfoResponseModel;

@SpringBootTest
public class ProductServiceUnitTest {
	
	private ProductService productService;
	
	public ProductServiceUnitTest() {
		this.productService = new ProductService();
	}

	@Test
	public void call_getProductInfo_than_getCorrectResponse() {
		ProductInfoRequestModel request = new ProductInfoRequestModel();
		request.setProductId(15);
		
		ProductInfoResponseModel response = this.productService.getProductInfoById(request);
		Assertions.assertThat(response.getId()).isEqualTo(request.getProductId());
	}
	
}
