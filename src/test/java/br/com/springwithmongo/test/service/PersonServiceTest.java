package br.com.springwithmongo.test.service;

import br.com.springwithmongo.test.model.Person;
import br.com.springwithmongo.test.repoitory.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {


    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;


    @Test
    public void souldThowExcelptionWhenEmailHasBeenTaken() {
        Person person = new Person("Adriano", "adrianor.rabello@hotmail.com");
        Mockito.when(personRepository.findPersonByEmail(person.getEmail())).thenReturn(Optional.of(person));
        BDDMockito.given(personRepository.findPersonByEmail(person.getEmail())).willReturn(Optional.of(person));
        assertThrows(IllegalArgumentException.class, () -> personService.save(person));
        assertThatThrownBy(() -> personService.save(person))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("Person with e-mail %s has been taken ", person.getEmail()));

        Mockito.verify(personRepository,Mockito.never()).save(Mockito.any());
    }

    @Test
    public void shouldSavePerson() {
        Person person = new Person("Adriano", "adrianor.rabello@hotmail.com");
        Mockito.when(personRepository.save(person)).thenReturn(person);
        assertThat(personService.save(person)).isNotNull();
    }

    @Test
    public void shouldDeleteById(){
        Person person = new Person("Person Test", "adrianor.rabello@hotmail.com");
        Mockito.when(personRepository.findById(Mockito.any())).thenReturn(Optional.of(person));
        personService.delete(1l);
        Mockito.verify(personRepository).deleteById(Mockito.any());
    }

    @Test
    public void shouldThowExceptionWhenDeleteById(){
        Assertions.assertThatThrownBy(() -> personService.delete(1l))
                        .isInstanceOf(IllegalArgumentException.class)
                                .hasMessage(String.format("There is no object for id %s", 1l));
        Mockito.verify(personRepository,Mockito.never()).deleteById(Mockito.any());
    }
}
