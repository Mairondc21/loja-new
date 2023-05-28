package br.com.my.loja.dao;

import br.com.my.loja.modelo.Categoria;
import br.com.my.loja.modelo.Produto;

import javax.persistence.EntityManager;

public class CategoriaDao {
    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }
    public void cadastrar (Categoria categoria){
        this.em.persist(categoria);
    }
}
