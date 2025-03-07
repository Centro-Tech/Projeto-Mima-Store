package school.sptech.projetoMima.Repository;

import org.springframework.data.repository.CrudRepository;
import school.sptech.projetoMima.Model.Roupa;

import java.util.List;

public interface RoupaRepository extends CrudRepository<Roupa, Integer> {
   List<Roupa> findRoupaByNomeContainingIgnoreCase(String nome);
   Boolean existsByCodigoIdentificacao(String codigoIdentificacao);
   List<Roupa> findRoupaByCodigoIdentificacaoContainingIgnoreCase(String codigoIdentificacao);

}
