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

import com.example.biblioteca.Models.Autor;
import com.example.biblioteca.Services.AutorService;

@RestController
public class AutorRoutes {
 
  private final AutorService autorService;

	public AutorRoutes(AutorService autorService) {
		this.autorService = autorService;
	}

  @GetMapping(value="/autor/all")
	public ResponseEntity<List<Autor>> getUsuarios(){
		return autorService.getAll();
	}

	@GetMapping(value="/autor/{id}")
	public ResponseEntity<Autor> getUsuarioByID(@PathVariable int id){
		return autorService.getById(id);
	}

	@PostMapping(value="/autor/create", 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Autor> createAutor(@RequestBody Autor autor) {  
    return autorService.create(autor);
	}

	@PutMapping(value="/autor/update", 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Autor> updateAutor(@RequestBody Autor autor) {  
    return autorService.update(autor);
	}

	@DeleteMapping(value="/autor/delete/{id}")
	public ResponseEntity<String> deleteUsuario(@PathVariable int id){
		return autorService.delete(id);
	}
}
