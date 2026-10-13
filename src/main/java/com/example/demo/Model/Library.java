package com.example.demo.Model;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Library {
	@Positive(message="Book ID must be positive")
	private int bookID;
	@NotBlank(message="Book name cannot be empty")
	private String bookname;
	@NotBlank(message="Author name cannot be empty")
	private String author;
	@PositiveOrZero(message="Fine cannot be negative")
	private double fine;
	private boolean avaliability;
	private LocalDate issuedate;
	private LocalDate duedate;
}
