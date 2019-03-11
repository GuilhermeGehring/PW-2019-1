/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Estado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author 20171pf.cc0178
 */
public class TestePersistirEstado {
    
    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirEstado() {
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
    public void teste(){
        Estado e = new Estado();
        e.setNome("Rio Grande do Sul");
        e.setUf("RS");
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        
        List<Estado> lista = em.createQuery("from Estado order by nome").getResultList();
        for(Estado es : lista) {
                System.out.println("ID: " + es.getId() +
                        "Nome: " + es.getNome() + "UF: " + es.getUf());
        }
    }        
    
}
