package tech.ada.apicaixa.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Resultado {

    @JsonProperty("numero")
    private Integer numeroSorteio;
    private boolean acumulado;
    private LocalDate dataSorteio;
    @JsonProperty("listaDezenas")
    private List<Integer> dezenasSorteadas;
    @JsonProperty("listaRateioPremio")
    private List<Premio> premios;
    private BigDecimal valorAcumuladoProximoConcurso;

    @JsonProperty("dataApuracao")
    private void convertDate(String dataString){
        this.dataSorteio = LocalDate.parse(dataString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
