package jeffersonrolino.com.github.dao;

import jakarta.persistence.EntityManager;
import jeffersonrolino.com.github.entities.Produto;

import java.util.List;

public class ProdutoDAO {
    private EntityManager entityManager;

    public ProdutoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Produto produto){
        this.entityManager.persist(produto);
    }

    public void atualizar(Produto produto){
        this.entityManager.merge(produto);
    }

    public void remover(Produto produto){
        produto = entityManager.merge(produto);
        this.entityManager.remove(produto);
    }

    public Produto buscarPorId(Long id){
        return entityManager.find(Produto.class, id);
    }

    public List<Produto> buscarTodos(){
        String query = "SELECT p FROM Produto p";
        return entityManager.createQuery(query, Produto.class).getResultList();
    }
}
