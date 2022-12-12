package com.example.biblioteca.Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.biblioteca.Models.Livro;
import com.example.biblioteca.Models.Pedido;
import com.example.biblioteca.Models.Usuario;
import com.example.biblioteca.Util.SqlOperacoes;

@Service
public class PedidoService implements CrudService<Pedido> {
  
  public PedidoService() {}

  // busca todos os pedidos
  public ResponseEntity<List<Pedido>> getAll() { 
    UsuarioService usuarioService = new UsuarioService();
    LivroService livroService = new LivroService();

    List<Pedido> pedidos = new ArrayList<>();
    
    String query = "select " +
    " pedidos.id as pedido_id," +
    " usuarios.id as usuario_id" +
    " from pedidos" +
    " inner join usuarios on usuarios.id = pedidos.id_usuario order by pedidos.id";

    ResultSet pedidosResponse = SqlOperacoes.consulta(query);

    try {
      while(pedidosResponse.next()) {
        Pedido pedido = new Pedido();
        List<Livro> livros = new ArrayList<>();

        Usuario usuario = usuarioService.getById(pedidosResponse.getInt("usuario_id")).getBody();
        pedido.setUsuario(usuario);

        // consulta livros
        String query_livros = "select " +
        "id_livro as livro_id, " +
        "quantidade_exemplares as qtd_exemplares " +
        "from pedido_itens where id_pedido = %d";

        ResultSet livrosResponse = SqlOperacoes.consulta(String.format(query_livros, 
          pedidosResponse.getInt("pedido_id")
        ));

        try {
          while(livrosResponse.next()) {
            Livro livro = livroService.getById(livrosResponse.getInt("livro_id")).getBody();

            if(livro != null)
              livro.setQuantidade(livrosResponse.getInt("qtd_exemplares"));

            livros.add(livro);
          }
        } 
        catch (Exception e) {
          e.getStackTrace();
        }

        pedido.setId(pedidosResponse.getInt("pedido_id"));
        pedido.setLivros(livros);
        pedido.setUsuario(usuario);

        pedidos.add(pedido);
      }
    }
    catch(Exception e) {
      e.getStackTrace();
    }

    return ResponseEntity.ok(pedidos);
  }

  // busca pedido por id
  public ResponseEntity<Pedido> getById(int idPedido) { 
    Pedido pedido = new Pedido();

    UsuarioService usuarioService = new UsuarioService();
    LivroService livroService = new LivroService();

    String query = "select " +
    " usuarios.id as usuario_id" +
    " from pedidos" +
    " inner join usuarios on usuarios.id = pedidos.id_usuario" +
    " where pedidos.id = %d";

    ResultSet pedidoResponse = SqlOperacoes.consulta(String.format(query, idPedido));

    try {
      while(pedidoResponse.next()) {
        List<Livro> livros = new ArrayList<>();

        Usuario usuario = usuarioService.getById(pedidoResponse.getInt("usuario_id")).getBody();
        pedido.setUsuario(usuario);

        // consulta livros
        String query_livros = "select " +
        "id_livro as livro_id, " +
        "quantidade_exemplares as qtd_exemplares " +
        "from pedido_itens where id_pedido = %d";

        ResultSet livrosResponse = SqlOperacoes.consulta(String.format(query_livros, 
          idPedido
        ));

        try {
          while(livrosResponse.next()) {
            Livro livro = livroService.getById(livrosResponse.getInt("livro_id")).getBody();

            if(livro != null)
              livro.setQuantidade(livrosResponse.getInt("qtd_exemplares"));

            livros.add(livro);
          }
        } 
        catch (Exception e) {
          e.getStackTrace();
        }

        pedido.setId(idPedido);
        pedido.setLivros(livros);
        pedido.setUsuario(usuario);
      }
    }
    catch(Exception e) {
      e.getStackTrace();
    }

    return ResponseEntity.ok(pedido);
  }

  // criar pedido
  public ResponseEntity<Pedido> create(Pedido pedido) { 
    
    String query = "insert into pedidos (data_pedido, id_usuario) values (now(), %d)";

    SqlOperacoes.executar(String.format(query, pedido.getUsuario().getId()));

    ResultSet idPedido = SqlOperacoes.consulta("select max(id) as id_pedido from pedidos");

    try {
      while(idPedido.next()) {
        pedido.setId(idPedido.getInt("id_pedido"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    // inserir livros do pedido
    for(Livro livro: pedido.getLivros()) {
      String query_livros = "insert into pedido_itens " +
      " (id_pedido, id_livro, quantidade_exemplares)" +
      " values (%d, %d, %d)";

      SqlOperacoes.executar(String.format(query_livros, 
        pedido.getId(),
        livro.getId(),
        livro.getQuantidade()
      ));
    }

    return ResponseEntity.ok(pedido);
  }

  // atualizar pedido
  public ResponseEntity<Pedido> update(Pedido pedido) { 
    
    String query = "update pedidos set id_usuario = %d";

    SqlOperacoes.executar(String.format(query, pedido.getUsuario().getId()));

    // deletar relacionamento pedido - livro
    SqlOperacoes.executar(String.format("delete from pedido_itens where id_pedido = %d", 
      pedido.getId()
    ));

    // reinserir livros do pedido
    for(Livro livro: pedido.getLivros()) {
      String query_livros = "insert into pedido_itens " +
      " (id_pedido, id_livro, quantidade_exemplares)" +
      " values (%d, %d, %d)";

      SqlOperacoes.executar(String.format(query_livros, 
        pedido.getId(),
        livro.getId(),
        livro.getQuantidade()
      ));
    }

    return ResponseEntity.ok(pedido);
  }

  // deletar pedido
  public ResponseEntity<String> delete(int idPedido) {

    String query = String.format("call P_PEDIDO_DELETAR(%d)", idPedido);

    SqlOperacoes.executar(query);

    return ResponseEntity.ok("pedido deletado com sucesso");
  }
}
