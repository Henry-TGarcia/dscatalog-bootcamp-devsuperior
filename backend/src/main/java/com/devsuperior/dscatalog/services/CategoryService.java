package com.devsuperior.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;

@Service 
public class CategoryService {

	//Associação
	@Autowired 
	private CategoryRepository repository; 
	//para a camada de service acessar a camada do BD.
	
	@Transactional(readOnly = true)
	public List<Category> findAll(){
		return repository.findAll(); //buscar todos
	}
}
