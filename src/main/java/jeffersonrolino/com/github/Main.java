package jeffersonrolino.com.github;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jeffersonrolino.com.github.dao.ProdutoDAO;
import jeffersonrolino.com.github.entities.Produto;
import jeffersonrolino.com.github.util.JPAUtil;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Produto celular = new Produto();
        celular.setNome("Galaxy S6");
        celular.setDescricao("Celular moderno da Samsung");
        celular.setPreco(new BigDecimal("2000"));

        EntityManager entityManager = JPAUtil.getEntityManager();

        ProdutoDAO produtoDao = new ProdutoDAO(entityManager);

        entityManager.getTransaction().begin();

            produtoDao.cadastrar(celular);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}