package com.example.demo.Routes;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.Livro;
import com.example.demo.Services.LivroService;

@RestController
public class LivroRoutes {
  
  private final LivroService livroService;

  public LivroRoutes(LivroService livroService) {
    this.livroService = livroService;
  }

	@GetMapping(value="/livro/all")
	public ResponseEntity<List<Livro>> getLivros(){
		return livroService.getAll();
	}

	@PostMapping(value="/livro/create", 
  consumes = MediaType.APPLICATION_JSON_VALUE, 
  produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Livro> createLivro(@RequestBody Livro livro) {  
    return livroService.create(livro);
  }
}
