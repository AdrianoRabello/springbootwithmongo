package br.com.springwithmongo.test.service;

import br.com.springwithmongo.test.model.Book;
import br.com.springwithmongo.test.repoitory.BookRepository;
import org.springframework.stereotype.Service;

/**
 * @author Adriano Rabello 24/09/2023 16:50:20
 **/

@Service
public class BookService {


    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
