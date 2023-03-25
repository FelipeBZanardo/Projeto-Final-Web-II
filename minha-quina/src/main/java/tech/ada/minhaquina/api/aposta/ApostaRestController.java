package tech.ada.minhaquina.api.aposta;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/minha-quina/api/v1/apostas")
public class ApostaRestController {

    private final ApostaService apostaService;

    public ApostaRestController(ApostaService apostaService) {
        this.apostaService = apostaService;
    }

    @GetMapping
    public List<ApostaModel> getAllApostas(){
        return apostaService.getAllApostas();
    }

    @GetMapping("/{id}")
    public ApostaModel getApostabyId(@PathVariable Long id){
        return apostaService.getApostabyId(id);
    }

    @PostMapping()
    public ApostaModel saveAposta(@RequestBody ApostaDTO apostaDTO){
        return apostaService.saveAposta(apostaDTO);
    }

    @PutMapping ("/{id}")
    public ApostaModel updateAposta(@PathVariable Long id, @RequestBody ApostaDTO apostaDTO){
        return apostaService.updateAposta(id, apostaDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAposta(@PathVariable Long id){
        apostaService.deleteAposta(id);
    }

}
