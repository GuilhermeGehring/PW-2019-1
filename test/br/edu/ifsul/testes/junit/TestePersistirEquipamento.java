/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Equipamento;
import br.edu.ifsul.modelo.Marca;
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
public class TestePersistirEquipamento {
 
    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirEquipamento() {
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
        Equipamento e = new Equipamento();
        e.setDescricao("Chave");
        e.setNumeroSerie("2206487");
        e.setMarca(em.find(Marca.class, 1));
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }
}
