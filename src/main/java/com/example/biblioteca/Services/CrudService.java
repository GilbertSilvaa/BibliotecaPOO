package com.example.biblioteca.Services;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface CrudService<T> {

    public ResponseEntity<List<T>> getAll();

    public ResponseEntity<T> getById(int id);

    public ResponseEntity<T> create(T novoObj);

    public ResponseEntity<T> update(T novoObj);

    public ResponseEntity<String> delete(int id);

}
