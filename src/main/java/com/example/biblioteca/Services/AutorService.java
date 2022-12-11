package com.example.biblioteca.Services;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.biblioteca.Models.Autor;
import com.example.biblioteca.Util.SqlOperacoes;

@Service
public class AutorService {
  
  public AutorService() {}

  // busca todos autores
  public ResponseEntity<List<Autor>> getAll() {
    List<Autor> autores = new ArrayList<>();

    String query = "select id as autor_id, nome as autor_nome from autores";

    ResultSet autoresResponse = SqlOperacoes.consulta(query);

    try {
      while(autoresResponse.next()) {
        Autor autor = new Autor();

        autor.setId(autoresResponse.getInt("autor_id"));
        autor.setNome(autoresResponse.getString("autor_nome"));

        autores.add(autor);
      }
    }
    catch(Exception e) {
      e.getStackTrace();
    }

    return ResponseEntity.ok(autores);
  }

  // busca autor por id
  public ResponseEntity<Autor> getById(int idAutor) {
    Autor autor = new Autor();

    String pre_query = "select id as autor_id, nome as autor_nome from autores where id = %d";
    String query = String.format(pre_query, idAutor);

    ResultSet autorResponse = SqlOperacoes.consulta(query);

    try {
      while(autorResponse.next()) {
        autor.setId(autorResponse.getInt("autor_id"));
        autor.setNome(autorResponse.getString("autor_nome"));
      }
    }
    catch(Exception e) {
      e.getStackTrace();
    }

    return ResponseEntity.ok(autor);
  }

  // criar autor
  public ResponseEntity<Autor> create(Autor autor) {

    String query = String.format("insert into autores (nome) values ('%s')", autor.getNome());

    SqlOperacoes.executar(query);

    return ResponseEntity.ok(autor);
  }

  // atualizar autor
  public ResponseEntity<Autor> update(Autor autor) {

    String query = String.format("update autores set nome = '%s' where id = %d", 
      autor.getNome(),
      autor.getId()
    );

    SqlOperacoes.executar(query);

    return ResponseEntity.ok(autor);
  }

  // deletar autor
  public ResponseEntity<String> delete(int idAutor) { 
    String query = String.format("call P_AUTOR_DELETAR(%d)", idAutor);

    SqlOperacoes.executar(query);

    return ResponseEntity.ok("autor deletado com sucesso");
  }
}
