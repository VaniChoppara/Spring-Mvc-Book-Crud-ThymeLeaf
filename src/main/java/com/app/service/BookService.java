package com.app.service;

import java.util.List;

import com.app.entity.Book;

public interface BookService {
	
	List<Book> listAllBooks();
	
	boolean saveBook(Book book);
	
	Book getBookById(Integer id);
	
	void deleteBook(Integer id);

}
