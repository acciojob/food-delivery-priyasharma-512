package com.driver.model.Converter;

import com.driver.io.entity.OrderEntity;
import com.driver.io.entity.UserEntity;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.shared.dto.OrderDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class orderConverter {

    public static OrderDto convertUserRequestToDto(OrderDetailsRequestModel order) {
        return OrderDto.builder()
                .userId(order.getUserId())
                .cost(order.getCost())
                .items(order.getItems())
                .build();
    }

    public static OrderDetailsResponse ConvertDtoToUserResponse(OrderDto orderDto) {
        return OrderDetailsResponse.builder()
                .orderId(orderDto.getOrderId())
                .cost(orderDto.getCost())
                .items(orderDto.getItems())
                .userId(orderDto.getUserId())
                .status(orderDto.isStatus())
                .build();
    }

    public static OrderEntity convertDtoToEntity(OrderDto order) {
        return OrderEntity.builder()
                .cost(order.getCost())
                .orderId(order.getOrderId())
                .status(order.isStatus())
                .items(order.getItems())
                .id(order.getId())
                .userId(order.getUserId())
                .build();
    }

    public static OrderDto convertEntityToDto(OrderEntity orderEntity) {
        return OrderDto.builder()
                .orderId(orderEntity.getOrderId())
                .userId(orderEntity.getUserId())
                .items(orderEntity.getItems())
                .cost(orderEntity.getCost())
                .status(orderEntity.isStatus())
                .build();
    }

    public static OrderDetailsResponse ConvertDtoToDetailsResponse(OrderDto orderDto) {
        return OrderDetailsResponse.builder()
                .status(orderDto.isStatus())
                .userId(orderDto.getUserId())
                .items(orderDto.getItems())
                .cost(orderDto.getCost())
                .orderId(orderDto.getOrderId())
                .build();
    }

    public static OrderDetailsResponse convertDtoToResponse(OrderDto orderDto) {
        return OrderDetailsResponse.builder()
                .orderId(orderDto.getOrderId())
                .cost(orderDto.getCost())
                .items(orderDto.getItems())
                .userId(orderDto.getUserId())
                .status(orderDto.isStatus())
                .build();
    }

    public static OrderDto convertRequestModelToDto(OrderDetailsRequestModel order) {
        return OrderDto.builder()
                .items(order.getItems())
                .cost(order.getCost())
                .userId(order.getUserId())
                .build();
    }
}
