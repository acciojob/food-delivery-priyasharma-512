package com.driver.ui.controller;

import java.util.List;

import com.driver.model.Converter.foodConverter;
import com.driver.model.Converter.orderConverter;
import com.driver.model.Converter.userConverter;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.*;
import com.driver.service.OrderService;
import com.driver.shared.dto.FoodDto;
import com.driver.shared.dto.OrderDto;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderService orderService;

	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{
		OrderDto orderDto = orderService.getOrderById(id);
		OrderDetailsResponse orderDetailsResponse = orderConverter.ConvertDtoToDetailsResponse(orderDto);
		return orderDetailsResponse;
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {
		OrderDto orderDto = orderService.createOrder(orderConverter.convertUserRequestToDto(order));
		OrderDetailsResponse orderDetailsResponse  = orderConverter.ConvertDtoToUserResponse(orderDto);
		return orderDetailsResponse;
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{
		OrderDto orderDto = orderService.updateOrderDetails(id, orderConverter.convertRequestModelToDto(order));
		return orderConverter.convertDtoToResponse(orderDto);

	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		orderService.deleteOrder(id);
		operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
		operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.toString());
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<OrderDetailsResponse> getOrders() {
		OrderDto orderDto = (OrderDto) orderService.getOrders();
		OrderDetailsResponse orderDetailsResponse = orderConverter.ConvertDtoToDetailsResponse(orderDto);
		return (List<OrderDetailsResponse>) orderDetailsResponse;

	}
}
