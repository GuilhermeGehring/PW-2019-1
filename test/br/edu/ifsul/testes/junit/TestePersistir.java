/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Arquivo;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.ContaReceber;
import br.edu.ifsul.modelo.ContaReceberID;
import br.edu.ifsul.modelo.Equipamento;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.FormaPagamento;
import br.edu.ifsul.modelo.Foto;
import br.edu.ifsul.modelo.ItemProduto;
import br.edu.ifsul.modelo.ItemServico;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.OrdemServico;
import br.edu.ifsul.modelo.Permissao;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.modelo.Servico;
import br.edu.ifsul.modelo.Status;
import br.edu.ifsul.modelo.Usuario;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author guilherme
 */
public class TestePersistir {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistir() {
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
        Estado e = new Estado();
        e.setNome("Rio Grande do Sul");
        e.setUf("RS");

        Cidade c = new Cidade();
        c.setNome("Marau");
        c.setEstado(e);

        Permissao p = new Permissao();
        p.setNome("Administrador");
        p.setDescricao("Administrador do sistema, com todas as permissãoes");

        PessoaFisica pf = new PessoaFisica();
        pf.setEmail("guilherme.gehring@gmail.com");
        pf.setNomeUsuario("GuiGehring");
        pf.setNome("Guilherme Gehring");
        pf.setSenha("bemdificil");
        pf.setTelefonePrincipal("(54)99233-4587");
        pf.setTelefoneAlternativo("(54)99148-1184");
        pf.setBairro("Centro");
        pf.setCep("99150-000");
        pf.setCidade(c);
        pf.setComplemento("Prédio");
        pf.setCpf("045.493.270-73");
        pf.setEndereco("Av.Brasil");
        pf.setNascimento(Calendar.getInstance());
        pf.setNumero("201");
        pf.setReferencia("Próximo a");
        pf.setRg("98763489");

        Marca m = new Marca();
        m.setNome("HP");

        Equipamento eq = new Equipamento();
        eq.setDescricao("Chave");
        eq.setNumeroSerie("22175");
        eq.setMarca(m);

        Servico s = new Servico();
        s.setNome("Serviço 1");
        s.setValor(50.00);

        ItemServico is = new ItemServico();
        is.setQuantidade(3);
        is.setValorUnitario(10.30);
        is.setValorTotal(is.getValorUnitario() * is.getQuantidade());

        Arquivo a = new Arquivo();
        a.setDescricao("Descrição de produto");
        a.setArquivo(arquivo);
        a.setNomeArquivo("arq2.pdf");

        Produto pr = new Produto();
        pr.setNome("monitor");
        pr.setDescricao("21' Full HD");
        pr.setPreco(600.00);
        pr.setMarca(m);
        pr.setArquivos(arquivos);

        ItemProduto it = new ItemProduto();
        it.setQuantidade(3);
        it.setValorUnitario(200.00);
        it.setValorTotal(it.getValorUnitario() * it.getQuantidade());

        Foto f = new Foto();
        f.setDescricao("Foto do monitor");
        f.setArquivo(arquivo);
        f.setNomeFoto("img.jpg");

        OrdemServico os = new OrdemServico();
        os.setDataAbertura(Calendar.getInstance());
        os.setDataFechamento(Calendar.getInstance());
        os.setDescricaoProblema("Monitor com defeito");
        os.setResolucaoProblema("Troca do monitor");
        os.setValorProdutos(780.00);
        os.setValorServicos(80.00);
        os.setValorTotal(990.00);
        os.setStatus(Status.FECHADA);
        os.setFormaPagamento(FormaPagamento.AVISTA);
        os.setQuantidadeParcelas(1);
        os.setItemProdutos(itemProdutos);
        os.setItemServico(ItemServico);
        os.setFoto(foto);

        ContaReceberID cri = new ContaReceberID();
        cri.setNumeroParcela(1);
        cri.setOrdemServico(os);

        ContaReceber cr = new ContaReceber();
        cr.setId(cri);
        cr.setValor(990.00);
        cr.setVencimento(Calendar.getInstance());
        cr.setValorPago(990.00);
        cr.setDataPagamento(Calendar.getInstance());

        em.getTransaction().begin();
        em.persist(e);
        em.persist(c);
        em.persist(pf);
        em.persist(p);
        em.persist(m);
        em.persist(eq);
        em.persist(s);
        em.persist(is);
        em.persist(a);
        em.persist(pr);
        em.persist(it);
        em.persist(f);
        em.persist(os);
        em.persist(cr);
        em.getTransaction().commit();
    }

}
