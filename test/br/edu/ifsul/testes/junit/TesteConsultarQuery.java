/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Produto;
import java.io.IOException;
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
public class TesteConsultarQuery {
    
    EntityManagerFactory emf;
    EntityManager em;

    public TesteConsultarQuery() {
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
        
        //mostrar a consulta sem o parametro
        //instrução HQL (referencia as classes e os seus respectivos atributos)
        Query consulta = em.createQuery("from Produto where nome like 'm%'");
        List<Produto> lista =  consulta.getResultList();
        for (Produto p : lista) {
            System.out.println("ID: " + p.getId() + " Nome: " + p.getNome());
        }
    }
}
