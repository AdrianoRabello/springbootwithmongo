package br.com.springwithmongo.test.controller;

import br.com.springwithmongo.test.model.Book;
import br.com.springwithmongo.test.repoitory.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> save(@RequestBody Book book) {
        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> get() {
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return ResponseEntity.ok(bookRepository.findById(id).get());
    }

}
