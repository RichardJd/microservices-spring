package br.com.rj.systems.bookservice.controller;

import br.com.rj.systems.bookservice.model.Book;
import br.com.rj.systems.bookservice.proxy.CambioProxy;
import br.com.rj.systems.bookservice.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book endpoint")
@RequiredArgsConstructor
@RestController
@RequestMapping("book-service")
public class BookController {

    private final Environment environment;

    private final CambioProxy cambioProxy;

    private final BookRepository bookRepository;

    @Operation(summary = "Find a specific book by your ID")
    @GetMapping("/{id}/{currency}")
    public Book finfById(@PathVariable Long id, @PathVariable String currency) {

        var book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        var cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);

        var environmentPort = environment.getProperty("local.server.port");
        book.setEnvironment("Book port: " + environmentPort + " Cambio port: " + cambio.environment());
        book.setPrice(cambio.convertedValue());
        return book;
    }
}
