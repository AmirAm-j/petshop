package com.am.petshop.user.service;


import com.am.petshop.user.exception.ResourceNotFoundException;
import com.am.petshop.user.model.User;
import com.am.petshop.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void createNewUsersWithList(List<User> newUsersAsList){
		for (User u: newUsersAsList){
			if (userRepository.existsByUsername(u.getUsername())){
				throw new RuntimeException("Username already exists");
			} else {
				userRepository.save(u);
			}
		}
	}

	@Override
	public User findUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()){
			return user.get();
		} else {
			throw new ResourceNotFoundException("User not found");
		}
	}

	@Override
	public User findUserById(Integer id) {
		return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
	}

	@Override
	@Transactional
	public void deleteUserById(int id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteUserById(id);
		} else {
			throw new ResourceNotFoundException("User not found");
		}
	}
}



