package jeffersonrolino.com.github.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CategoriaId implements Serializable {
    private String nome;
    private String tipo;

    public CategoriaId() {
    }

    public CategoriaId(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "CategoriaId{" +
                "nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
