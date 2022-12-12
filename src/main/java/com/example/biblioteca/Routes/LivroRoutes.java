package com.example.biblioteca.Routes;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.biblioteca.Models.Livro;
import com.example.biblioteca.Services.LivroService;

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

  @GetMapping(value="/livro/{id}")
	public ResponseEntity<Livro> getLivros(@PathVariable int id){
		return livroService.getById(id);
	}

	@PostMapping(value="/livro/create", 
  consumes = MediaType.APPLICATION_JSON_VALUE, 
  produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Livro> createLivro(@RequestBody Livro livro) {  
    return livroService.create(livro);
  }

  @PutMapping(value="/livro/update", 
  consumes = MediaType.APPLICATION_JSON_VALUE, 
  produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Livro> updateLivro(@RequestBody Livro livro) {  
    return livroService.update(livro);
  }

  @DeleteMapping(value="/livro/delete/{id}")
	public ResponseEntity<String> deleteLivro(@PathVariable int id){
		return livroService.delete(id);
	}
}
