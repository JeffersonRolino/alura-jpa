package jeffersonrolino.com.github.dao;

import jakarta.persistence.EntityManager;
import jeffersonrolino.com.github.entities.Produto;

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
}
