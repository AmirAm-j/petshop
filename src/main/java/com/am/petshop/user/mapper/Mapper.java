package com.am.petshop.user.mapper;

public interface Mapper <D,E> {

	D convertToDto (E entity);
	E convertToEntity(D dto);

}
