package br.com.my.loja.teste;

import br.com.my.loja.dao.CategoriaDao;
import br.com.my.loja.dao.ProdutoDao;
import br.com.my.loja.modelo.Categoria;
import br.com.my.loja.modelo.Produto;
import br.com.my.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
public class CadastroDeProduto {
    public static void main(String[] args) {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi","Bom celular",new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        
        em.getTransaction().commit();
        em.close();
    }
}
