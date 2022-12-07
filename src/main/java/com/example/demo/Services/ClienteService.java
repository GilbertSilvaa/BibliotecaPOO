package com.example.demo.Services;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Cliente;
import com.example.demo.Util.SqlOperacoes;

@Service
public class ClienteService {

  public ClienteService() {}
  
  public ResponseEntity<List<Cliente>> getAll(){
    List<Cliente> clientes = new ArrayList<>();

    String sql = "select * from clientes";
    ResultSet clienteResponse = SqlOperacoes.consulta(sql);

    try {
      while(clienteResponse.next()) {
        Cliente cliente = new Cliente();
        
        cliente.setId(clienteResponse.getInt("id"));
        cliente.setNome(clienteResponse.getString("nome"));
        cliente.setEmail(clienteResponse.getString("email"));
        cliente.setTelefone(clienteResponse.getLong("telefone"));

        clientes.add(cliente);
      }
    }catch(Exception e) {
      e.getStackTrace();
    }

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
