package br.com.springwithmongo.test.repoitory;

import br.com.springwithmongo.test.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Adriano Rabello 24/09/2023 12:46:19
 **/
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findPersonByEmail(String name);

}
