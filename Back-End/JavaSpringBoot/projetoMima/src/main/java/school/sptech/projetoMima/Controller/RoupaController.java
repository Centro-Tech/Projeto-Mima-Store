    package school.sptech.projetoMima.Controller;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import school.sptech.projetoMima.Model.Roupa;
    import school.sptech.projetoMima.Repository.RoupaRepository;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Random;

    @RestController
    @RequestMapping("/roupas")
    public class RoupaController {

        @Autowired
        private RoupaRepository roupaRepository;

        @GetMapping("/{nomeOuCodigo}")
        public ResponseEntity<List<Roupa>> buscarPorNomeOuCodigo(@PathVariable String nomeOuCodigo){
            List<Roupa> roupa = new ArrayList<>();
            roupa = roupaRepository.findByNomeContainingIgnoreCase(nomeOuCodigo);

            if(roupa.isEmpty()){
                roupa = roupaRepository.findCodigoIdentificacaoContainingIgnoreCase(nomeOuCodigo);
            }

            if(roupa.isEmpty()){
                return ResponseEntity.status(404).body(null);
            }else {
                return ResponseEntity.ok(roupa);
            }
        }



        @GetMapping("/estoque")
        public ResponseEntity<List<Roupa>> buscarEstoque() {
            List<Roupa> estoque = new ArrayList<>();

            for (Roupa r : roupaRepository.findAll()) {
                if (r != null && r.getQuantidade() > 0 && !r.getVendido()) {
                    estoque.add(r);
                }
            }

            if (!estoque.isEmpty()) {
                return ResponseEntity.ok(estoque);
            }

            return ResponseEntity.status(404).body(null);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Roupa> venderRoupa(@PathVariable int id, @RequestBody Roupa roupa) {
            Roupa roupaExistente = roupaRepository.findById(id).orElse(null);
            if (roupaExistente == null) {
                return ResponseEntity.status(404).body(null);
            }
            if (roupa.getQuantidade() != null && roupa.getQuantidade() > 0 && roupaExistente.getQuantidade() >= roupa.getQuantidade()) {
                roupaExistente.setQuantidade(roupaExistente.getQuantidade() - roupa.getQuantidade());
            }else {
                return ResponseEntity.status(404).body(null);
            }

            roupaExistente.setVendido(true);

            roupaRepository.save(roupaExistente);
            return ResponseEntity.ok(roupaExistente);
        }




        @PostMapping
        public ResponseEntity<Roupa> cadastrarRoupa(@RequestBody Roupa roupa) {
            if (roupaRepository.existsByCodigoIdentificacao(roupa.getCodigoIdentificacao())) {
                return ResponseEntity.status(400).body(null);
            }

            String nomeRoupa = roupa.getNome().toUpperCase();
            String tamanhoRoupa = roupa.getTamanho().toUpperCase();

            String codigoIdentificacao = null;

            if ("BERMUDA".equals(nomeRoupa)) {
                codigoIdentificacao = "BZ";
            } else if ("BLAZER".equals(nomeRoupa)) {
                codigoIdentificacao = "BL";
            } else if ("BLUSA".equals(nomeRoupa)) {
                codigoIdentificacao = "BL";
            } else if ("BRACELETE".equals(nomeRoupa)) {
                codigoIdentificacao = "BR";
            } else if ("BRINCO".equals(nomeRoupa)) {
                codigoIdentificacao = "BC";
            } else if ("CALÇA".equals(nomeRoupa)) {
                codigoIdentificacao = "CL";
            } else if ("CAMISA".equals(nomeRoupa)) {
                codigoIdentificacao = "CA";
            } else if ("CAMISETA".equals(nomeRoupa)) {
                codigoIdentificacao = "BL";
            } else if ("CARDIGAN".equals(nomeRoupa)) {
                codigoIdentificacao = "TR";
            } else if ("CHEMISE".equals(nomeRoupa)) {
                codigoIdentificacao = "CH";
            } else if ("COLAR".equals(nomeRoupa)) {
                codigoIdentificacao = "CR";
            } else if ("CONJUNTO".equals(nomeRoupa)) {
                codigoIdentificacao = "CO";
            } else if ("CROPPED".equals(nomeRoupa)) {
                codigoIdentificacao = "BL";
            } else if ("ELASTICO".equals(nomeRoupa)) {
                codigoIdentificacao = "EL";
            } else if ("JAQUETA".equals(nomeRoupa)) {
                codigoIdentificacao = "JA";
            } else if ("LENÇO".equals(nomeRoupa)) {
                codigoIdentificacao = "LE";
            } else if ("MACACÃO".equals(nomeRoupa)) {
                codigoIdentificacao = "MA";
            } else if ("MACAQUINHO".equals(nomeRoupa)) {
                codigoIdentificacao = "MA";
            } else if ("PARKA".equals(nomeRoupa)) {
                codigoIdentificacao = "PK";
            } else if ("PONCHO".equals(nomeRoupa)) {
                codigoIdentificacao = "TR";
            } else if ("PULSEIRA".equals(nomeRoupa)) {
                codigoIdentificacao = "PU";
            } else if ("REGATA".equals(nomeRoupa)) {
                codigoIdentificacao = "BL";
            } else if ("SAIA".equals(nomeRoupa)) {
                codigoIdentificacao = "SA";
            } else if ("SHORT".equals(nomeRoupa)) {
                codigoIdentificacao = "SH";
            } else if ("TOMARA QUE CAIA".equals(nomeRoupa)) {
                codigoIdentificacao = "BL";
            } else if ("TRICOT".equals(nomeRoupa)) {
                codigoIdentificacao = "TR";
            } else if ("T-SHIRT".equals(nomeRoupa)) {
                codigoIdentificacao = "BL";
            } else if ("VESTIDO".equals(nomeRoupa)) {
                codigoIdentificacao = "VE";
            } else {
                return ResponseEntity.status(400).body(null);
            }

            Random random = new Random();
            int numeroAleatorio = 1000000 + random.nextInt(9000000);


            String codigoFinal = codigoIdentificacao + numeroAleatorio + tamanhoRoupa;

            roupa.setCodigoIdentificacao(codigoFinal);

            Roupa novaRoupa = roupaRepository.save(roupa);

            return ResponseEntity.status(201).body(novaRoupa);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletarRoupa(@PathVariable int id){
            if(roupaRepository.existsById(id)){
                roupaRepository.deleteById(id);
                return ResponseEntity.status(204).build();
            };
            return ResponseEntity.status(404).build();


        }




    }
