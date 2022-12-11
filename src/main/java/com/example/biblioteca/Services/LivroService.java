package com.example.biblioteca.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.biblioteca.Models.Livro;

@Service
public class LivroService {
  
  private final List<Livro> livros = new ArrayList<>();

  public LivroService() {}

  public ResponseEntity<List<Livro>> getAll() {
    return ResponseEntity.ok(livros);
  }

  public ResponseEntity<Livro> create(Livro livro) {
    return ResponseEntity.ok(livro);
  }
}
