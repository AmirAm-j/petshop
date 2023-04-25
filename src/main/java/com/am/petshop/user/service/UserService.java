package com.am.petshop.user.service;


import com.am.petshop.user.exception.ResourceNotFoundException;
import com.am.petshop.user.mapper.UserMapper;
import com.am.petshop.user.mapper.response.UserDto;
import com.am.petshop.user.model.User;
import com.am.petshop.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

	private final UserRepository userRepository;
	private final UserMapper mapper;

	public UserService(UserRepository userRepository, UserMapper mapper) {
		this.userRepository = userRepository;
		this.mapper = mapper;
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
	public UserDto findUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()){
			return mapper.convertToDto(user.get()) ;
		} else {
			throw new ResourceNotFoundException("User not found");
		}
	}

	@Override
	@Transactional
	public void deleteUserById(Integer id) {
		 try {
			 if (userRepository.existsById(id)) {
				 userRepository.deleteUserById(id);
			 } else {
				 throw new ResourceNotFoundException("User not found");
			 }
		 } catch (Exception e){
			 throw new RuntimeException("Error deleting user: " + id, e);
		 }
	}

	@Override
	public User findUserById(Integer id) {
		return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
	}

	@Override
	@Transactional
	public UserDto updateUser(UserDto updateUser, Integer id) {
		try {
			User user = findUserById(id);
			if (user!=null){
				user.setFirstname(updateUser.getFirstname());
				user.setLastname(updateUser.getLastname());
				user.setEmail(updateUser.getEmail());
				user.setPhone(updateUser.getPhone());
				user.setUsername(updateUser.getUsername());
				userRepository.save(user);
				return mapper.convertToDto(user);
			} else {
				throw new ResourceNotFoundException("User not found");
			}
		} catch (Exception e){
			throw new RuntimeException("Error updating user: " + id, e);
		}

	}
}



