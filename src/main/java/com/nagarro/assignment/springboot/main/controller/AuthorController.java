package com.nagarro.assignment.springboot.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.assignment.springboot.main.model.AuthorsModel;
import com.nagarro.assignment.springboot.main.service.AuthorServices;

@RestController
public class AuthorController {
	
	@Autowired
	private AuthorServices authorservice;

	@GetMapping("/getauthors")
	public ResponseEntity<List<AuthorsModel>> getAuthors() {
		
		List<AuthorsModel> list =  this.authorservice.getAllAuthors();
		
		if(list.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.of(Optional.of(list));
	}
	
	@GetMapping("/getauthor/{authorid}")
	public ResponseEntity<AuthorsModel> getAuthor(@PathVariable("authorid")int authorid) {	
		
		AuthorsModel author = this.authorservice.getAuthorById(authorid);
		
		if(author==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.of(Optional.of(author));
		
	}
	
	@PostMapping("/addauthor")
	public ResponseEntity<AuthorsModel> addAuthor(@RequestBody AuthorsModel authorsdata) {
		
		try {
			AuthorsModel author = this.authorservice.addAuthor(authorsdata);
			
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@DeleteMapping("/deleteauthor/{authorid}")
	public ResponseEntity<Void> deleteAuthor(@PathVariable("authorid")int authorid) {
		try {
			this.authorservice.deleteAuthor(authorid);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/editauthor/{authorid}")
	public ResponseEntity<AuthorsModel> editAuthor(@RequestBody AuthorsModel author, @PathVariable("authorid")int authorid) {
		try {
			this.authorservice.editAuthor(author,authorid);
			return ResponseEntity.ok().body(author);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
