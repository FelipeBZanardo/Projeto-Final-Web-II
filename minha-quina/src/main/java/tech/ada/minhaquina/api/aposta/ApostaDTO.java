package tech.ada.minhaquina.api.aposta;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import tech.ada.minhaquina.api.validation.GameConstraint;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApostaDTO {

    private Integer numeroSorteio;
    @GameConstraint
    private int[] dezenas;
    private LocalDate dataJogo;

    public ApostaDTO(ApostaModel apostaModel){
        this(apostaModel.getNumeroSorteio(), apostaModel.getDezenas(), apostaModel.getDataJogo());
    }

    public static ApostaModel convertToModel(ApostaModel apostaModel, ApostaDTO apostaDTO){
        apostaModel.setNumeroSorteio(apostaDTO.getNumeroSorteio());
        apostaModel.setDezenas(apostaDTO.getDezenas());
        apostaModel.setDataJogo(apostaDTO.getDataJogo());
        return apostaModel;
    }
}
