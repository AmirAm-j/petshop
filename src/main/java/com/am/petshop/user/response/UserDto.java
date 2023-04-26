package com.am.petshop.user.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {

	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
}
