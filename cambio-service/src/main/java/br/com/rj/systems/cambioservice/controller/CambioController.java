package br.com.rj.systems.cambioservice.controller;

import br.com.rj.systems.cambioservice.model.Cambio;
import br.com.rj.systems.cambioservice.repository.CambioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Tag(name = "Cambio endpoint")
@RequiredArgsConstructor
@RestController
@RequestMapping("cambio-service")
public class CambioController {

    private final Environment environment;

    private final CambioRepository cambioRepository;

    @Operation(summary = "Find cambio by currency!")
    @GetMapping("/{amount}/{from}/{to}")
    public Cambio getCambio(
            @PathVariable BigDecimal amount,
            @PathVariable String from,
            @PathVariable String to
    ) {
        log.info("getCambio is called with: {}, {} and {}", amount, from, to);
        var cambio = cambioRepository.findByFromAndTo(from, to)
                .orElseThrow(() -> new RuntimeException("CurrencyUnsupported"));

        var environmentPort = environment.getProperty("local.server.port");

        BigDecimal conversionFactor = cambio.getConversionFactor();
        BigDecimal convertedValue = conversionFactor.multiply(amount);

        cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));
        cambio.setEnvironment(environmentPort);

        return cambio;
    }
}
