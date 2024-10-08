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

public class CadastroDePedido {

    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManager();

        popularBancoDeDados();

        entityManager.getTransaction().begin();

            ClienteDAO clienteDAO = new ClienteDAO(entityManager);
            ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);

            Produto celular = produtoDAO.buscarPorId(1L);
            Produto ps5 = produtoDAO.buscarPorId(2L);

            Cliente cliente = clienteDAO.buscarPorId(1L);

            Pedido pedido1 = new Pedido(cliente);

            pedido1.adicionarItem(new ItemPedido(10, pedido1, celular));
            pedido1.adicionarItem(new ItemPedido(40, pedido1, ps5));

            Produto macbook = produtoDAO.buscarPorId(3L);

            Pedido pedido2 = new Pedido(cliente);

            pedido2.adicionarItem(new ItemPedido(50, pedido2, celular));
            pedido2.adicionarItem(new ItemPedido(10, pedido2, macbook));


            PedidoDAO pedidoDAO = new PedidoDAO(entityManager);

            pedidoDAO.cadastrar(pedido1);
            pedidoDAO.cadastrar(pedido2);

        entityManager.getTransaction().commit();


            BigDecimal totalVendido = pedidoDAO.valorTotalVendido();
            System.out.println("VALOR TOTAL: " + totalVendido);

            List<RelatorioDeVendasVO> relatorio = pedidoDAO.relatorioDeVendas();
            relatorio.forEach(System.out::println);

        entityManager.close();
    }

    public static void popularBancoDeDados(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Categoria celulares = new Categoria("CELULARES", "Eletronicos");
        Categoria videogames = new Categoria("VIDEOGAMES", "Eletronicos");
        Categoria informatica = new Categoria("INFORMATICA", "Eletronicos");

        Produto celular = new Produto("IPhone 16 Pro", "Novo iphone da Apple", new BigDecimal(2800), celulares);
        Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal(1800), videogames);
        Produto macbook = new Produto("Macbook", "MacBook Pro", new BigDecimal(10000), informatica);

        Cliente cliente = new Cliente("Fernando", "123456");

        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
        CategoriaDAO categoriaDAO = new CategoriaDAO(entityManager);
        ClienteDAO clienteDAO = new ClienteDAO(entityManager);

        entityManager.getTransaction().begin();

            categoriaDAO.cadastrar(celulares);
            categoriaDAO.cadastrar(videogames);
            categoriaDAO.cadastrar(informatica);

            produtoDAO.cadastrar(celular);
            produtoDAO.cadastrar(videogame);
            produtoDAO.cadastrar(macbook);

            clienteDAO.cadastrar(cliente);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
