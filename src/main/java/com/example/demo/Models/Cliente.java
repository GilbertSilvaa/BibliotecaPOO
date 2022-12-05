package com.example.demo.Models;

public class Cliente {
  private int id;
  private String nome;
  private String sobrenome;
  private String email;
  private Endereco endereco;

  public void setId(int id) {
    this.id = id;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public void setEndereco(Endereco endereco) {
    this.endereco = endereco;
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
  public String getEmail() {
    return email;
  }
  public Endereco getEndereco() {
    return endereco;
  }
}


