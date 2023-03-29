package tech.ada.minhaquina.api.admin.sorteio;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.minhaquina.api.sorteio.SorteioService;
import tech.ada.minhaquina.client.SorteioDTO;

@PreAuthorize("hasRole('ADMIN')")
@Log4j2
@RestController
@RequestMapping("/minha-quina/api/v1/sorteios/external-search")
public class SorteioManagerRestController {

    private final SorteioService sorteioService;

    public SorteioManagerRestController(SorteioService sorteioService) {
        this.sorteioService = sorteioService;
    }

    @GetMapping
    public SorteioDTO getUltimoResultado(){
        return sorteioService.getUltimoSorteio();
    }

    @GetMapping("/{numero}")
    public SorteioDTO getResultadoByNumeroSorteio(@PathVariable Integer numero){
        return sorteioService.getSorteioByNumeroSorteio(numero);
    }


}
