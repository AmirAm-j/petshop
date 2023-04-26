package com.am.petshop.user.mapper;

import com.am.petshop.user.response.UserDto;
import com.am.petshop.user.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements Mapper <UserDto, User>{

	private final ModelMapper modelMapper;

	public UserMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDto convertToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public User convertToEntity(UserDto dto) {
		return null;
	}
}
