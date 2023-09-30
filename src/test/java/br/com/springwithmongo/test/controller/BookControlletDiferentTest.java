package br.com.springwithmongo.test.controller;

import br.com.springwithmongo.test.model.Book;
import br.com.springwithmongo.test.repoitory.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Adriano Rabello 30/09/2023 18:06:13
 **/

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControlletDiferentTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;


    @MockBean
    private BookRepository bookRepository;

    @TestConfiguration
    static class Config {

        @Bean
        public RestTemplateBuilder restTemplateBuilder(){
            return new RestTemplateBuilder();
        }

    }


    @Test
    public void shouldListAllBooks(){
        BDDMockito.when(bookRepository.findAll()).thenReturn(List.of(new Book()));
         restTemplate = restTemplate.withBasicAuth("adriano", "1234");
        ResponseEntity<Book[]> forEntity = restTemplate.getForEntity("/books", Book[].class);
        Assertions.assertEquals(forEntity.getStatusCode().value(), HttpStatus.OK.value());
    }

}
