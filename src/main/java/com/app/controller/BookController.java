package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.Book;
import com.app.service.BookService;

@Controller
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("/listbooks")
	public ModelAndView listAllBooks() {
		ModelAndView mav = new ModelAndView();
		List<Book> listAllBooks = bookService.listAllBooks();
		mav.addObject("listAllBooks", listAllBooks);
		mav.setViewName("listing");
		return mav;
	}

	@GetMapping("/addbook")
	public ModelAndView openAddNew() {
		ModelAndView mav = new ModelAndView();
		Book book = new Book();
		mav.addObject("book", book);
		mav.setViewName("addnew");
		return mav;
	}
	
	@GetMapping("/editbook")
	public ModelAndView openEdit(@RequestParam("id") Integer id) {
		ModelAndView mav = new ModelAndView();		
		Book book = bookService.getBookById(id);
		mav.addObject("book", book);
		mav.setViewName("addnew");
		return mav;
	}
	
	@GetMapping("/deletebook")
	public ModelAndView deleteBook(@RequestParam("id") Integer id) {
		ModelAndView mav = new ModelAndView();		
		bookService.deleteBook(id);
		List<Book> listAllBooks = bookService.listAllBooks();
		mav.addObject("listAllBooks", listAllBooks);
		mav.setViewName("listing");
		return mav;
	}
	
	
	@PostMapping("/savebook")
	public ModelAndView saveBook(Book book) {
		ModelAndView mav=new ModelAndView();
		book.setActive("Y");
		boolean status = bookService.saveBook(book);
		
		if(status)
			mav.addObject("successMsg", "Book has been save Successfully!");
		else
			mav.addObject("failureMsg", "Error occured in Book saving...");
		
		Book newbook = new Book();
		mav.addObject("book", newbook);
		mav.setViewName("addnew");
		return mav;
	}
}
