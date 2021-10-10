package com.nagarro.assignment.springboot.main.dao;

import org.springframework.data.repository.CrudRepository;

import com.nagarro.assignment.springboot.main.model.AuthorsModel;

public interface AuthorRepository extends CrudRepository<AuthorsModel, Integer>{

	public AuthorsModel findById(int id);
}
