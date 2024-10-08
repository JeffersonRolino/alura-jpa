package jeffersonrolino.com.github.dao;

import jakarta.persistence.EntityManager;
import jeffersonrolino.com.github.entities.Pedido;
import jeffersonrolino.com.github.vo.RelatorioDeVendasVO;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDAO {
    private EntityManager entityManager;

    public PedidoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Pedido pedido){
        this.entityManager.persist(pedido);
    }

    public BigDecimal valorTotalVendido(){
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return entityManager.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
    }

    public List<RelatorioDeVendasVO> relatorioDeVendas(){
        String jpql = "SELECT new RelatorioDeVendasVO(" +
                "produto.nome, " +
                "SUM(item.quantidade), " +
                "MAX(pedido.data)) " +
                "FROM Pedido pedido " +
                "JOIN pedido.itens item " +
                "JOIN item.produto produto " +
                "GROUP BY produto.nome, item.quantidade " +
                "ORDER BY item.quantidade DESC";
        return entityManager.createQuery(jpql, RelatorioDeVendasVO.class)
            .getResultList();
    }

    public Pedido buscarPedidoComCliente(Long id){
        String query = "SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id";
        return entityManager.createQuery(query, Pedido.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
