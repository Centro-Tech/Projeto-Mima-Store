package school.sptech.projetoMima.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.Model.Roupa;
import school.sptech.projetoMima.Repository.FuncionarioRepository;
import school.sptech.projetoMima.Model.Funcionario; // Supondo que você tenha essa classe no pacote Model
import school.sptech.projetoMima.Repository.RoupaRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    @GetMapping("/{nome}")
    public ResponseEntity<List<Funcionario>> listarFuncionarios(@RequestParam String nome) {
        List<Funcionario> funcionarios = funcionarioRepository.findFuncionarioByNomeContainingIgnoreCase(nome);
        if (funcionarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(funcionarios);

    }

    @GetMapping("/{cargo}")
    public ResponseEntity<List<Funcionario>> listarFuncionariosCargo(@RequestParam String cargo) {
        List<Funcionario> funcionarios = funcionarioRepository.findFuncionarioByCargoContainingIgnoreCase(cargo);
        if (funcionarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Funcionario>> listarFuncionariosEmail(@RequestParam String email) {
        List<Funcionario> funcionarios = funcionarioRepository.findFuncionarioByEmailContainingIgnoreCase(email);
        if (funcionarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{telefone}")
    public ResponseEntity<List<Funcionario>> listarFuncionariosTelefone(@RequestParam String telefone) {
        List<Funcionario> funcionarios = funcionarioRepository.findFuncionarioByTelefoneContainingIgnoreCase(telefone);
        if(funcionarios.isEmpty()) {
            return ResponseEntity.notFound().build();
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
       return ResponseEntity.status(200).body("Funcionário atualizado com sucesso");
    }






}
