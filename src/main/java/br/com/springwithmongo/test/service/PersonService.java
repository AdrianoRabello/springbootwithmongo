package br.com.springwithmongo.test.service;

import br.com.springwithmongo.test.model.Person;
import br.com.springwithmongo.test.repoitory.PersonRepository;
import org.springframework.stereotype.Service;

/**
 * @author Adriano Rabello 24/09/2023 16:50:20
 **/

@Service
public class PersonService {


    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public Person save(Person person) {

        if (personRepository.findPersonByEmail(person.getEmail()).isPresent()) {
            throw new IllegalArgumentException(String.format("Person with e-mail %s has been taken ", person.getEmail()));
        }

        return personRepository.save(person);
    }

    public void delete(Long id){

        if(!personRepository.findById(id).isPresent()){
            throw  new IllegalArgumentException(String.format("There is no object for id %s", id));
        }
        personRepository.deleteById(id);
    }
}
