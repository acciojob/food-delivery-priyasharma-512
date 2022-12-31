package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.entity.OrderEntity;
import com.driver.io.entity.UserEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.model.Converter.foodConverter;
import com.driver.model.Converter.orderConverter;
import com.driver.model.Converter.userConverter;
import com.driver.service.OrderService;
import com.driver.shared.dto.FoodDto;
import com.driver.shared.dto.OrderDto;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService
        {
            @Autowired
            OrderRepository orderRepository;

            @Override
            public OrderDto createOrder(OrderDto order) {
                OrderEntity orderEntity = orderRepository.save(orderConverter.convertDtoToEntity(order));
                return orderConverter.convertEntityToDto(orderEntity);
            }

            @Override
            public OrderDto getOrderById(String orderId) throws Exception {
                OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
                OrderDto orderDto = orderConverter.convertEntityToDto(orderEntity);
                return orderDto;
            }

            @Override
            public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
                OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
                orderEntity.setOrderId(order.getOrderId());
                orderEntity.setItems(order.getItems());
                orderEntity.setCost(order.getCost());
                orderEntity.setStatus(order.isStatus());
                orderRepository.save(orderEntity);
                return orderConverter.convertEntityToDto(orderEntity);
            }

            @Override
            public void deleteOrder(String orderId) throws Exception {
                orderRepository.deleteById(Long.valueOf(orderId));
            }

            @Override
            public List<OrderDto> getOrders() {
                List<OrderDto> orderDtos = new ArrayList<>();
                List<OrderEntity> orderEntityList = (List<OrderEntity>) orderRepository.findAll();
                for(OrderEntity orders: orderEntityList)
                {
                    orderDtos.add(orderConverter.convertEntityToDto(orders));
                }
                return orderDtos;
            }
        }