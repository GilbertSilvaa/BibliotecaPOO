package com.example.biblioteca.Models;

public class RegistroComEndereco {
    private int id;
    private String nome;
    Endereco endereco;

    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
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
    public Endereco getEndereco() {
        return endereco;
    }
}
