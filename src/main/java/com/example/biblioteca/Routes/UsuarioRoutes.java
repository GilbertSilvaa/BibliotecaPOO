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

import com.example.biblioteca.Models.Usuario;
import com.example.biblioteca.Services.UsuarioService;

@RestController
public class UsuarioRoutes {

  private final UsuarioService usuarioService;

	public UsuarioRoutes(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping(value="/usuario/all")
	public ResponseEntity<List<Usuario>> getUsuarios(){
		return usuarioService.getAll();
	}
	
	@PostMapping(value="/usuario/create", 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario cliente) {  
    return usuarioService.create(cliente);
	}

	@GetMapping(value="/usuario/{id}")
	public ResponseEntity<Usuario> getUsuarioByID(@PathVariable int id){
		return usuarioService.getById(id);
	}

	@PutMapping(value="/usuario/update", 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario cliente) {  
    return usuarioService.update(cliente);
	}

	@DeleteMapping(value="/usuario/delete/{id}")
	public ResponseEntity<String> deleteUsuario(@PathVariable int id){
		return usuarioService.delete(id);
	}
}