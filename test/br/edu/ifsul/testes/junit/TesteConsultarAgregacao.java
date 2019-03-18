/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author 20171pf.cc0178
 */
public class TesteConsultarAgregacao {
    
    EntityManagerFactory emf;
    EntityManager em;

    public TesteConsultarAgregacao() {
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("PW-2019-1-ModelPU");
        em = emf.createEntityManager();
    }

    @After
    public void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    public void teste() {
        //consulta que retorna a quantidade de vendas por produto
        /*Query query = em.createNativeQuery("select p.nome, count(*) from produto p, item_produto ios "
            + "where p.id = ios.produto group by p.nome");
        for (Object linha : query.getResultList()) {
            Object[] obj = (Object[]) linha;
            System.out.println("Produto: " + obj[0] + " \nQuantidade de item produtos: " + obj[1]);
        }*/
        
        Query q2 = em.createQuery("select p.nome, m.nome from Produto p, Marca m "
                + "where p.marca = m.id");
        for (Object linha : q2.getResultList()) {
            Object[] obj = (Object[]) linha;
            System.out.println("\n\nProduto: " + obj[0] + " \nMarca: " + obj[1]);
        }
        
        //qual é a diferença entre essas duas listagens ?
        
        Query q3 = em.createQuery("select p from Produto p");
        List<Produto> list = q3.getResultList();
        for(Produto produto : list) {
            System.out.println("\n\nProduto: " + produto.getNome() + " \nMarca: " + produto.getMarca().getNome());
        }
    }
}
