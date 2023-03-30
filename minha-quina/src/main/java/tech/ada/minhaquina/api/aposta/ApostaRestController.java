package tech.ada.minhaquina.api.aposta;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public List<ApostaDTO> getAllApostas(@AuthenticationPrincipal UserDetails userDetails){
        return apostaService.getAllApostas(userDetails);
    }

    @GetMapping("/{apostaId}")
    public ApostaDTO getApostabyId(@PathVariable Long apostaId, @AuthenticationPrincipal UserDetails userDetails){
        return apostaService.getApostaById(userDetails, apostaId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ApostaDTO saveAposta(@Valid @RequestBody ApostaDTO apostaDTO, @AuthenticationPrincipal UserDetails userDetails) {
        return apostaService.saveAposta(userDetails, apostaDTO);
    }

    @PutMapping ("/{apostaId}")
    public ApostaDTO updateAposta(@PathVariable Long apostaId, @RequestBody ApostaDTO apostaDTO, @AuthenticationPrincipal UserDetails userDetails){
        return apostaService.updateAposta(userDetails, apostaId, apostaDTO);
    }

    @DeleteMapping("/{apostaId}")
    public void deleteAposta(@PathVariable Long apostaId, @AuthenticationPrincipal UserDetails userDetails){
            apostaService.deleteAposta(userDetails, apostaId);
    }

}
