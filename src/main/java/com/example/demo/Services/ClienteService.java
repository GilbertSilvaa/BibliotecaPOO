package com.example.demo.Services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Cliente;
import com.example.demo.Util.SqlOperacoes;

@Service
public class ClienteService {

  private final List<Cliente> clientes = new ArrayList<>();

  public ClienteService() {}
  
  public ResponseEntity<List<Cliente>> getAll() {
    return ResponseEntity.ok(clientes);
  }

  public ResponseEntity<Cliente> create(Cliente cliente) {
    String sql = String.format("insert into clientes (nome, email, telefone) values ('%s', '%s', %d)", 
      cliente.getNome(), 
      cliente.getEmail(), 
      cliente.getTelefone()
    );

    SqlOperacoes.executar(sql);
    return ResponseEntity.ok(cliente);
  }
}
