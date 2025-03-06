package school.sptech.projetoMima.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.Model.Roupa;
import school.sptech.projetoMima.Repository.RoupaRepository;

import java.util.List;

@RestController
@RequestMapping("/roupas")
public class RoupaController {

    @Autowired
    private RoupaRepository roupaRepository;

    @GetMapping("/{nome}")
    public ResponseEntity<List<Roupa>> buscarPorNome(@RequestParam String nome) {
        List<Roupa> roupas = roupaRepository.findByNomeContainingIgnoreCase(nome);

        if (roupas.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok(roupas);
    }

    @PostMapping
    public ResponseEntity<Roupa> cadastrarRoupa(@RequestBody Roupa roupa) {
        if (roupaRepository.existsByCodigoIdentificacao(roupa.getCodigoIdentificacao())) {
            return ResponseEntity.status(400).body(null);
        }

        Roupa novaRoupa = roupaRepository.save(roupa);
        return ResponseEntity.status(201).body(novaRoupa);
    }
}
