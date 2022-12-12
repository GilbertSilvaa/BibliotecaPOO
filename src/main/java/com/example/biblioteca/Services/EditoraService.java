package com.example.biblioteca.Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.biblioteca.Models.Editora;
import com.example.biblioteca.Models.Endereco;
import com.example.biblioteca.Util.SqlOperacoes;

@Service
public class EditoraService {
  
  public EditoraService() {}

  // busca todas editoras
  public ResponseEntity<List<Editora>> getAll() {
    List<Editora> editoras = new ArrayList<>();

    String query = "select " +
    "editoras.id as editora_id, " +
    "editoras.nome as editora_nome, " +
    "enderecos.cep as endereco_cep, " +
    "enderecos.numero as endereco_numero, " +
    "enderecos.referencia as endereco_referencia " +
    "from editoras " +
    "inner join enderecos on editoras.id_endereco = enderecos.id"; 

    ResultSet editorasResponse = SqlOperacoes.consulta(query);

    try {
      while(editorasResponse.next()) {
        Editora editora = new Editora();
        Endereco endereco = new Endereco();

        endereco.setCEP(editorasResponse.getInt("endereco_cep"));
        endereco.setNumero(editorasResponse.getInt("endereco_numero"));
        endereco.setReferencia(editorasResponse.getString("endereco_referencia"));

        editora.setId(editorasResponse.getInt("editora_id"));
        editora.setNome(editorasResponse.getString("editora_nome"));
        editora.setEndereco(endereco);

        editoras.add(editora);
      }
    }
    catch(Exception e) {
      e.getStackTrace();
    }

    return ResponseEntity.ok(editoras);
  }

  // busca editora por id
  public ResponseEntity<Editora> getByid(int idEditora) {
    Editora editora = new Editora();
    Endereco endereco = new Endereco();

    String pre_query = "select " +
    "editoras.id as editora_id, " +
    "editoras.nome as editora_nome, " +
    "enderecos.cep as endereco_cep, " +
    "enderecos.numero as endereco_numero, " +
    "enderecos.referencia as endereco_referencia " +
    "from editoras " +
    "inner join enderecos on editoras.id_endereco = enderecos.id " +
    "where editoras.id = %d"; 

    String query = String.format(pre_query, idEditora);

    ResultSet editoraResponse = SqlOperacoes.consulta(query);

    try {
      while(editoraResponse.next()) {
        endereco.setCEP(editoraResponse.getInt("endereco_cep"));
        endereco.setNumero(editoraResponse.getInt("endereco_numero"));
        endereco.setReferencia(editoraResponse.getString("endereco_referencia"));

        editora.setId(editoraResponse.getInt("editora_id"));
        editora.setNome(editoraResponse.getString("editora_nome"));
        editora.setEndereco(endereco);
      }
    }
    catch(Exception e) {
      e.getStackTrace();
    }  
    
    return ResponseEntity.ok(editora);
  }

  // criar editora
  public ResponseEntity<Editora> create(Editora editora) {

    String query = String.format("call P_EDITORA_INSERIR('%s', %d, %d, '%s')", 
      editora.getNome(),
      editora.getEndereco().getCEP(),
      editora.getEndereco().getNumero(),
      editora.getEndereco().getReferencia()
    );
    
    SqlOperacoes.executar(query);

    ResultSet idEditora = SqlOperacoes.consulta("select max(id) as id_editora from editoras");

    try {
      while(idEditora.next()) {
        editora.setId(idEditora.getInt("id_editora"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return ResponseEntity.ok(editora);
  }

  // atualizar editora
  public ResponseEntity<Editora> update(Editora editora) {

    String query = String.format("call P_EDITORA_ATUALIZAR(%d,'%s', %d, %d, '%s')", 
      editora.getId(),
      editora.getNome(),
      editora.getEndereco().getCEP(),
      editora.getEndereco().getNumero(),
      editora.getEndereco().getReferencia()
    );

    SqlOperacoes.executar(query);

    return ResponseEntity.ok(editora);
  }

  // deletar editora
  public ResponseEntity<String> delete(int idEditora) {

    String query = String.format("call P_EDITORA_DELETAR(%d)", idEditora);

    SqlOperacoes.executar(query);

    return ResponseEntity.ok("editora deletada com sucesso");
  }
}
