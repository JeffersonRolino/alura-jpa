package jeffersonrolino.com.github.simulations;

import jakarta.persistence.EntityManager;
import jeffersonrolino.com.github.dao.CategoriaDAO;
import jeffersonrolino.com.github.dao.ClienteDAO;
import jeffersonrolino.com.github.dao.PedidoDAO;
import jeffersonrolino.com.github.dao.ProdutoDAO;
import jeffersonrolino.com.github.entities.*;
import jeffersonrolino.com.github.util.JPAUtil;

import java.math.BigDecimal;

public class CadastroDePedido {

    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManager();

        popularBancoDeDados();

        entityManager.getTransaction().begin();

            ClienteDAO clienteDAO = new ClienteDAO(entityManager);
            ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);

            Produto produto = produtoDAO.buscarPorId(1L);
            Cliente cliente = clienteDAO.buscarPorId(1L);

            Pedido pedido = new Pedido(cliente);

            pedido.adicionarItem(new ItemPedido(10, pedido, produto));
            PedidoDAO pedidoDAO = new PedidoDAO(entityManager);

            pedidoDAO.cadastrar(pedido);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void popularBancoDeDados(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Categoria celulares = new Categoria("CELULARES");
        Produto produto = new Produto("IPhone 16 Pro", "Novo iphone da Apple", new BigDecimal(2800), celulares);
        Cliente cliente = new Cliente("Fernando", "123456");

        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
        CategoriaDAO categoriaDAO = new CategoriaDAO(entityManager);
        ClienteDAO clienteDAO = new ClienteDAO(entityManager);

        entityManager.getTransaction().begin();

            categoriaDAO.cadastrar(celulares);
            produtoDAO.cadastrar(produto);
            clienteDAO.cadastrar(cliente);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
