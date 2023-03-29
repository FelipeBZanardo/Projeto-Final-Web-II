package tech.ada.minhaquina.api.sorteio;

import org.springframework.web.bind.annotation.*;
import tech.ada.minhaquina.client.SorteioDTO;

@RestController
@RequestMapping("/minha-quina/api/v1/apostas/{apostaId}/sorteios")
public class SorteioRestController {

    private final SorteioService sorteioService;

    public SorteioRestController(SorteioService sorteioService) {
        this.sorteioService = sorteioService;
    }

    @GetMapping()
    public SorteioDTO getSorteioCadastradoByNumeroSorteio(@PathVariable Long apostaId){
        return sorteioService.getSorteioByAposta(apostaId);
    }


}
