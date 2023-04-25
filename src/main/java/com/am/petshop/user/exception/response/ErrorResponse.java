package com.am.petshop.user.exception.response;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	private String code;
	private String message;

}
