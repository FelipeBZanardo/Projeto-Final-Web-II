package tech.ada.minhaquina.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tech.ada.minhaquina.api.aposta.ApostaDTO;
import tech.ada.minhaquina.api.aposta.ApostaModel;
import tech.ada.minhaquina.api.sorteio.SorteioModel;
import tech.ada.minhaquina.api.sorteio.SorteioService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SorteioDTO {

    @JsonProperty("numero")
    private Integer numeroSorteio;
    private Integer numeroConcursoProximo;
    private boolean acumulado;
    private LocalDate dataSorteio;
    private LocalDate dataProximoSorteio;
    @JsonProperty("listaDezenas")
    private int[] dezenasSorteadas;
    @JsonProperty("listaRateioPremio")
    private List<Premio> premios;
    private BigDecimal valorAcumuladoProximoConcurso;

    @JsonProperty("dataApuracao")
    private void convertDate(String dataString){
        this.dataSorteio = LocalDate.parse(dataString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @JsonProperty("dataProximoConcurso")
    private void convertDateProximoSorteio(String dataString){
        this.dataProximoSorteio = LocalDate.parse(dataString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public SorteioDTO(SorteioModel sorteioModel){
        this(sorteioModel.getNumeroSorteio(), sorteioModel.getNumeroConcursoProximo(),
                sorteioModel.isAcumulado(), sorteioModel.getDataSorteio(),
                sorteioModel.getDataProximoSorteio(), sorteioModel.getDezenasSorteadas(),
                Premio.convertStringToPremio(sorteioModel.getPremios()), sorteioModel.getValorAcumuladoProximoConcurso());
    }

    public static SorteioModel convertToModel(SorteioModel sorteioModel, SorteioDTO sorteioDTO){
        sorteioModel.setNumeroSorteio(sorteioDTO.getNumeroSorteio());
        sorteioModel.setNumeroConcursoProximo(sorteioDTO.getNumeroConcursoProximo());
        sorteioModel.setAcumulado(sorteioDTO.isAcumulado());
        sorteioModel.setDataSorteio(sorteioDTO.getDataSorteio());
        sorteioModel.setDataProximoSorteio(sorteioDTO.getDataProximoSorteio());
        sorteioModel.setDezenasSorteadas(sorteioDTO.getDezenasSorteadas());
        sorteioModel.setPremios(Premio.convertPremioToString(sorteioDTO.getPremios()));
        sorteioModel.setValorAcumuladoProximoConcurso(sorteioDTO.getValorAcumuladoProximoConcurso());
        return sorteioModel;
    }



}
