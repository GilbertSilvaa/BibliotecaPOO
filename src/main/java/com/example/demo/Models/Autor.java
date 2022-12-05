package com.example.demo.Models;

public class Autor {
  private int id;
  private String nome;
  private String sobrenome;

  public void setId(int id) {
    this.id = id;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
  }

  public int getId() {
    return id;
  }
  public String getNome() {
    return nome;
  }
  public String getSobrenome() {
    return sobrenome;
  }
}
