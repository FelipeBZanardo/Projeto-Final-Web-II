package tech.ada.minhaquina.api.resultado;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/minha-quina/api/v1/resultados")
public class ResultadoRestController {

    private final ResultadoService resultadoService;

    public ResultadoRestController(ResultadoService resultadoService) {
        this.resultadoService = resultadoService;
    }

    @GetMapping()
    public ResultadoResponse getUltimoResultado(){
        return resultadoService.getUltimoResultado();
    }

    @GetMapping("/{numero}")
    public ResultadoResponse getResultadoByNumeroSorteio(Integer numero){
        return resultadoService.getResultadoByNumeroSorteio(numero);
    }
}
