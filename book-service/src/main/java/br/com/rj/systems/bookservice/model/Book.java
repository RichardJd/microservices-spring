package br.com.rj.systems.bookservice.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Entity
public class Book implements Serializable {

    @Serial
    private static final long serialVersionUID = 6919904993229527L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 180)
    private String author;

    @Column(nullable = false, length = 250)
    private String title;

    @Column(name = "launch_date", nullable = false)
    private OffsetDateTime launchDate;

    @Column(nullable = false)
    private BigDecimal price;

    @Transient
    private String currency;

    @Transient
    private String environment;
}
