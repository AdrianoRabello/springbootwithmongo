package br.com.springwithmongo.test.repoitory;

import br.com.springwithmongo.test.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class BookRepositoryTest {


    @Autowired
    private BookRepository bookRepository;


    @Test
    public void shouldFindAbookByName() {

        // given
        String name = "Book for test";
        Book toSave = new Book.Builder()
                .name(name)
                .build();
        bookRepository.save(toSave);
        // when
        Book book = bookRepository.findBookByNameContainingIgnoreCase(name);

        // then
        Assertions.assertNotNull(book.getId());
    }


}
