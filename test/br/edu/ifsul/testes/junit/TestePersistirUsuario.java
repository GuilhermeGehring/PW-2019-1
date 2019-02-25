package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class TestePersistirUsuario {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirUsuario() {
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
        Usuario obj = new Usuario();
        obj.setEmail("guilherme.gehring@gmail.com");
        obj.setNomeUsuario("GGehring");
        obj.setNome("Guilherme Gehring");
        obj.setSenha("bemdificil");
        obj.setTelefonePrincipal("(54)99233-4587");
        obj.setTelefoneAlternativo("(54)99148-1184");

        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }

}
