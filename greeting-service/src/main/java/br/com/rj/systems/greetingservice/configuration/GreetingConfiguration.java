package br.com.rj.systems.greetingservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties("greeting-service")
public record GreetingConfiguration(
        String greeting,
        String defaultValue
) {
}
