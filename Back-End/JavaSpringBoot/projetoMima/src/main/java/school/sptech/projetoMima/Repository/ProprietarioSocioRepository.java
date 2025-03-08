package school.sptech.projetoMima.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.projetoMima.Model.ProprietarioSocio;

import java.util.List;

@Repository
public interface ProprietarioSocioRepository extends JpaRepository<ProprietarioSocio, Integer> {
    List<ProprietarioSocio> findProprietarioSocioByNomeContainingIgnoreCase(String nome);

    List<ProprietarioSocio> findProprietarioSocioByPapelContainingIgnoreCase(String papel);
    List<ProprietarioSocio> findProprietarioSocioByEmailContainingIgnoreCase(String email);
    List<ProprietarioSocio> findProprietarioSocioById(Integer id);
}
