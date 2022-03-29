package br.com.rj.systems.bookservice.response;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public record Cambio(
        Long id,
        String from,
        String to,
        BigDecimal conversionFactor,
        BigDecimal convertedValue,
        String environment
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 8116563941154808077L;
}
