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

    @PutMapping("/{id}/{nome}")
    public ResponseEntity<Funcionario> atualizarNomeFuncionario(@PathVariable Integer id,@PathVariable String nome, @RequestParam Funcionario funcionario) {
        Integer funcionarioExistente = funcionarioRepository.findFuncionarioById(id);
        if (funcionarioExistente == null) {
            return ResponseEntity.notFound().build();
        }
        funcionario.setNome(nome);

        return ResponseEntity.ok(funcionarioRepository.save(funcionario));
    }

    @PutMapping("/{id}/{cargo}")
    public ResponseEntity<String> atualizarCargoFuncionario(@PathVariable Integer id, @PathVariable String cargo) {
        Funcionario funcionarioExistente = funcionarioRepository.findById(id).orElse(null);

        if (funcionarioExistente == null) {
            return ResponseEntity.status(404).body("Funcionário não encontrado");
        }

        funcionarioExistente.setCargo(cargo);
        funcionarioRepository.save(funcionarioExistente);

        return ResponseEntity.ok("Cargo atualizado com sucesso");
    }


    @PutMapping("{id}/{email}")
    public ResponseEntity<String> atualizarEmailFuncionario(@PathVariable Integer id, @PathVariable String email) {
        Funcionario funcionarioExistente = funcionarioRepository.findById(id).orElse(null);
        if (funcionarioExistente == null) {
            return ResponseEntity.notFound().build();
        }
        funcionarioExistente.setEmail(email);
        funcionarioRepository.save(funcionarioExistente);
        return ResponseEntity.ok("Email atualizado com sucesso");
    }

    @PutMapping("{id}/{telefone}")
    public ResponseEntity<String> atualizarTelefone(@PathVariable Integer id, @PathVariable String telefone) {
        Funcionario funcionarioExistente = funcionarioRepository.findById(id).orElse(null);
        if (funcionarioExistente == null) {
            return ResponseEntity.notFound().build();
        }
        funcionarioExistente.setTelefone(telefone);
        funcionarioRepository.save(funcionarioExistente);
        return ResponseEntity.ok("Telefone atualizado com sucesso");
    }

    



}
