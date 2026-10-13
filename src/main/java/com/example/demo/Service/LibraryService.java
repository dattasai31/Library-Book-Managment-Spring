package com.example.demo.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.ExceptionHandling.BookNotFound;
import com.example.demo.ExceptionHandling.DuplicateBookFound;
import com.example.demo.ExceptionHandling.NoIssuedBooks;
import com.example.demo.ExceptionHandling.NoOverdueBooks;
import com.example.demo.Model.Library;

@Service
public class LibraryService {
	private static final int duedays=14;
	private static final int fineamount=5;
	private List<Library> books=new ArrayList<>();
	public LibraryService()
	{
		books.add(new Library(101,"1984","George Orwell",0,true,null,null));
		books.add(new Library(102,"War and Peace","Leo Tolstoy",0,true,null,null));
		books.add(new Library(103,"Pride and Prejudice","Jane Austen",0,true,null,null));
	}
	
	// method for showing all books
	public List<Library> showBooks()
	{
		return books;
	}
	
	// method for searching a book by ID
	public Library searchBookByID(int id)
	{
		for(Library li:books)
		{
			if(li.getBookID()==id)
			{
				return li;
			}
		}
		throw new BookNotFound("No book exists with this ID");
	}
	
	// method for adding a book
	public String addBook(Library lib)
	{
		for(Library li:books)
		{
			if(li.getBookID()==lib.getBookID())
			{
				throw new DuplicateBookFound("Another book already exists with this ID");
			}
		}
		lib.setAvaliability(true);
		lib.setIssuedate(null);
		lib.setDuedate(null);
		lib.setFine(0);
		books.add(lib);
		return "Book added successfully";
	}
	
	// method for issuing a book
	public String issueBook(int id)
	{
		Library book=searchBookByID(id);
		if(!book.isAvaliability())
		{
			return "This book is already issued";
		}
		book.setAvaliability(false);
		book.setIssuedate(LocalDate.now());
		book.setDuedate(book.getIssuedate().plusDays(duedays));
		return "Book issued successfully. Due date is: "+book.getDuedate();
	}
	
	//method for returning a book
	public String bookReturn(int id)
	{
		Library book=searchBookByID(id);
		if(book.isAvaliability())
		{
			return "This book is not issued";
		}
		book.setAvaliability(true);
		if(LocalDate.now().isAfter(book.getDuedate()))
		{
			long duedays=ChronoUnit.DAYS.between(book.getDuedate(), LocalDate.now());
			book.setFine(fineamount*duedays);
		}
		book.setDuedate(null);
		book.setIssuedate(null);
		double fine=book.getFine();
		book.setFine(0);
		return "Book returned successfully - Fine amount: "+fine;	
	}
	

	// method for deleting a book
	public String deleteBook(int id)
	{
		Library book=searchBookByID(id);
		books.remove(book);
		return "Book deleted successfully";
	}
	
	// method for showing issued books
	public List<Library> showIssuedBooks()
	{
		List<Library> issuedbooks=new ArrayList<>();
		for(Library lib:books)
		{
			
			if(!(lib.isAvaliability()))
			{
				issuedbooks.add(lib);
			}
		}
		if(issuedbooks.isEmpty())
		{
			throw new NoIssuedBooks("No books are issued yet");
		}
		return issuedbooks;
	}
	
	
	// method for showing overdue books
	public List<Library> showOverDueBooks()
	{
		List<Library> overduebooks=new ArrayList<>();
		for(Library lib:books)
		{
			if(!(lib.isAvaliability()))
			{
				if(LocalDate.now().isAfter(lib.getDuedate()))
				{
					overduebooks.add(lib);
				}
			}
		}
		if(overduebooks.isEmpty())
		{
			throw new NoOverdueBooks("No books are over due yet");
		}
		return overduebooks;
	}
	
	// method for showing how many books in library
	public String countBooks()
	{
		return "Total books in this library: "+books.size();
	}

}
