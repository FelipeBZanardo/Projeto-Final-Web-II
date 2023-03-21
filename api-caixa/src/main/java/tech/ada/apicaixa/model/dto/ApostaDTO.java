package tech.ada.apicaixa.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApostaDTO {

    private Long numeroSorteio;
    private String dezenas;
    private LocalDate dataJogo;
}
