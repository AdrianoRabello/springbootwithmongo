package br.com.springwithmongo.test.repoitory;

import br.com.springwithmongo.test.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Adriano Rabello 24/09/2023 12:46:19
 **/
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookByNameContainingIgnoreCase(String name);

}
