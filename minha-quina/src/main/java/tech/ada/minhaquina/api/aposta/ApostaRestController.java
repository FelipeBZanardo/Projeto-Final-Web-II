package tech.ada.minhaquina.api.aposta;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/minha-quina/api/v1/usuarios/{userId}/apostas")
public class ApostaRestController {

    private final ApostaService apostaService;

    public ApostaRestController(ApostaService apostaService) {
        this.apostaService = apostaService;
    }

    @GetMapping
    public List<ApostaDTO> getAllApostas(@PathVariable Long userId){
        return apostaService.getAllApostas(userId);
    }

    @GetMapping("/{apostaId}")
    public ApostaDTO getApostabyId(@PathVariable Long userId, @PathVariable Long apostaId){
        return apostaService.getApostabyId(userId, apostaId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ApostaDTO saveAposta(@PathVariable Long userId, @Valid @RequestBody ApostaDTO apostaDTO) {
        return apostaService.saveAposta(userId, apostaDTO);
    }

    @PutMapping ("/{apostaId}")
    public ApostaDTO updateAposta(@PathVariable Long userId, @PathVariable Long apostaId, @RequestBody ApostaDTO apostaDTO){
        return apostaService.updateAposta(userId, apostaId, apostaDTO);
    }

    @DeleteMapping("/{apostaId}")
    public void deleteAposta(@PathVariable Long userId, @PathVariable Long apostaId){
            apostaService.deleteAposta(userId, apostaId);
    }

}
