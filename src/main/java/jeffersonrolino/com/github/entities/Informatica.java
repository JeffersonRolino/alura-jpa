package jeffersonrolino.com.github.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "informatica")
public class Informatica extends Produto{
    private String marca;
    private Integer modelo;

    public Informatica() {
    }

    public Informatica(String marca, Integer modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Informatica{" +
                "marca='" + marca + '\'' +
                ", modelo=" + modelo +
                '}';
    }
}
