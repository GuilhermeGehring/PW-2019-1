/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.modelo.PessoaFisica;
import java.util.Calendar;
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
public class TestePersistirPessoaFisica {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirPessoaFisica() {
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
        PessoaFisica obj = new PessoaFisica();
        obj.setEmail("guilherme.gehring@gmail.com");
        obj.setNomeUsuario("GuiGehring");
        obj.setNome("Guilherme Gehring");
        obj.setSenha("bemdificil");
        obj.setTelefonePrincipal("(54)99233-4587");
        obj.setTelefoneAlternativo("(54)99148-1184");
        obj.setBairro("Centro");
        obj.setCep("99150-000");
        obj.setCidade(em.find(Cidade.class, 1));
        obj.setComplemento("Prédio");
        obj.setCpf("045.493.270-73");
        obj.setEndereco("Av.Brasil");
        obj.setNascimento(Calendar.getInstance());
        obj.setNumero("201");
        obj.setReferencia("Próximo a");
        obj.setRg("98763489");
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }
}
