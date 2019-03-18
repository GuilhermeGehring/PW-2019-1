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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public void teste() throws IOException {
        Estado e = new Estado();
        e.setNome("Rio Grande do Sul");
        e.setUf("RS");

        Cidade c = new Cidade();
        c.setNome("Marau");
        c.setEstado(e);

        Permissao p = new Permissao();
        p.setNome("Administrador");
        p.setDescricao("Administrador do sistema, com todas as permissões");

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
        
        OrdemServico os = new OrdemServico();
        os.setDataAbertura(Calendar.getInstance());
        os.setDataFechamento(Calendar.getInstance());
        os.setDescricaoProblema("Monitor com defeito");
        os.setResolucaoProblema("Troca do monitor");
        os.setValorProdutos(780.00);
        os.setValorServicos(80.00);
        os.setValorTotal(990.00);
        os.setStatus(Status.FECHADA);
        os.setFormaPagamento(FormaPagamento.APRAZO);
        os.setQuantidadeParcelas(3);
        os.setEquipamento(eq);
        os.setPessoaFisica(pf);
        os.setUsuario(pf);
        os.gerarContasReceber();
        
        //os.setItemProdutos(itemProdutos);
        //os.setItemServico(ItemServico);
        //os.setFoto(foto);

        ItemServico is = new ItemServico();
        is.setQuantidade(3);
        is.setValorUnitario(10.30);
        is.setValorTotal(is.getValorUnitario() * is.getQuantidade());
        is.setOrdemServico(os);
        is.setServico(s);       

        Produto pr = new Produto();
        pr.setNome("monitor");
        pr.setDescricao("21' Full HD");
        pr.setPreco(600.00);
        pr.setMarca(m);
        
        Arquivo a = new Arquivo();
        a.setDescricao("Descrição de produto");
        Path path = Paths.get("C:\\Users\\20171pf.cc0178\\Downloads\\tux.jpg");       
        a.setArquivo(Files.readAllBytes(path));
        a.setNomeArquivo("tux.jpeg");
        a.setProduto(pr);

        ItemProduto it = new ItemProduto();
        it.setQuantidade(3);
        it.setValorUnitario(200.00);
        it.setValorTotal(it.getValorUnitario() * it.getQuantidade());
        it.setOrdemServico(os);
        it.setProduto(pr);

        Foto f = new Foto();
        f.setDescricao("Foto do monitor");        
        f.setArquivo(Files.readAllBytes(path));
        f.setNomeFoto("tux.jpeg");
        f.setOrdemServico(os);

        /*ContaReceberID cri = new ContaReceberID();
        cri.setNumeroParcela(1);
        cri.setOrdemServico(os);

        ContaReceber cr = new ContaReceber();
        cr.setId(cri);
        cr.setValor(990.00);
        cr.setVencimento(Calendar.getInstance());
        cr.setValorPago(990.00);
        cr.setDataPagamento(Calendar.getInstance());*/

        em.getTransaction().begin();
        em.persist(e);
        em.persist(c);
        em.persist(pf);
        em.persist(p);
        em.persist(m);
        em.persist(eq);
        em.persist(s);
        em.persist(os);
        em.persist(is);
        em.persist(pr);
        em.persist(a);        
        em.persist(it);
        em.persist(f);        
        //em.persist(cr);
        em.getTransaction().commit();
    }

}
