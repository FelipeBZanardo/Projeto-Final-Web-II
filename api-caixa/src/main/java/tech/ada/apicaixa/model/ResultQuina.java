package tech.ada.apicaixa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResultQuina {
    private String numero;
    private String dataApuracao;
    private List<String> listaDezenas;
    @JsonProperty("valorAcumuladoConcurso_0_5")
    private String valorPremio;

}
