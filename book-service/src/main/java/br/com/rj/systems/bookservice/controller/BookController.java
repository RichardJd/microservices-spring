package br.com.rj.systems.bookservice.controller;

import br.com.rj.systems.bookservice.model.Book;
import br.com.rj.systems.bookservice.proxy.CambioProxy;
import br.com.rj.systems.bookservice.repository.BookRepository;
import br.com.rj.systems.bookservice.response.Cambio;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("book-service")
public class BookController {

    private final Environment environment;

    private final CambioProxy cambioProxy;

    private final BookRepository bookRepository;

    @GetMapping("/{id}/{currency}")
    public Book finfById(@PathVariable Long id, @PathVariable String currency) {

        var book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));



        var cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);

        var environmentPort = environment.getProperty("local.server.port");
        book.setEnvironment(environmentPort);
        book.setPrice(cambio.convertedValue());
        return book;
    }
}
