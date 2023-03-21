package tech.ada.apicaixa.model.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Aposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numeroSorteio;
    private String dezenas;
    private LocalDate dataJogo;
    private LocalDate dataSorteio;
    private Integer pontuacao;
    private BigDecimal valorPremio;
    private boolean acumulou;

    //private User usuario;
}
