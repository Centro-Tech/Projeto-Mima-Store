package school.sptech.projetoMima.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.projetoMima.Repository.FornecedorRepository;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {


    @Autowired
    private FornecedorRepository fornecedorRepository;
}
