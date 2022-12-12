package com.example.biblioteca.Models;

import java.util.List;

public class Livro {
  
  private int id;
  private String titulo;
  private Editora editora;
  private List<Autor> autores;

  public void setId(int id) {
    this.id = id;
  }
  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }
  public void setEditora(Editora editora) {
    this.editora = editora;
  }
  public void setAutores(List<Autor> autores) {
    this.autores = autores;
  }

  public int getId() {
    return id;
  }
  public String getTitulo() {
    return titulo;
  }
  public Editora getEditora() {
    return editora;
  }
  public List<Autor> getAutores() {
    return autores;
  }
}
