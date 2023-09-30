package br.com.springwithmongo.test.datapoll;

import br.com.springwithmongo.test.model.Book;

import java.util.UUID;

/**
 * @author Adriano Rabello 30/09/2023 16:38:50
 **/
public class BookDataPoll {


    public static Book createBook(){
        return new Book.Builder().name("Book one").title("Book one").uuid(UUID.randomUUID()).build();
    }
}
