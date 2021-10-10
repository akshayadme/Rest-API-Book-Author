package com.nagarro.assignment.springboot.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.assignment.springboot.main.dao.AuthorRepository;
import com.nagarro.assignment.springboot.main.model.AuthorsModel;

@Component
public class AuthorServices {

	@Autowired
	private AuthorRepository authorrepository;
	
	public List<AuthorsModel> getAllAuthors() {
		
		List<AuthorsModel> authors = (List<AuthorsModel>) this.authorrepository.findAll();
		return authors;
	}
	
	public AuthorsModel getAuthorById(int authorid) {
		
		AuthorsModel author = this.authorrepository.findById(authorid);
		return author;
	}

	public AuthorsModel addAuthor(AuthorsModel author) {

		AuthorsModel result = this.authorrepository.save(author);
		return result;
		
	}

	public void deleteAuthor(int authorid) {
		this.authorrepository.deleteById(authorid);
	}

	public void editAuthor(AuthorsModel author, int authorid) {
		
		author.setId(authorid);
		this.authorrepository.save(author);
		
	}
}
