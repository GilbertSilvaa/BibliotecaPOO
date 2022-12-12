package com.example.biblioteca.Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.biblioteca.Models.Autor;
import com.example.biblioteca.Models.Editora;
import com.example.biblioteca.Models.Endereco;
import com.example.biblioteca.Models.Livro;
import com.example.biblioteca.Util.SqlOperacoes;

@Service
public class LivroService {
  
  public LivroService() {}

  // busca todos os livros
  public ResponseEntity<List<Livro>> getAll() {
    List<Livro> livros = new ArrayList<>();

    String query = "select" +
    " livros.id as livro_id," +
    " livros.titulo as livro_titulo," +
    " livros.qtd_exemplares as livro_qtd_exemplares," +
    " editoras.id as editora_id," +
    " editoras.nome as editora_nome," +
    " enderecos.cep as endereco_editora_cep," +
    " enderecos.numero as endereco_editora_numero," +
    " enderecos.referencia as endereco_editora_referencia" +
    " from livros" +
    " inner join editoras on editoras.id = livros.id_editora" +
    " inner join enderecos on enderecos.id = editoras.id_endereco";

    ResultSet livrosResponse = SqlOperacoes.consulta(query);

    try {
      while(livrosResponse.next()) {
        Livro livro =  new Livro();
        Endereco enderecoEditora = new Endereco();
        Editora editora = new Editora();
        List<Autor> autores =  new ArrayList<>();

        enderecoEditora.setCEP(livrosResponse.getInt("endereco_editora_cep"));
        enderecoEditora.setNumero(livrosResponse.getInt("endereco_editora_numero"));
        enderecoEditora.setReferencia(livrosResponse.getString("endereco_editora_referencia"));

        editora.setId(livrosResponse.getInt("editora_id"));
        editora.setNome(livrosResponse.getString("editora_nome"));
        editora.setEndereco(enderecoEditora);

        String pre_query_autores = "select " +
        "autores.id as autor_id,  " +
        "autores.nome as autor_nome " +
        "from autores  " +
        "inner join livro_autor on livro_autor.id_autor = autores.id " +
        "where livro_autor.id_livro = %d";

        String query_autores = String.format(pre_query_autores, livrosResponse.getInt("livro_id"));

        ResultSet autoresResponse = SqlOperacoes.consulta(query_autores);

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

        livro.setId(livrosResponse.getInt("livro_id"));
        livro.setTitulo(livrosResponse.getString("livro_titulo"));
        livro.setEditora(editora);
        livro.setAutores(autores);
        livro.setQuantidade(livrosResponse.getInt("livro_qtd_exemplares"));

        livros.add(livro);
      }
    }
    catch(Exception e) {
      e.getStackTrace();
    }

    return ResponseEntity.ok(livros);
  }

  // busca livro por id
  public ResponseEntity<Livro> getById(int idLivro) { 
    Livro livro = new Livro();

    String query = "select" +
    " livros.id as livro_id," +
    " livros.titulo as livro_titulo," +
    " livros.qtd_exemplares as livro_qtd_exemplares," +
    " editoras.id as editora_id," +
    " editoras.nome as editora_nome," +
    " enderecos.cep as endereco_editora_cep," +
    " enderecos.numero as endereco_editora_numero," +
    " enderecos.referencia as endereco_editora_referencia" +
    " from livros" +
    " inner join editoras on editoras.id = livros.id_editora" +
    " inner join enderecos on enderecos.id = editoras.id_endereco" +
    " where livros.id = %d";

    ResultSet livroResponse = SqlOperacoes.consulta(String.format(query, idLivro));

    try {
      while(livroResponse.next()) {
        Endereco enderecoEditora = new Endereco();
        Editora editora = new Editora();
        List<Autor> autores =  new ArrayList<>();

        enderecoEditora.setCEP(livroResponse.getInt("endereco_editora_cep"));
        enderecoEditora.setNumero(livroResponse.getInt("endereco_editora_numero"));
        enderecoEditora.setReferencia(livroResponse.getString("endereco_editora_referencia"));

        editora.setId(livroResponse.getInt("editora_id"));
        editora.setNome(livroResponse.getString("editora_nome"));
        editora.setEndereco(enderecoEditora);

        String pre_query_autores = "select " +
        "autores.id as autor_id,  " +
        "autores.nome as autor_nome " +
        "from autores  " +
        "inner join livro_autor on livro_autor.id_autor = autores.id " +
        "where livro_autor.id_livro = %d";

        String query_autores = String.format(pre_query_autores, livroResponse.getInt("livro_id"));

        ResultSet autoresResponse = SqlOperacoes.consulta(query_autores);

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

        livro.setId(livroResponse.getInt("livro_id"));
        livro.setTitulo(livroResponse.getString("livro_titulo"));
        livro.setEditora(editora);
        livro.setAutores(autores);
        livro.setQuantidade(livroResponse.getInt("livro_qtd_exemplares"));
      }
    }
    catch(Exception e) {
      e.getStackTrace();
    }

    return ResponseEntity.ok(livro);
  }

  // criar livro
  public ResponseEntity<Livro> create(Livro livro) {

    String query = "insert into livros (titulo, id_editora, qtd_exemplares) values ('%s', %d, %d)";

    SqlOperacoes.executar(String.format(query, 
      livro.getTitulo(), 
      livro.getEditora().getId(),
      livro.getQuantidade()
    ));

    ResultSet idLivro = SqlOperacoes.consulta("select max(id) as id_livro from livros");

    try {
      while(idLivro.next()) {
        livro.setId(idLivro.getInt("id_livro"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    // inserir autores 
    for(Autor autor: livro.getAutores()) {
      String query_autor = "insert into livro_autor (id_livro, id_autor) values (%d, %d)";

      SqlOperacoes.executar(String.format(query_autor,livro.getId(), autor.getId()));
    }

    return ResponseEntity.ok(livro);
  }

  // atualizar livro
  public ResponseEntity<Livro> update(Livro livro) {

    String query = "update livros set titulo = '%s', id_editora = %d, qtd_exemplares = %d where id = %d";

    SqlOperacoes.executar(String.format(query, 
      livro.getTitulo(), 
      livro.getEditora().getId(), 
      livro.getQuantidade(), 
      livro.getId()
    ));

    // deletar o relacionameto autor - livro
    SqlOperacoes.executar(String.format("delete from livro_autor where id_livro = %d", 
      livro.getId()
    ));

    // reinserir autores 
    for(Autor autor: livro.getAutores()) {
      String query_autor = "insert into livro_autor (id_livro, id_autor) values (%d, %d)";

      SqlOperacoes.executar(String.format(query_autor,livro.getId(), autor.getId()));
    }

    return ResponseEntity.ok(livro);
  }

  // deletar livro
  public ResponseEntity<String> delete(int idLivro) { 

    SqlOperacoes.executar(String.format("call P_LIVRO_DELETAR(%d)", idLivro));

    return ResponseEntity.ok("livro deletado com sucesso");
  }
}
