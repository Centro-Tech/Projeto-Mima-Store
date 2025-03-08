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
        List<Funcionario> funcionarios = funcionarioRepository.findFuncionarioByNomeContainingIgnoreCase(valor);

        if (funcionarios.isEmpty()) {
            funcionarios = funcionarioRepository.findFuncionarioByCargoContainingIgnoreCase(valor);
        }

        if(funcionarios.isEmpty()){
            funcionarios = funcionarioRepository.findFuncionarioById(Integer.valueOf(valor));
        }

        if (funcionarios.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(funcionarios);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Funcionario>> listarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
       for(Funcionario funcionario : funcionarioRepository.findAll()){
           funcionarios.add(funcionario);
       }

       if(funcionarios.isEmpty()){
           return ResponseEntity.status(404).build();
       }else {
           return ResponseEntity.status(200).body(funcionarios);
       }
    }



    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizarFuncionario(@RequestBody Funcionario funcionario, @PathVariable int id) {
        Funcionario funcionarioExistente = funcionarioRepository.findById(id).orElse(null);
        if (funcionarioExistente == null) {
            return ResponseEntity.status(404).build();
        }

        if (funcionario.getNome() != null) {
            funcionarioExistente.setNome(funcionario.getNome());
        }
        if (funcionario.getCargo() != null) {
            funcionarioExistente.setCargo(funcionario.getCargo());
        }
        if (funcionario.getEmail() != null) {
            funcionarioExistente.setEmail(funcionario.getEmail());
        }
        if (funcionario.getTelefone() != null) {
            funcionarioExistente.setTelefone(funcionario.getTelefone());
        }

        Funcionario funcionarioNovo = funcionarioRepository.save(funcionarioExistente);

        return ResponseEntity.status(201).body(funcionarioNovo);
    }


    @PostMapping
    public ResponseEntity<Funcionario> inserirFuncionario(@RequestBody Funcionario funcionario) {

        if (funcionario.getEmail() == null || funcionario.getTelefone() == null) {
            return ResponseEntity.status(404).build();
        }

        if (funcionarioRepository.existsByEmail(funcionario.getEmail())) {
            return ResponseEntity.status(400).build();
        }

        if (funcionarioRepository.existsByTelefone(funcionario.getTelefone())) {
            return ResponseEntity.status(400).build();
        }

        Funcionario novoFuncionario = funcionarioRepository.save(funcionario);
        return ResponseEntity.status(201).body(novoFuncionario);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable int id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }







}
