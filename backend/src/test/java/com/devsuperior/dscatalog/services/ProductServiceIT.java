package com.devsuperior.dscatalog.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

@SpringBootTest
@Transactional
public class ProductServiceIT {

	@Autowired
	private ProductService service;

	@Autowired
	private ProductRepository repository;

	private Long existingId;
	private Long nonExistingId;
	private Long countTotalProducts;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalProducts = 25L; // Total de produtos
	}

	@Test
	public void deleteShouldDeleteResourceWhenIdExists() {
		// Act
		service.delete(existingId);

		// Assertion
		Assertions.assertEquals(countTotalProducts - 1, repository.count());
		// repository.count conta o total de registros no BD
	}

	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		// Assertions
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
	}
	
	@Test
	public void findAllPagedShouldReturnPageWhenPage0Size10() {
		//Arrange							pagina0, n elementos10
		PageRequest pageRequest = PageRequest.of(0, 10);
		//Act
		Page<ProductDTO> result = service.findAllPaged(pageRequest);
		//Assertions
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals(0, result.getNumber());//testar se é mesmo a Pagina0
		Assertions.assertEquals(10, result.getSize());//testar se está retornando 10 elementos
		Assertions.assertEquals(countTotalProducts, result.getTotalElements());
						//getTotalElements -> pega o total dos elementos das paginas
	}
	
	@Test
	public void findAllPagedShouldReturnEmptyPageWhenPageDoesNotExist() {
		//Arrange
		PageRequest pageRequest = PageRequest.of(50, 10);
		//Act
		Page<ProductDTO> result = service.findAllPaged(pageRequest);
		//Assertions
		Assertions.assertTrue(result.isEmpty());
	}
	
	@Test
	public void findAllPagedShouldReturnSortedPageWhenSortByName() {
		//Arrange						pagina, quantidade de elementos, ordenação dos eleme	
		PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("name"));
		//Act
		Page<ProductDTO> result = service.findAllPaged(pageRequest);
		//Assertions
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals("Macbook Pro", result.getContent().get(0).getName());
		//Testando se o priemiro elemento é um MacBook Pro
		Assertions.assertEquals("PC Gamer", result.getContent().get(1).getName());
		Assertions.assertEquals("PC Gamer Alfa", result.getContent().get(2).getName());
	}

}
