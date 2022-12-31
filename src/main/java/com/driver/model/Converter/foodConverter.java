package com.driver.model.Converter;

import com.driver.io.entity.FoodEntity;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.shared.dto.FoodDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class foodConverter {


    public static FoodDetailsResponse ConvertDtoToDetailsResponse(FoodDto foodDto) {
        return FoodDetailsResponse.builder()
                .foodId(foodDto.getFoodId())
                .foodPrice(foodDto.getFoodPrice())
                .foodCategory(foodDto.getFoodCategory())
                .foodName(foodDto.getFoodName())
                .build();
    }

    public static FoodEntity convertDtoToEntity(FoodDto food) {
        return FoodEntity.builder()
                .foodName(food.getFoodName())
                .foodPrice(food.getFoodPrice())
                .foodCategory(food.getFoodCategory())
                .foodId(food.getFoodId())
            .build();
    }

    public static FoodDto convertEntityToDto(FoodEntity foodEntity) {
        return FoodDto.builder()
                .foodId(foodEntity.getFoodId())
                .foodCategory(foodEntity.getFoodCategory())
                .foodPrice(foodEntity.getFoodPrice())
                .foodName(foodEntity.getFoodName())
                .build();
    }

    public static FoodDto convertDetailsRequestToDto(FoodDetailsRequestModel foodDetails) {
        return FoodDto.builder()
                .foodName(foodDetails.getFoodName())
                .foodPrice(foodDetails.getFoodPrice())
                .foodCategory(foodDetails.getFoodCategory())
                .build();
    }

    public static FoodDetailsResponse convertDtoToResponseDetails(FoodDto foodDto) {
        return FoodDetailsResponse.builder()
                .foodName(foodDto.getFoodName())
                .foodCategory(foodDto.getFoodCategory())
                .foodPrice(foodDto.getFoodPrice())
                .foodId(foodDto.getFoodId())
                .build();
    }
}
