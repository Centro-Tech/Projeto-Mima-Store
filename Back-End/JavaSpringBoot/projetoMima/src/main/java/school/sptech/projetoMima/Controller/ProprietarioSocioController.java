package school.sptech.projetoMima.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.Model.Funcionario;
import school.sptech.projetoMima.Model.ProprietarioSocio;
import school.sptech.projetoMima.Repository.FuncionarioRepository;
import school.sptech.projetoMima.Repository.ProprietarioSocioRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/proprietaria")
public class ProprietarioSocioController {

    @Autowired
    private ProprietarioSocioRepository proprietarioSocioRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;


    @GetMapping("/{valor}")
    public ResponseEntity<List<ProprietarioSocio>> listarProprietarioSocio(@PathVariable String valor) {
        List <ProprietarioSocio> proprietarioSocios = new ArrayList<>();
        proprietarioSocios = proprietarioSocioRepository.findProprietarioSocioByNomeContainingIgnoreCase(valor);

        if(proprietarioSocios.isEmpty()){
            proprietarioSocios = proprietarioSocioRepository.findProprietarioSocioByPapelContainingIgnoreCase(valor);
        }
        if(proprietarioSocios.isEmpty()){
            proprietarioSocios = proprietarioSocioRepository.findProprietarioSocioByEmailContainingIgnoreCase(valor);
        }

        if(proprietarioSocios.isEmpty()){
          proprietarioSocios = proprietarioSocioRepository.findProprietarioSocioById(Integer.valueOf(valor));

        }

        if(proprietarioSocios.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(proprietarioSocios);
    }


    @GetMapping("/listar")
    public ResponseEntity<List<ProprietarioSocio>> listarProprietarios() {
        List<ProprietarioSocio> proprietarios = new ArrayList<>();

        for(ProprietarioSocio p : proprietarioSocioRepository.findAll()){
            proprietarios.add(p);
        }

        if(proprietarios.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(proprietarios);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ProprietarioSocio> atualizarDados(@RequestBody ProprietarioSocio proprietarioSocio, @PathVariable int id) {
        ProprietarioSocio pessoaExistente = proprietarioSocioRepository.findById(id).orElse(null);
        if(pessoaExistente == null){
            return ResponseEntity.status(404).build();
        }

        if(proprietarioSocio.getCpf() != null){
            pessoaExistente.setCpf(proprietarioSocio.getCpf());
        }
        if(proprietarioSocio.getNome() != null){
            pessoaExistente.setNome(proprietarioSocio.getNome());
        }
        if(proprietarioSocio.getPapel() != null){
            pessoaExistente.setPapel(proprietarioSocio.getPapel());
        }
        if(proprietarioSocio.getEmail() != null){
            pessoaExistente.setEmail(proprietarioSocio.getEmail());
        }
        if(proprietarioSocio.getTelefone() != null){
            pessoaExistente.setTelefone(proprietarioSocio.getTelefone());
        }else {
            return ResponseEntity.status(404).build();
        }

        proprietarioSocioRepository.save(pessoaExistente);
        return ResponseEntity.status(201).body(pessoaExistente);


    }


    @PostMapping
    public ResponseEntity<ProprietarioSocio> cadastrar(@RequestBody ProprietarioSocio proprietarioSocio) {
        if(proprietarioSocio == null){
            ResponseEntity.status(400).build();
        }

        proprietarioSocioRepository.save(proprietarioSocio);
        return ResponseEntity.status(201).body(proprietarioSocio);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ProprietarioSocio> excluir(@PathVariable int id) {
        if(proprietarioSocioRepository.existsById(id)){
            proprietarioSocioRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }



}
