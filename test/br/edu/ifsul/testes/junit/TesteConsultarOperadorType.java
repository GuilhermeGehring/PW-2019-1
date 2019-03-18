/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Usuario;
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
public class TesteConsultarOperadorType {
    
    EntityManagerFactory emf;
    EntityManager em;

    public TesteConsultarOperadorType() {
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
        List<Usuario> lista 
                = em.createQuery("select u from Usuario u "
                + "where TYPE(u) in (PessoaFisica)").getResultList();
        for(Usuario u : lista) {
            System.out.println("Nome: " + u.getNome());            
        }
    }
}
