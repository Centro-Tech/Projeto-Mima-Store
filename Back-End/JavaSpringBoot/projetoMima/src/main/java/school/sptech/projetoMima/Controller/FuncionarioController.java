package school.sptech.projetoMima.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.Model.Roupa;
import school.sptech.projetoMima.Repository.FuncionarioRepository;
import school.sptech.projetoMima.Model.Funcionario;
import school.sptech.projetoMima.Repository.RoupaRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    @GetMapping("/{valor}")
    public ResponseEntity<List<Funcionario>> listarFuncionarios(@PathVariable String valor) {
        List<Funcionario> funcionarios = funcionarioRepository.findFuncionarioByEmailContainingIgnoreCase(valor);
        if (funcionarios.isEmpty()) {
            funcionarios = funcionarioRepository.findFuncionarioByNomeContainingIgnoreCase(valor);
        }

        if (funcionarios.isEmpty()) {
            funcionarios = funcionarioRepository.findFuncionarioByCargoContainingIgnoreCase(valor);
        }

        if (funcionarios.isEmpty()) {
            funcionarios = funcionarioRepository.findFuncionarioByTelefoneContainingIgnoreCase(valor);
        }

        if (funcionarios.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(funcionarios);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarFuncionario(@RequestBody Funcionario funcionario, @PathVariable int id) {
        Funcionario funcionarioExistente = funcionarioRepository.findById(id).orElse(null);
        if (funcionarioExistente == null) {
            return ResponseEntity.status(404).body("Funcionário não encontrado");
        }

        if(funcionario.getNome() != null) {
            funcionarioExistente.setNome(funcionario.getNome());
        }

        if(funcionario.getCargo() != null) {
            funcionarioExistente.setCargo(funcionario.getCargo());
        }

       if(funcionario.getEmail() != null) {
           funcionarioExistente.setEmail(funcionario.getEmail());
       }

       if(funcionario.getTelefone() != null) {
           funcionarioExistente.setTelefone(funcionario.getTelefone());
       }

       funcionarioRepository.save(funcionarioExistente);
       return ResponseEntity.status(200).build();
    }

    @PostMapping
    public ResponseEntity<String> inserirFuncionario(@RequestBody Funcionario funcionario) {
        if(funcionarioRepository.findFuncionarioByEmail(funcionario.getEmail()) != null) {
            return ResponseEntity.status(404).build();
        }

        funcionarioRepository.save(funcionario);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable int id) {
        if(funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }else {
            return ResponseEntity.status(404).build();
        }
    }






}
