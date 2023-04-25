package com.am.petshop.user.service;

import com.am.petshop.user.model.User;

import java.util.List;

public interface IUserService {

	void createNewUsersWithList(List<User> newUsersAsList);

	User findUserByUsername(String username);

	User findUserById(Integer id);

	void deleteUserById(int id);
}
