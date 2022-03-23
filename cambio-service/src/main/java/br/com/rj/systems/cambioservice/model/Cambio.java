package br.com.rj.systems.cambioservice.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
public class Cambio implements Serializable {

    @Serial
    private static final long serialVersionUID = -3076553482468455175L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_currency", nullable = false, length = 300)
    private String from;

    @Column(name = "to_currency", nullable = false, length = 300)
    private String to;

    @Column(nullable = false)
    private BigDecimal conversionFactor;

    @Transient
    private BigDecimal convertedValue;

    @Transient
    private String environment;
}
