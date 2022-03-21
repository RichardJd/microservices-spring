package br.com.rj.systems.cambioservice.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Cambio implements Serializable {

    @Serial
    private static final long serialVersionUID = -3076553482468455175L;

    private final Long id;

    private final String from;

    private final String to;

    private final BigDecimal conversionFactor;

    private final BigDecimal convertedValue;

    private final String environment;
}
