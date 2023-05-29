package br.com.my.loja.dao;

import br.com.my.loja.modelo.Categoria;
import br.com.my.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDao {
    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }
    public void cadastrar (Produto produto){
        this.em.persist(produto);
    }
    public void atualizar(Produto produto) {
        this.em.merge(produto);
    }

    public void remover(Produto produto) {
        produto = em.merge(produto);
        this.em.remove(produto);
    }
    public Produto buscarId(Long id){
       return em.find(Produto.class,id);
    }
    public List<Produto> buscarTodos(){
        String jpql = "SELECT p FROM Produto p ";
        return em.createQuery(jpql,Produto.class).getResultList();
    }
    public List<Produto> buscarNome(String nome){
        String jpql = "SELECT p FROM Produto p  WHERE p.nome = :nome";
        return em.createQuery(jpql,Produto.class)
                .setParameter("nome",nome)
                .getResultList();
    }
    public List<Produto> buscarNomeCategoria(String nome){ //INNER JOIN no JPA relacionando categoria com produto filtrando pelo nome
        String jpql = "SELECT p FROM Produto p  WHERE p.categoria.nome = :nome";//"SELECT p FROM Produto p  WHERE p.categoria.nome = ?1" outra forma de escrever esse codigo
        return em.createQuery(jpql,Produto.class)
                .setParameter("nome",nome)
                .getResultList();
    }
    public BigDecimal buscarPrecoProdutoComNome(String nome){
        String jpql = "SELECT p.preco FROM Produto p  WHERE p.nome = :nome";
        return em.createQuery(jpql,BigDecimal.class)
                .setParameter("nome",nome)
                .getSingleResult();
    }
}

