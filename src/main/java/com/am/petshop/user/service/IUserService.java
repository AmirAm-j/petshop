package com.am.petshop.user.service;

import com.am.petshop.user.response.UserDto;
import com.am.petshop.user.model.User;
import com.am.petshop.user.response.UserInfos;

import java.io.FileNotFoundException;
import java.util.List;

public interface IUserService {

	void createNewUsersWithList(List<User> newUsersAsList);

	UserDto findUserByUsername(String username);

	User findUserById(Integer id);

	void deleteUserById(Integer id);

	UserDto updateUser(UserDto newInfos, Integer id);

	List<UserInfos> listOfUsersAsTextDatei ();

	List<UserInfos> listOfUsersAsPDF() throws FileNotFoundException;
}
