package com.am.petshop.user.controller;

import com.am.petshop.user.model.User;
import com.am.petshop.user.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/findUserByUsername/{username}")
	ResponseEntity <User> findUserByUsername (@PathVariable String username){
		User user = userService.findUserByUsername(username);
		return new ResponseEntity<>(user, HttpStatus.FOUND);
	}

	@GetMapping("/findUserWithId")
	ResponseEntity<User> findUserById (@RequestParam int id){
		User user = userService.findUserById(id);
		return new ResponseEntity<>(user, HttpStatus.FOUND);
	}

	@DeleteMapping("/deleteUserById/{id}")
	ResponseEntity<Map<String,Integer>> deleteUSerById (@PathVariable int id){
		Map<String,Integer> info = new HashMap<>();
		userService.deleteUserById(id);
		info.put("user is deleted,  id: ", id);
		return new ResponseEntity<>(info, HttpStatus.OK);
	}
}

