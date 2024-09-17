package jeffersonrolino.com.github;

import jakarta.persistence.EntityManager;
import jeffersonrolino.com.github.dao.CategoriaDAO;
import jeffersonrolino.com.github.dao.ProdutoDAO;
import jeffersonrolino.com.github.entities.Categoria;
import jeffersonrolino.com.github.entities.Produto;
import jeffersonrolino.com.github.util.JPAUtil;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Categoria celulares = new Categoria("CELULARES");

        Produto celular = new Produto(
        "Galaxy S6", "Celular moderno da Samsung", new BigDecimal("2000"), celulares
        );

        EntityManager entityManager = JPAUtil.getEntityManager();

        CategoriaDAO categoriaDAO = new CategoriaDAO(entityManager);
        ProdutoDAO produtoDao = new ProdutoDAO(entityManager);


        entityManager.getTransaction().begin();

            categoriaDAO.cadastrar(celulares);
            produtoDao.cadastrar(celular);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}