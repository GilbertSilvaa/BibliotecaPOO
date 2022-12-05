package com.example.demo.Routes;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.Cliente;
import com.example.demo.Services.ClienteService;

@RestController
public class ClienteRoutes {

  private final ClienteService clienteService;

	public ClienteRoutes(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping(value="/cliente/all")
	public ResponseEntity<List<Cliente>> getClientes(){
		return clienteService.getAll();
	}
	
	@PostMapping(value="/cliente/create", 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {  
    return clienteService.create(cliente);
	}

}