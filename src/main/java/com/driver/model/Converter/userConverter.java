package com.driver.model.Converter;

import com.driver.io.entity.UserEntity;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.UserResponse;
import com.driver.shared.dto.UserDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class userConverter {
    public static UserDto convertUserRequestToDto(UserDetailsRequestModel userDetails) {
        return UserDto.builder()
                .email(userDetails.getEmail())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .build();
    }


    public static UserResponse ConvertDtoToUserResponse(UserDto userDto) {
        return UserResponse.builder()
                .userId(userDto.getUserId())
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build();
    }

    public static UserEntity convertDtoToEntity(UserDto user) {
        return UserEntity.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .id(user.getId())
                .userId(user.getUserId())
                .build();

    }

    public static UserDto convertEntityToDto(UserEntity userEntity) {
        return UserDto.builder()
                .email(userEntity.getEmail())
                .id(userEntity.getId())
                .lastName(userEntity.getLastName())
                .firstName(userEntity.getFirstName())
                .userId(userEntity.getUserId())
                .build();
    }

    public static UserResponse convertDtoToResponseDetails(UserDto userDto) {
        return UserResponse.builder()
                .lastName(userDto.getLastName())
                .firstName(userDto.getFirstName())
                .email(userDto.getEmail())
                .userId(userDto.getUserId())
                .build();
    }

    public static UserResponse ConvertDtoToDetailsResponse(UserDto userDto) {
        return UserResponse.builder()
                .userId(userDto.getUserId())
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build();
    }
}
