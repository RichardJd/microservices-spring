package br.com.rj.systems.bookservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Foo-bar endpoint")
@Slf4j
@RestController
@RequestMapping("book-service")
public class FooBarController {

//    @Retry(name = "foo-bar", fallbackMethod = "fallbackMethod")
//    @CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
//    @RateLimiter(name = "default")
    @Bulkhead(name = "default")
    @Operation(summary = "Foo Bar")
    @GetMapping("/foo-bar")
    public String fooBar() {
        log.info("Request to foo-bar is received!");
//        var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
        return "Foo-Bar";
    }

    public String fallbackMethod(Exception ex) {
        return "fallbackMethod foo-bar";
    }
}
