package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Library;
import com.example.demo.Service.LibraryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/library")
@Validated
public class LibraryController {
	@Autowired
	private LibraryService service;
	
	@GetMapping("/show")
	public List<Library> showBooks()
	{
		return service.showBooks();
	}
	
	@GetMapping("/search/{id}")
	public Library searchBookByID(@PathVariable int id)
	{
		return service.searchBookByID(id);
	}
	
	@PostMapping("/addbook")
	public String addBook(@Valid @RequestBody Library lib)
	{
		return service.addBook(lib);
	}
	
	@PatchMapping("/issue/{id}")
	public String issue( @PathVariable int id)
	{
		return service.issueBook(id);
	}
	@PatchMapping("/return/{id}")
	public String returnbook( @PathVariable int id)
	{
		return service.bookReturn(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id)
	{
		return service.deleteBook(id);
	}
	
	@GetMapping("/showissued")
	public List<Library> issued()
	{
		return service.showIssuedBooks();
	}
	
	@GetMapping("/showoverdue")
	public List<Library> showoverdue()
	{
		return service.showOverDueBooks();
	}
	
	
	@GetMapping("/count")
	public String count()
	{
		return service.countBooks();
	}
	
	
			

	
	
}
