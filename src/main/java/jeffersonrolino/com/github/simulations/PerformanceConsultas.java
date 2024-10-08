package jeffersonrolino.com.github.simulations;

import jakarta.persistence.EntityManager;
import jeffersonrolino.com.github.dao.CategoriaDAO;
import jeffersonrolino.com.github.dao.ClienteDAO;
import jeffersonrolino.com.github.dao.PedidoDAO;
import jeffersonrolino.com.github.dao.ProdutoDAO;
import jeffersonrolino.com.github.entities.*;
import jeffersonrolino.com.github.util.JPAUtil;
import jeffersonrolino.com.github.vo.RelatorioDeVendasVO;

import java.math.BigDecimal;
import java.util.List;

public class PerformanceConsultas {

    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        popularBancoDeDados();

        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);

        List<Produto> produtos = produtoDAO.buscarPorParametrosComCriteria("Macbook", BigDecimal.valueOf(9999.99), null);

        produtos.forEach(System.out::println);

        entityManager.close();
    }

    public static void popularBancoDeDados(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("IPhone 16 Pro", "Novo iphone da Apple", new BigDecimal(2800), celulares);
        Produto ps5 = new Produto("PS5", "Playstation 5", new BigDecimal(1800), videogames);
        Produto macbook = new Produto("Macbook", "MacBook Pro", new BigDecimal(12499.99), informatica);
        Produto macbook2 = new Produto("Macbook", "MacBook Air", new BigDecimal(9999.99), informatica);

        Cliente cliente = new Cliente("Fernando", "123456");

        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
        CategoriaDAO categoriaDAO = new CategoriaDAO(entityManager);
        ClienteDAO clienteDAO = new ClienteDAO(entityManager);
        PedidoDAO pedidoDAO = new PedidoDAO(entityManager);

        Pedido pedido1 = new Pedido(cliente);
        Pedido pedido2 = new Pedido(cliente);

        pedido1.adicionarItem(new ItemPedido(10, pedido1, celular));
        pedido1.adicionarItem(new ItemPedido(40, pedido1, ps5));

        pedido2.adicionarItem(new ItemPedido(50, pedido2, celular));
        pedido2.adicionarItem(new ItemPedido(10, pedido2, macbook));

        entityManager.getTransaction().begin();

            categoriaDAO.cadastrar(celulares);
            categoriaDAO.cadastrar(videogames);
            categoriaDAO.cadastrar(informatica);

            produtoDAO.cadastrar(celular);
            produtoDAO.cadastrar(ps5);
            produtoDAO.cadastrar(macbook);
            produtoDAO.cadastrar(macbook2);

            clienteDAO.cadastrar(cliente);

            pedidoDAO.cadastrar(pedido1);
            pedidoDAO.cadastrar(pedido2);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
