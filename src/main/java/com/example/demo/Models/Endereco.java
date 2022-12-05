package com.example.demo.Models;

public class Endereco {
  private int CEP;
  private int NumeroResidencia;
  private String Referencia;

  public void setCEP(int CEP) {
    this.CEP = CEP;
  }
  public void setNumeroResidencia(int NumeroResidencia) {
    this.NumeroResidencia = NumeroResidencia;
  }

  public void setReferencia(String Referencia) {
    this.Referencia = Referencia;
  }

  public int getCEP() {
    return CEP;
  }
  public int getNumeroResidencia() {
    return NumeroResidencia;
  }
  public String getReferencia() {
    return Referencia;
  }
}
