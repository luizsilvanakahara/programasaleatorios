package dao;

import model.Produto;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDAO {

    public void salvar(Produto produto) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
        em.close();
    }

    public List<Produto> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Produto> lista =
            em.createQuery("from Produto", Produto.class).getResultList();
        em.close();
        return lista;
    }
}
