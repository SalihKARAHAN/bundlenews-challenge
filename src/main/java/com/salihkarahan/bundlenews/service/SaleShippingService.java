package com.salihkarahan.bundlenews.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.salihkarahan.bundlenews.service.model.ProductInfoRequestModel;
import com.salihkarahan.bundlenews.service.model.ProductInfoResponseModel;
import com.salihkarahan.bundlenews.service.model.SaleInfoRequestModel;
import com.salihkarahan.bundlenews.service.model.SaleInfoResponseModel;
import com.salihkarahan.bundlenews.service.model.SaleShippingRequestModel;
import com.salihkarahan.bundlenews.service.model.SaleShippingResponseModel;
import com.salihkarahan.bundlenews.service.model.ShippingInfoRequestModel;
import com.salihkarahan.bundlenews.service.model.ShippingInfoResponseModel;

@Service
public class SaleShippingService {

	private final SaleService saleService;
	private final ProductService productService;
	private final ShippingService shippingService;

	public SaleShippingService(
			SaleService saleService
			, ProductService productService
			, ShippingService shippingService
			) {
		this.saleService = saleService;
		this.productService = productService;
		this.shippingService = shippingService;
	}

	public SaleShippingResponseModel getSaleShippingBySaleId(SaleShippingRequestModel requestModel) throws InterruptedException, ExecutionException {

		SaleInfoRequestModel saleInfoRequest = new SaleInfoRequestModel();
		saleInfoRequest.setSaleId(requestModel.getSaleId());
		CompletableFuture<SaleInfoResponseModel> getSaleInfoTask = this.saleService.getSaleInfoById(saleInfoRequest);

		ShippingInfoRequestModel shippingInfoRequest = new ShippingInfoRequestModel();
		shippingInfoRequest.setSaleId(requestModel.getSaleId());
		CompletableFuture<ShippingInfoResponseModel> getShippingInfoTask = this.shippingService.getShippingInfoBySaleId(shippingInfoRequest);

		CompletableFuture.allOf(getSaleInfoTask, getShippingInfoTask).join();
		SaleInfoResponseModel saleInfo = getSaleInfoTask.get();
		ShippingInfoResponseModel shippingInfo = getShippingInfoTask.get();

		ProductInfoRequestModel productInfoRequest = new ProductInfoRequestModel();
		productInfoRequest.setProductId(saleInfo.getProductId());
		ProductInfoResponseModel productInfo = this.productService.getProductInfoById(productInfoRequest);

		SaleShippingResponseModel responseModel = new SaleShippingResponseModel();
		responseModel.setStatus(shippingInfo.isStatus() ? "TESLİM EDİLDİ" : "TESLİM EDİLEMEDİ");
		SaleShippingResponseModel.SaleResponseModel sale = responseModel.new SaleResponseModel();
		sale.setId(saleInfo.getId());
		sale.setCode(saleInfo.getSaleCode());
		responseModel.setSale(sale);
		
		SaleShippingResponseModel.ProductResponseModel product = responseModel.new ProductResponseModel();
		product.setId(productInfo.getId());
		product.setName(productInfo.getName());
		product.setPrice(productInfo.getPrice());
		responseModel.setProduct(product);
		
		return responseModel;

	}

}
