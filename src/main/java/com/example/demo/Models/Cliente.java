package com.example.demo.Models;

public class Cliente {
  private int id;
  private String nome;
  private String email;
  private long telefone;
  //private Endereco endereco;

  public void setId(int id) {
    this.id = id;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public void setTelefone(long telefone) {
    this.telefone = telefone;
  }
  /* public void setEndereco(Endereco endereco) {
    this.endereco = endereco;
  } */

  public int getId() {
    return id;
  }
  public String getNome() {
    return nome;
  }
  public String getEmail() {
    return email;
  }
  public long getTelefone() {
    return telefone;
  }
  /* public Endereco getEndereco() {
    return endereco;
  } */
}


