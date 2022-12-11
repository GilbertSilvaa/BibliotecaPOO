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

}
