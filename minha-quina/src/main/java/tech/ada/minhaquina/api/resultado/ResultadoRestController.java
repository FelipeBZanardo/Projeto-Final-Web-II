package tech.ada.minhaquina.api.resultado;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/minha-quina/api/v1/apostas/{apostaId}/resultados")
public class ResultadoRestController {

    private final ResultadoService resultadoService;

    public ResultadoRestController(ResultadoService resultadoService) {
        this.resultadoService = resultadoService;
    }

    @GetMapping
    public ResultadoResponse getResultado(@PathVariable Long apostaId){
        return resultadoService.getResultadoByAposta(apostaId);
    }
}
