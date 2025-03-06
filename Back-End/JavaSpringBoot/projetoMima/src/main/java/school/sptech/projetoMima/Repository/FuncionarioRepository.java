package school.sptech.projetoMima.Repository;

import org.springframework.data.repository.CrudRepository;
import school.sptech.projetoMima.Model.Funcionario;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
}
