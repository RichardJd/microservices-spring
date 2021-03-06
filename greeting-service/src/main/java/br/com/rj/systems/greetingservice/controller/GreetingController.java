package br.com.rj.systems.greetingservice.controller;

import br.com.rj.systems.greetingservice.configuration.GreetingConfiguration;
import br.com.rj.systems.greetingservice.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("greeting")
public class GreetingController {

    private static final String template = "%s %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private GreetingConfiguration greetingConfiguration;

    @GetMapping
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        if (name.isEmpty()) {
            name = greetingConfiguration.defaultValue();
        }
        return new Greeting(counter.incrementAndGet(),
                String.format(template, greetingConfiguration.greeting(), name));
    }
}
