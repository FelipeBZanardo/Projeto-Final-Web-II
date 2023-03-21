package tech.ada.apicaixa.controller;

import org.springframework.web.bind.annotation.*;
import tech.ada.apicaixa.model.dao.Aposta;
import tech.ada.apicaixa.model.dto.ApostaDTO;
import tech.ada.apicaixa.service.ApostaService;

import java.util.List;

@RestController
@RequestMapping("/minha-quina/api/v1/apostas")
public class ApostaRestController {

    private final ApostaService apostaService;

    public ApostaRestController(ApostaService apostaService) {
        this.apostaService = apostaService;
    }

    @GetMapping
    public List<Aposta> getAllApostas(){
        return apostaService.getAllApostas();
    }

    @GetMapping("/{id}")
    public Aposta getApostabyId(@PathVariable Long id){
        return apostaService.getApostabyId(id);
    }

    @PostMapping()
    public Aposta saveAposta(@RequestBody ApostaDTO apostaDTO){
        return apostaService.saveAposta(apostaDTO);
    }

    @PutMapping ("/{id}")
    public Aposta updateAposta(@PathVariable Long id, @RequestBody ApostaDTO apostaDTO){
        return apostaService.updateAposta(id, apostaDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAposta(@PathVariable Long id){
        apostaService.deleteAposta(id);
    }

}
