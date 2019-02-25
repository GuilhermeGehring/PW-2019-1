package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Estado;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class TestePersistirEstado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("PW-2019-1-ModelPU");
        EntityManager em = emf.createEntityManager();
        Estado e = new Estado();
        e.setNome("Rio Grande do Sul");
        e.setUf("RS");
        // Chamando a validação manualmente
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Estado>> erros
                = validador.validate(e);
        if (erros.size() > 0) {
            for (ConstraintViolation<Estado> erro : erros) {
                System.out.println("Erro: " + erro.getMessage());
            }
        } else {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            em.close();
            emf.close();
        }
    }

}
