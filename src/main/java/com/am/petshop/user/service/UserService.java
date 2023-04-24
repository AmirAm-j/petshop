package com.am.petshop.user.service;


import com.am.petshop.user.model.User;
import com.am.petshop.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void createNewUsersWithList(List<User> newUsersAsList){
		for (User u: newUsersAsList){
			if (userRepository.existsByUsername(u.getUsername())){
				throw new RuntimeException("Username already exists");
			} else {
				userRepository.save(u);
			}
		}
	}
}

