package com.example.demo.Models;

import java.util.List;

public class Livro {
  
  private int id;
  private String nome;
  private List<Autor> autores;
  private Editora editora;

  public void setId(int id) {
    this.id = id;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public void setAutores(List<Autor> autores) {
    this.autores = autores;
  }
  public void setEditora(Editora editora) {
    this.editora = editora;
  }

  public int getId() {
    return id;
  }
  public String getNome() {
    return nome;
  }
  public List<Autor> getAutores() {
    return autores;
  }
  public Editora getEditora() {
    return editora;
  }
}
