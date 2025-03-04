package school.sptech.projetoMima.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Roupa> roupasEmEstoque;

    public Estoque() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Roupa> getRoupasEmEstoque() {
        return roupasEmEstoque;
    }

    public void setRoupasEmEstoque(List<Roupa> roupasEmEstoque) {
        this.roupasEmEstoque = roupasEmEstoque;
    }
}
