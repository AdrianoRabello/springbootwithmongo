package br.com.springwithmongo.test.service;

import br.com.springwithmongo.test.model.Book;
import br.com.springwithmongo.test.repoitory.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {


    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;



    @Test
    public void shouldSaveBook(){

        Book book = new Book.Builder()
                .name("Cleancode")
                .title("Clean code book")
                .build();

        bookService.save(book);
        ArgumentCaptor<Book> argumentCaptor = ArgumentCaptor.forClass(Book.class);
        Mockito.verify(bookRepository).save(argumentCaptor.capture());
    }
}
