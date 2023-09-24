package br.com.springwithmongo.test.controller;

import br.com.springwithmongo.test.model.Book;
import br.com.springwithmongo.test.repoitory.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Adriano Rabello 24/09/2023 13:03:17
 **/

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping
    public ResponseEntity<?> save() {
        return new ResponseEntity<>(bookRepository.save(new Book.Builder().name("Clean Code").title("Dev").build()), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> get() {
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.findAll());
    }

}
