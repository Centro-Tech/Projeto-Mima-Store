package school.sptech.projetoMima.Repository;

import org.springframework.data.repository.CrudRepository;
import school.sptech.projetoMima.Model.Funcionario;

import java.util.List;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
    List<Funcionario> findFuncionarioByNomeContainingIgnoreCase(String nome);
    List<Funcionario> findFuncionarioByCargoContainingIgnoreCase(String cargo);
    List<Funcionario> findFuncionarioByEmailContainingIgnoreCase(String email);
    List<Funcionario> findFuncionarioByTelefoneContainingIgnoreCase(String telefone);
    Boolean findFuncionarioByEmail(String email);

    List<Funcionario> findFuncionarioById(Integer id);

    boolean existsByEmail(String email);

    boolean existsByTelefone(String telefone);
}
