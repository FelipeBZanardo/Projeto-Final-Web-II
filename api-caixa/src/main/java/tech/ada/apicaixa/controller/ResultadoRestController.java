package tech.ada.apicaixa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.apicaixa.model.dto.Resultado;
import tech.ada.apicaixa.service.ResultadoService;

@RestController
@RequestMapping("/minha-quina/api/v1/resultados")
public class ResultadoRestController {

    private final ResultadoService resultadoService;

    public ResultadoRestController(ResultadoService resultadoService) {
        this.resultadoService = resultadoService;
    }

    @GetMapping()
    public Resultado getUltimoResultado(){
        return resultadoService.getUltimoResultado();
    }

    @GetMapping("/{numero}")
    public Resultado getResultadoByNumeroSorteio(Integer numero){
        return resultadoService.getResultadoByNumeroSorteio(numero);
    }
}
