package jeffersonrolino.com.github.dao;

import jakarta.persistence.EntityManager;
import jeffersonrolino.com.github.entities.Categoria;
import jeffersonrolino.com.github.entities.Cliente;

public class ClienteDAO {
    private EntityManager entityManager;

    public ClienteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Cliente cliente){
        this.entityManager.persist(cliente);
    }

    public Cliente buscarPorId(Long id){
        return entityManager.find(Cliente.class, id);
    }
}
