package tech.ada.minhaquina.api.resultado;

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
public class ResultadoResponse {
    private Integer numeroSorteio;
    private Integer numeroConcursoProximo;
    private boolean acumulado;
    private LocalDate dataSorteio;
    private List<Integer> dezenasSorteadas;
    private List<Premio> premios;
    private BigDecimal valorAcumuladoProximoConcurso;

}
