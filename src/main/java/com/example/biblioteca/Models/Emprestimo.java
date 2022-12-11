package com.example.biblioteca.Models;

import java.util.Date;
import java.util.List;

public class Emprestimo {
  private int id;
  private Usuario cliente;
  private List<Livro> livros;
  private Date dataEmprestimo;
  private Date dataDevolucao;

  public void setId(int id) {
    this.id = id;
  }
  public void setCliente(Usuario cliente) {
    this.cliente = cliente;
  }
  public void setLivros(List<Livro> livros) {
    this.livros = livros;
  }
  public void setDataEmprestimo(Date dataEmprestimo) {
    this.dataEmprestimo = dataEmprestimo;
  }
  public void setDataDevolucao(Date dataDevolucao) {
    this.dataDevolucao = dataDevolucao;
  }

  public int getId() {
    return id;
  }
  public Usuario getCliente() {
    return cliente;
  }
  public List<Livro> getLivros() {
    return livros;
  }
  public Date getDataEmprestimo() {
    return dataEmprestimo;
  }
  public Date getDataDevolucao() {
    return dataDevolucao;
  }
}
