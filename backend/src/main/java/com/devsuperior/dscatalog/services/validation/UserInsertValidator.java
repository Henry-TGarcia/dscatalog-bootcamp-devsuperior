package com.devsuperior.dscatalog.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.devsuperior.dscatalog.dto.UserInsertDTO;
import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.repositories.UserRepository;
import com.devsuperior.dscatalog.resources.exceptions.FieldMessage;
															//1-Tipo da annotation UserInsertValid
public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {
															  //2-Tipo da classe que vai receber
															 //essa annotation. UserInsertDTO
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public void initialize(UserInsertValid ann) {
	}
	//Logica para iniciar o objeto.
	

	@Override
	public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {
		//Testa para saber se meu método UserInsertDTO é valido ou não.
		
		List<FieldMessage> list = new ArrayList<>();
		
		User user = repository.findByEmail(dto.getEmail());
		if(user != null) {
			list.add(new FieldMessage("email", "Email já existe"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}