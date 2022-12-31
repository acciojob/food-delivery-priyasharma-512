package com.driver.ui.controller;

import java.util.List;

import com.driver.model.Converter.foodConverter;
import com.driver.model.Converter.userConverter;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.*;
import com.driver.service.impl.UserServiceImpl;
import com.driver.shared.dto.FoodDto;
import com.driver.shared.dto.UserDto;
import org.apache.catalina.User;
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
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserServiceImpl userService;

	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception{
		UserDto userDto  = userService.getUserByUserId(id);
		UserResponse userResponse = userConverter.ConvertDtoToDetailsResponse(userDto);
		return userResponse;
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto = userService.createUser(userConverter.convertUserRequestToDto(userDetails));
		UserResponse userResponse = userConverter.ConvertDtoToUserResponse(userDto);
		return userResponse;
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto = new UserDto();
		userDto.setEmail(userDetails.getEmail());
		userDto.setFirstName(userDetails.getFirstName());
		userDto.setLastName(userDetails.getLastName());
		userService.updateUser(id,userDto);
		UserResponse userResponse = userConverter.convertDtoToResponseDetails(userDto);
		return userResponse;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception{
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		userService.deleteUser(id);
		operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
		operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.toString());
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<UserResponse> getUsers(){
		UserDto userDto = (UserDto) userService.getUsers();
		UserResponse userResponse = userConverter.ConvertDtoToDetailsResponse(userDto);
		return (List<UserResponse>) userResponse;
	}
	
}
