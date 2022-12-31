package com.driver.ui.controller;

import java.util.List;

import com.driver.model.Converter.foodConverter;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.impl.FoodServiceImpl;
import com.driver.shared.dto.FoodDto;
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
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	FoodServiceImpl foodService;
	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{
		FoodDto foodDto = foodService.getFoodById(id);
		FoodDetailsResponse foodDetailsResponse = foodConverter.ConvertDtoToDetailsResponse(foodDto);
		return foodDetailsResponse;
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {
		FoodDto foodDto = foodService.createFood(foodConverter.convertDetailsRequestToDto(foodDetails));
		FoodDetailsResponse foodDetailsResponse = foodConverter.ConvertDtoToDetailsResponse(foodDto);
		return foodDetailsResponse;
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		FoodDto foodDto = new FoodDto();
		foodDto.setFoodCategory(foodDetails.getFoodCategory());
		foodDto.setFoodName(foodDetails.getFoodName());
		foodDto.setFoodPrice(foodDetails.getFoodPrice());
		foodService.updateFoodDetails(id,foodDto);
		FoodDetailsResponse foodDetailsResponse = foodConverter.convertDtoToResponseDetails(foodDto);
		return foodDetailsResponse;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		foodService.deleteFoodItem(id);
		operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
		operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.toString());
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {
		FoodDto foodDto = (FoodDto) foodService.getFoods();
		FoodDetailsResponse foodDetailsResponse = foodConverter.ConvertDtoToDetailsResponse(foodDto);
		return (List<FoodDetailsResponse>) foodDetailsResponse;
	}
}
