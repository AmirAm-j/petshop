package com.am.petshop.user.controller;

import com.am.petshop.user.model.User;
import com.am.petshop.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/createWithList")
	ResponseEntity <Map<String,String>> createUsersWithList(@RequestBody List<User> newUsersAsList){
		Map<String,String> info = new HashMap<>();
		userService.createNewUsersWithList(newUsersAsList);
		info.put("message", "Users created successfully");
		return new ResponseEntity<>(info, HttpStatus.CREATED);
	}
}

