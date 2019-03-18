/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.modelo.Usuario;
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
public class TesteConsultarNamedQuery {
    
    EntityManagerFactory emf;
    EntityManager em;

    public TesteConsultarNamedQuery() {
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
        
        List<Usuario> u = em.createNamedQuery("todosUsuarioOrdemNome")
                .setParameter("email","guilherme.gehring@gmail.com")
                .setParameter("senha","bemdificil").getResultList();
        
        if (u != null) {
            System.out.println("Nome: " + u.get(0).getNome());            
        } else {            
            System.out.println("Não autenticou");
        }
        
    }
}
