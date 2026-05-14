package consulta_cep.controller;

import consulta_cep.dto.ViaCepRetornoDTO;
import consulta_cep.entity.ViaCep;
import consulta_cep.service.ViaCepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cep")
public class ViaCepController {

    private final ViaCepService viaCepService;

    public ViaCepController(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }
    @GetMapping("/{cep}")
    public ResponseEntity<ViaCepRetornoDTO> buscarCep(@PathVariable String cep) {
        ViaCepRetornoDTO endereco = viaCepService.buscarCep(cep);
        return ResponseEntity.ok(endereco);
    }
    @GetMapping("/historico")
    public ResponseEntity<List<ViaCepRetornoDTO>> listarHistorico() {
        List<ViaCepRetornoDTO> viaCepRetornoDTOS = viaCepService.listarHistorico();
        return ResponseEntity.ok(viaCepRetornoDTOS);
    }
    @DeleteMapping("/historico/{id}")
    public ResponseEntity<Void> deletarHistoricoPorId(@PathVariable Long id) {
        viaCepService.deletarHistoricoPorId(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/historico")
    public ResponseEntity<Void> limparHistorico() {
        viaCepService.limparHistorico();
        return ResponseEntity.noContent().build();
    }


}
