package br.com.springwithmongo.test.controller;

import br.com.springwithmongo.test.datapoll.BookDataPoll;
import br.com.springwithmongo.test.model.Book;
import br.com.springwithmongo.test.repoitory.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void shouldRetornListOfBooks() throws Exception {
        List<Book> books = List.of(new Book("Livro1"), new Book("Livro2"));
        Mockito.when(bookRepository.findAll()).thenReturn(books);

        ResultActions response = mockMvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(books)));


        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", CoreMatchers.is(2)))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void souldTestFinfBookById() throws Exception {
        Book book = BookDataPoll.createBook();
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(book));

        mockMvc.perform(get(String.format("/books/%s",1)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(new Gson().toJson(book)));

    }

    @Test
    public void shouldSaveBook() throws Exception {
        Book book = BookDataPoll.createBook();
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
        mockMvc.perform(post(String.format("/books"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(book)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(new Gson().toJson(book)))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()));

    }


}
