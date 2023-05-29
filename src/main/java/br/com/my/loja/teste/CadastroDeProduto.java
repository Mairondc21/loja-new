package br.com.my.loja.teste;

import br.com.my.loja.dao.CategoriaDao;
import br.com.my.loja.dao.ProdutoDao;
import br.com.my.loja.modelo.Categoria;
import br.com.my.loja.modelo.Produto;
import br.com.my.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {
    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto p = produtoDao.buscarId(1l);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDao.buscarNomeCategoria("CELULARES");

        todos.forEach(p2 -> System.out.println(p2.getNome()));

        BigDecimal precoDoProduto = produtoDao.buscarPrecoProdutoComNome("Xiaomi");
        System.out.println(precoDoProduto);

    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto
                ("Xiaomi","Bom celular",new BigDecimal("800"), celulares);

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

