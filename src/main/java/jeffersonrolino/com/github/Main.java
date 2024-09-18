package jeffersonrolino.com.github;

import jakarta.persistence.EntityManager;
import jeffersonrolino.com.github.entities.Categoria;
import jeffersonrolino.com.github.util.JPAUtil;

public class Main {
    public static void main(String[] args) {
        Categoria celulares = new Categoria("CELULARES");
        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(celulares);
        celulares.setNome("XPTO");

        entityManager.flush();
        entityManager.clear();

        celulares = entityManager.merge(celulares);
        celulares.setNome("1234");

        entityManager.flush();
        entityManager.remove(celulares);
        entityManager.flush();

        entityManager.getTransaction().commit();
    }
}