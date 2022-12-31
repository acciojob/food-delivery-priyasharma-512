package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.model.Converter.foodConverter;
import com.driver.model.Converter.userConverter;
import com.driver.service.UserService;
import com.driver.shared.dto.FoodDto;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) throws Exception {
        UserEntity userEntity = userRepository.save(userConverter.convertDtoToEntity(user));
        return userConverter.convertEntityToDto(userEntity);
    }

   @Override
   public UserDto getUser(String email) throws Exception {
       return null;
   }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        UserDto userDto = userConverter.convertEntityToDto(userEntity);
        return userDto;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        userEntity.setEmail(userEntity.getEmail());
        userEntity.setLastName(userEntity.getLastName());
        userEntity.setFirstName(userEntity.getFirstName());
        userRepository.save(userEntity);
        return userConverter.convertEntityToDto(userEntity);

    }

    @Override
    public void deleteUser(String userId) throws Exception {
        userRepository.deleteById(Long.valueOf(userId));
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> userDto = new ArrayList<>();
        List<UserEntity> userEntityList = (List<UserEntity>) userRepository.findAll();
        for(UserEntity users: userEntityList)
        {
            userDto.add(userConverter.convertEntityToDto(users));
        }
        return userDto;
    }
}