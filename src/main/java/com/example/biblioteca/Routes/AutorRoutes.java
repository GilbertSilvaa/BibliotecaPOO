package com.example.biblioteca.Routes;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
