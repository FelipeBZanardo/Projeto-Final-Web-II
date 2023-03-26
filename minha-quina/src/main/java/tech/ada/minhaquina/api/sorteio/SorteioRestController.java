package tech.ada.minhaquina.api.sorteio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.minhaquina.client.SorteioDTO;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/minha-quina/api/v1/")
public class SorteioRestController {

    private final SorteioService sorteioService;

    public SorteioRestController(SorteioService sorteioService) {
        this.sorteioService = sorteioService;
    }

    @GetMapping("sorteios/external-search")
    public SorteioDTO getUltimoResultado(){
        return sorteioService.getUltimoSorteio();
    }

    @GetMapping("sorteios/external-search/{numero}")
    public SorteioDTO getResultadoByNumeroSorteio(@PathVariable Integer numero){
        return sorteioService.getSorteioByNumeroSorteio(numero);
    }

    @GetMapping("usuarios/{userId}/apostas/{apostaId}/sorteios")
    public SorteioDTO getSorteioCadastradoByNumeroSorteio(@PathVariable Long userId, @PathVariable Long apostaId){
        return sorteioService.getSorteioByAposta(userId, apostaId);
    }


}
