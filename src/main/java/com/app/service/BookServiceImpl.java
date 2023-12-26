package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Book;
import com.app.repo.BookRepo;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepo bookRepo;
	
	@Override
	public List<Book> listAllBooks() {
		// TODO Auto-generated method stub
		List<Book> listOfBooks = bookRepo.findByActive("Y");
		return listOfBooks;
	}
	@Override
	public boolean saveBook(Book book) {
		Book savedBook = bookRepo.save(book);
		return savedBook.getId()!=null?true:false;
	}
	@Override
	public Book getBookById(Integer id) {
		// TODO Auto-generated method stub
		 Optional<Book> findById = bookRepo.findById(id);
		 if(findById.isPresent())
			 return findById.get();
		 return null;
	}
	@Override
	public void deleteBook(Integer id) {
		Optional<Book> bookById = bookRepo.findById(id);
		 if(bookById.isPresent()) {
			 Book book=bookById.get();
			 book.setActive("N");
			 bookRepo.save(book);
		 }
	}
	
	


}
