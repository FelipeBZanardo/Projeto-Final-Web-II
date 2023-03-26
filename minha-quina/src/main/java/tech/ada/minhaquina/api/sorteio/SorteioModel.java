package tech.ada.minhaquina.api.sorteio;

import jakarta.persistence.*;
import lombok.*;
import tech.ada.minhaquina.client.Premio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class SorteioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numeroSorteio;
    private Integer numeroConcursoProximo;
    private boolean acumulado;
    private LocalDate dataSorteio;
    private LocalDate dataProximoSorteio;
    private int[] dezenasSorteadas;
    private String premios;
    private BigDecimal valorAcumuladoProximoConcurso;
}
