package jeffersonrolino.com.github;

import jakarta.persistence.EntityManager;
import jeffersonrolino.com.github.dao.ProdutoDAO;
import jeffersonrolino.com.github.entities.Categoria;
import jeffersonrolino.com.github.entities.Produto;
import jeffersonrolino.com.github.util.JPAUtil;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManager();

        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);

        Produto produto = produtoDAO.buscarPorId(1L);
        System.out.println(produto);

        List<Produto> produtos = produtoDAO.buscarTodos();

        System.out.println("\nPRODUTOS");
        produtos.forEach(System.out::println);
    }

    public static void cadastrarProduto(Categoria categoria, String nome, String descricacao, BigDecimal preco){
        EntityManager entityManager = JPAUtil.getEntityManager();

        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
        Produto produto = new Produto(nome, descricacao, preco, categoria);

        entityManager.getTransaction().begin();

            produtoDAO.cadastrar(produto);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}