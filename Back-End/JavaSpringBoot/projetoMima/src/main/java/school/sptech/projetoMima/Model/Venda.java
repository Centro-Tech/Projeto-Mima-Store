package school.sptech.projetoMima.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCliente;
    private String emailCliente;
    private String telefoneCliente;

    @ManyToMany
    private List<Roupa> roupasVendidas;

    public Venda() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public List<Roupa> getRoupasVendidas() {
        return roupasVendidas;
    }

    public void setRoupasVendidas(List<Roupa> roupasVendidas) {
        this.roupasVendidas = roupasVendidas;
    }
}
