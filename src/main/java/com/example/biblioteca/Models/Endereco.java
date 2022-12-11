package com.example.biblioteca.Models;

public class Endereco {
  private int CEP;
  private int numero;
  private String referencia;

  public void setCEP(int CEP) {
    this.CEP = CEP;
  }
  public void setNumero(int numero) {
    this.numero = numero;
  }

  public void setReferencia(String referencia) {
    this.referencia = referencia;
  }

  public int getCEP() {
    return CEP;
  }
  public int getNumero() {
    return numero;
  }
  public String getReferencia() {
    return referencia;
  }
}
