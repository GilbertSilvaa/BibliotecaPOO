package com.example.biblioteca.Routes;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.biblioteca.Models.Editora;
import com.example.biblioteca.Services.EditoraService;

@RestController
public class EditoraRoutes {
  
  private final EditoraService editoraService;

  public EditoraRoutes(EditoraService editoraService) {
    this.editoraService = editoraService;
  }

  @GetMapping(value="/editora/all")
	public ResponseEntity<List<Editora>> getEditoras(){
		return editoraService.getAll();
	}

  @GetMapping(value="/editora/{id}")
	public ResponseEntity<Editora> getEditoraById(@PathVariable int id){
		return editoraService.getByid(id);
	}

  @PostMapping(value="/editora/create", 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Editora> createEditora(@RequestBody Editora editora) {  
    return editoraService.create(editora);
	}

  @PutMapping(value="/editora/update", 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Editora> updateEditora(@RequestBody Editora editora) {  
    return editoraService.update(editora);
	}

  @DeleteMapping(value="/editora/delete/{id}")
	public ResponseEntity<String> deleteEditora(@PathVariable int id){
		return editoraService.delete(id);
	}
}
