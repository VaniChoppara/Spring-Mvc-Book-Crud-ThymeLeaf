package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String openAddNew(@ModelAttribute("book") Book book) {
		return "addnew";
	}
	
	@GetMapping("/editbook")
	public String openEdit(Model model,@RequestParam("id") Integer id) {
		Book book = bookService.getBookById(id);
		model.addAttribute("book", book);
		return "addnew";
	}
	
	@GetMapping("/deletebook")
	public String deleteBook(Model model, @RequestParam("id") Integer id) {
		bookService.deleteBook(id);
		List<Book> listAllBooks = bookService.listAllBooks();
		model.addAttribute("listAllBooks", listAllBooks);
		return "listing";
	}
	
	
	@PostMapping("/savebook")
	public String saveBook(Model model, Book book) {
		book.setActive("Y");
		boolean status = bookService.saveBook(book);
		
		if(status) {
			model.addAttribute("successMsg", "Book has been save Successfully!");
			model.addAttribute("book", new Book());
		}
		else
			model.addAttribute("failureMsg", "Error occured in Book saving...");
		return "addnew";
	}
}
