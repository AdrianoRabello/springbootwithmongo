package br.com.springwithmongo.test;

import br.com.springwithmongo.test.model.Book;
import br.com.springwithmongo.test.repoitory.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {

		SpringApplication.run(TestApplication.class, args);
	}

}
