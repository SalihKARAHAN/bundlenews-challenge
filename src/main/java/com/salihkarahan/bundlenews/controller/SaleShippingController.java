package com.salihkarahan.bundlenews.controller;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salihkarahan.bundlenews.service.ProductService;
import com.salihkarahan.bundlenews.service.SaleService;
import com.salihkarahan.bundlenews.service.SaleShippingService;
import com.salihkarahan.bundlenews.service.model.ProductInfoRequestModel;
import com.salihkarahan.bundlenews.service.model.ProductInfoResponseModel;
import com.salihkarahan.bundlenews.service.model.SaleInfoRequestModel;
import com.salihkarahan.bundlenews.service.model.SaleInfoResponseModel;
import com.salihkarahan.bundlenews.service.model.SaleShippingRequestModel;
import com.salihkarahan.bundlenews.service.model.SaleShippingResponseModel;

@RestController
@RequestMapping("/sale")
public class SaleShippingController {

	private final SaleShippingService saleShippingService;

	public SaleShippingController(
			SaleShippingService saleShippingService
			) {
		this.saleShippingService = saleShippingService;
	}

	@GetMapping("/{saleId}/shipping")
	public ResponseEntity<SaleShippingResponseModel> GetShippingInfoById(@PathVariable("saleId") Long saleId) {
		SaleShippingRequestModel requestModel = new SaleShippingRequestModel();
		requestModel.setSaleId(saleId);
		ResponseEntity<SaleShippingResponseModel> response = null;
		try {
			SaleShippingResponseModel responseModel = this.saleShippingService.getSaleShippingBySaleId(requestModel);
			response = ResponseEntity.ok(responseModel);
		} catch (Exception exception) {
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Error:", exception.getMessage())
					.build();
		}

		return response;
	}
}
