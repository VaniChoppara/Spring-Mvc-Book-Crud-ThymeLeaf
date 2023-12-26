package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Book;
public interface BookRepo extends JpaRepository<Book, Integer>{
	

	public List<Book> findByActive(String active);
}
