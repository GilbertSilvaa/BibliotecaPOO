package com.example.biblioteca.Services;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.biblioteca.Models.Endereco;
import com.example.biblioteca.Models.Usuario;
import com.example.biblioteca.Util.SqlOperacoes;

@Service
public class UsuarioService {

  public UsuarioService() {}
  
  public ResponseEntity<List<Usuario>> getAll(){
    List<Usuario> usuarios = new ArrayList<>();

    String queryCliente = "select " +
    " usuarios.id as usuario_id," +
    " usuarios.nome as usuario_nome," +
    " enderecos.cep as endereco_cep," +
    " enderecos.numero as endereco_numero," +
    " enderecos.referencia as endereco_referencia" +
    " from usuarios " +
    " inner join enderecos on usuarios.id_endereco = enderecos.id";

    ResultSet usuariosResponse = SqlOperacoes.consulta(queryCliente);

    try {
      while(usuariosResponse.next()) {
        Usuario usuario = new Usuario();
        Endereco endereco = new Endereco();

        endereco.setCEP(usuariosResponse.getInt("endereco_cep"));
        endereco.setNumero(usuariosResponse.getInt("endereco_numero"));
        endereco.setReferencia(usuariosResponse.getString("endereco_referencia"));

        usuario.setId(usuariosResponse.getInt("usuario_id"));
        usuario.setNome(usuariosResponse.getString("usuario_nome"));
        usuario.setEndereco(endereco);

        usuarios.add(usuario);
      }
    }catch(Exception e) {
      e.getStackTrace();
    }

    return ResponseEntity.ok(usuarios);
  }

  public ResponseEntity<Usuario> create(Usuario cliente) {

    String queryUsuarioCreate = String.format("CALL P_USUARIO_INSERIR('%s', %d, %d, '%s')", 
      cliente.getNome(),
      cliente.getEndereco().getCEP(),
      cliente.getEndereco().getNumero(),
      cliente.getEndereco().getReferencia()
    );
    
    String mensagem = SqlOperacoes.executar(queryUsuarioCreate);

    System.out.println(mensagem);

    return ResponseEntity.ok(cliente);
  }
}
