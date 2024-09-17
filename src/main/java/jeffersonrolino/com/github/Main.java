package jeffersonrolino.com.github;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jeffersonrolino.com.github.entities.Produto;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Produto celular = new Produto();
        celular.setNome("Galaxy S6");
        celular.setDescricao("Celular moderno da Samsung");
        celular.setPreco(new BigDecimal("2000"));

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("loja");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

            entityManager.persist(celular);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}