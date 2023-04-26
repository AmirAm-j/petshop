package com.am.petshop.user.response;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfos {
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
}
