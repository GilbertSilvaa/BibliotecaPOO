package com.example.biblioteca.Services;
import java.sql.ResultSet;
import java.sql.SQLException;
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
  
  // busca todos os usuarios
  public ResponseEntity<List<Usuario>> getAll(){
    List<Usuario> usuarios = new ArrayList<>();
    
    String query = "select " +
    " usuarios.id as usuario_id," +
    " usuarios.nome as usuario_nome," +
    " enderecos.cep as endereco_cep," +
    " enderecos.numero as endereco_numero," +
    " enderecos.referencia as endereco_referencia" +
    " from usuarios " +
    " inner join enderecos on usuarios.id_endereco = enderecos.id";

    ResultSet usuariosResponse = SqlOperacoes.consulta(query);

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
    }
    catch(Exception e) {
      e.getStackTrace();
    }

    return ResponseEntity.ok(usuarios);
  }

  // busca usuario por id
  public ResponseEntity<Usuario> getById(int idUsuario){
    Usuario usuario  = new Usuario();
    Endereco endereco = new Endereco();

    String pre_query = "select " +
    " usuarios.id as usuario_id," +
    " usuarios.nome as usuario_nome," +
    " enderecos.cep as endereco_cep," +
    " enderecos.numero as endereco_numero," +
    " enderecos.referencia as endereco_referencia" +
    " from usuarios " +
    " inner join enderecos on usuarios.id_endereco = enderecos.id" +
    " where usuarios.id = %d";

    String query = String.format(pre_query, idUsuario);

    ResultSet usuarioResponse = SqlOperacoes.consulta(query);

    try {
      while(usuarioResponse.next()) {
        endereco.setCEP(usuarioResponse.getInt("endereco_cep"));
        endereco.setNumero(usuarioResponse.getInt("endereco_numero"));
        endereco.setReferencia(usuarioResponse.getString("endereco_referencia"));

        usuario.setId(usuarioResponse.getInt("usuario_id"));
        usuario.setNome(usuarioResponse.getString("usuario_nome"));
        usuario.setEndereco(endereco);
      }
    }
    catch(Exception e){
      e.getStackTrace();
    }

    return ResponseEntity.ok(usuario);
  }
  
  // cria usuario
  public ResponseEntity<Usuario> create(Usuario usuario) {

    String query = String.format("call P_USUARIO_INSERIR('%s', %d, %d, '%s')", 
      usuario.getNome(),
      usuario.getEndereco().getCEP(),
      usuario.getEndereco().getNumero(),
      usuario.getEndereco().getReferencia()
    );
    
    SqlOperacoes.executar(query);

    ResultSet idUsuario = SqlOperacoes.consulta("select max(id) as id_usuario from usuarios");

    try {
      while(idUsuario.next()) {
        usuario.setId(idUsuario.getInt("id_usuario"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return ResponseEntity.ok(usuario);
  }

  // atualizar usuario
  public ResponseEntity<Usuario> update(Usuario usuario) {

    String query = String.format("call P_USUARIO_ATUALIZAR(%d,'%s', %d, %d, '%s')", 
      usuario.getId(),
      usuario.getNome(),
      usuario.getEndereco().getCEP(),
      usuario.getEndereco().getNumero(),
      usuario.getEndereco().getReferencia()
    );

    SqlOperacoes.executar(query);

    return ResponseEntity.ok(usuario);
  }

  // deletar usuario
  public ResponseEntity<String> delete(int idUsuario) {

    String query = String.format("call P_USUARIO_DELETAR(%d)", idUsuario);

    SqlOperacoes.executar(query);

    return ResponseEntity.ok("usuario deletado com sucesso");
  }
}