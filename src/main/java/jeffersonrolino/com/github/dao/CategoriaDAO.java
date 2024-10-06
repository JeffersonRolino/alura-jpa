package jeffersonrolino.com.github.dao;

import jakarta.persistence.EntityManager;
import jeffersonrolino.com.github.entities.Categoria;
import jeffersonrolino.com.github.entities.Produto;

public class CategoriaDAO {
    private EntityManager entityManager;

    public CategoriaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Categoria categoria){
        this.entityManager.persist(categoria);
    }

    public Categoria buscarPorId(Long id){
        return entityManager.find(Categoria.class, id);
    }

    public void atualizar(Categoria categoria){
        this.entityManager.merge(categoria);
    }

    public void remover(Categoria categoria){
        categoria = entityManager.merge(categoria);
        this.entityManager.remove(categoria);
    }
}
