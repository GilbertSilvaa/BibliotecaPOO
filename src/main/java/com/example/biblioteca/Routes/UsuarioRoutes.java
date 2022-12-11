package com.example.biblioteca.Routes;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<List<Usuario>> getClientes(){
		return usuarioService.getAll();
	}
	
	@PostMapping(value="/usuario/create", 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> createCliente(@RequestBody Usuario cliente) {  
    return usuarioService.create(cliente);
	}

}