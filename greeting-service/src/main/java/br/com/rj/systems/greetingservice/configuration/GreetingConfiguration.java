package br.com.rj.systems.greetingservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@ConfigurationProperties("greeting-service")
public record GreetingConfiguration(
        String greeting,
        String defaultValue
) {
}
