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

import com.example.biblioteca.Models.Pedido;
import com.example.biblioteca.Services.CrudService;
import com.example.biblioteca.Services.PedidoService;

@RestController
public class PedidoRoutes {
  
  private final CrudService<Pedido> pedidoService;

  public PedidoRoutes(PedidoService pedidoService) {
    this.pedidoService = pedidoService;
  }

  @GetMapping(value="/pedido/all")
	public ResponseEntity<List<Pedido>> getPedidos(){
		return pedidoService.getAll();
	}

  @GetMapping(value="/pedido/{id}")
	public ResponseEntity<Pedido> getPedidoById(@PathVariable int id){
		return pedidoService.getById(id);
	}

  @PostMapping(value="/pedido/create", 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido) {  
    return pedidoService.create(pedido);
	}

  @PutMapping(value="/pedido/update", 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pedido> updatePedido(@RequestBody Pedido pedido) {  
    return pedidoService.update(pedido);
	}

  @DeleteMapping(value="/pedido/delete/{id}")
	public ResponseEntity<String> deletePedido(@PathVariable int id){
		return pedidoService.delete(id);
	}
}
