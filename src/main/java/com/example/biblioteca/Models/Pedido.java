package com.example.biblioteca.Models;

import java.util.List;

public class Pedido {
  
  private int id;
  private Usuario usuario;
  private List<Livro> livros;

  public void setId(int id) {
    this.id = id;
  }
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
  public void setLivros(List<Livro> livros) {
    this.livros = livros;
  }

  public int getId() {
    return id;
  }
  public Usuario getUsuario() {
    return usuario;
  }
  public List<Livro> getLivros() {
    return livros;
  }

}
