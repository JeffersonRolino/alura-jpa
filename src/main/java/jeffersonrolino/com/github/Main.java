package jeffersonrolino.com.github;

import jakarta.persistence.EntityManager;
import jeffersonrolino.com.github.dao.ProdutoDAO;
import jeffersonrolino.com.github.entities.Categoria;
import jeffersonrolino.com.github.entities.Produto;
import jeffersonrolino.com.github.util.JPAUtil;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Produto celular = new Produto(
        "Galaxy S6", "Celular moderno da Samsung", new BigDecimal("2000"), Categoria.CELULARES
        );

        EntityManager entityManager = JPAUtil.getEntityManager();

        ProdutoDAO produtoDao = new ProdutoDAO(entityManager);

        entityManager.getTransaction().begin();

            produtoDao.cadastrar(celular);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}