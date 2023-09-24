package br.com.springwithmongo.test.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

/**
 * @author Adriano Rabello 24/09/2023 12:44:37
 **/

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UUID uuid;
    private String name;
    private String title;


    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }

    private Book(Builder builder) {
        this.name   = builder.name;
        this.title  = builder.title;
        this.uuid   = builder.uuid;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public static class Builder {
        private String name;
        private String title;
        private UUID uuid;

        public Builder name(String name) {
            this.name = name;

            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder uuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}
