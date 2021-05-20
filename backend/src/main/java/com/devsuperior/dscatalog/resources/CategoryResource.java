package com.devsuperior.dscatalog.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscatalog.entities.Category;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@GetMapping 
	public ResponseEntity<List<Category>> findAll(){
		List<Category> list = new ArrayList<>();
		list.add(new Category(1L, "Books")); //o 'L' indica que vai ser um long.
		list.add(new Category(2L, "Electonics"));
		
		return ResponseEntity.ok().body(list);
						//o '.ok' me permite responder uma resposta 200,
				//200 quer dizer que a requisição foi realizada com sucesso.
						// 'body' definir o corpo da resposta.
	}
}
