package com.devsuperior.dscatalog.dto;

public class UserInsertDTO extends UserDTO {
	private static final long serialVersionUID = 1L;

	//Atributos
	private String password;

	//Construtor
	UserInsertDTO(){
		super();
	}
	
	//Gets e Sets
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
