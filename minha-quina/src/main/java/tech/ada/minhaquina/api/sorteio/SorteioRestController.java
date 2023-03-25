package tech.ada.minhaquina.api.sorteio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.minhaquina.client.SorteioDTO;

@RestController
@RequestMapping("/minha-quina/api/v1/sorteios")
public class SorteioRestController {

    private final SorteioService sorteioService;

    public SorteioRestController(SorteioService sorteioService) {
        this.sorteioService = sorteioService;
    }

    @GetMapping()
    public SorteioDTO getUltimoResultado(){
        return sorteioService.getUltimoResultado();
    }

    @GetMapping("/{numero}")
    public SorteioDTO getResultadoByNumeroSorteio(@PathVariable Integer numero){
        return sorteioService.getResultadoByNumeroSorteio(numero);
    }
}
