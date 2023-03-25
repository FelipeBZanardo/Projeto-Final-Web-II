package tech.ada.minhaquina.api.aposta;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.minhaquina.api.exception.DataJogoException;
import tech.ada.minhaquina.api.exception.NumeroSorteioException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/minha-quina/api/v1/apostas")
public class ApostaRestController {

    private final ApostaService apostaService;

    public ApostaRestController(ApostaService apostaService) {
        this.apostaService = apostaService;
    }

    @GetMapping
    public List<ApostaDTO> getAllApostas(){
        return apostaService.getAllApostas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getApostabyId(@PathVariable Long id){
        try{
            return ResponseEntity.ok(apostaService.getApostabyId(id));
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().body("Id de aposta não existe");
        }
    }

    @PostMapping()
    public ResponseEntity<Object> saveAposta(@Valid @RequestBody ApostaDTO apostaDTO) {
        return new ResponseEntity<>(apostaService.saveAposta(apostaDTO), HttpStatus.CREATED);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Object> updateAposta(@PathVariable Long id, @RequestBody ApostaDTO apostaDTO){
        try{
            return ResponseEntity.ok(apostaService.updateAposta(id, apostaDTO));
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().body("Id de aposta não existe");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAposta(@PathVariable Long id){
        try{
            apostaService.deleteAposta(id);
            return ResponseEntity.ok("Aposta deletada com sucesso");
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().body("Id de aposta não existe");
        }

    }

}
