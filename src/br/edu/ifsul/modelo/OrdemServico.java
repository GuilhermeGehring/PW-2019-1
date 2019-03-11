/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author 20171pf.cc0178
 */
@Entity
@Table(name = "ordem_servico")
public class OrdemServico implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_ordem_arquivo", sequenceName = "seq_ordem_arquivo_id", 
            allocationSize = 1)
    @GeneratedValue(generator = "seq_ordem_arquivo", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Temporal(TemporalType.DATE)
    @NotNull(message = "A data de abertura não pode ser nula")
    @Column(name = "data_abertura", nullable = false)
    private Calendar dataAbertura;
    
    @Temporal(TemporalType.DATE)
    @NotNull(message = "A data de fechamento não pode ser nula")
    @Column(name = "data_fechamento", nullable = true)
    private Calendar dataFechamento;
    
    @NotNull(message = "A descrição do problema não pode ser nula")    
    @NotBlank(message = "A descrição do problema não pode ser em branco")
    @Column(name = "descricao_problema", nullable = false, columnDefinition = "text")
    private String descricaoProblema;
    
    @NotNull(message = "A resolução do problema não pode ser nula")    
    @NotBlank(message = "A resolução do problema não pode ser em branco")
    @Column(name = "resolucao_problema", length = 50, columnDefinition = "text")
    private String resolucaoProblema;
    
    @Min(message = "O valor dos produtos não pode ser negativo", value = 0)
    @NotNull(message = "O valor dos produtos não pode ser nulo")        
    @Column(name = "valor_produtos", nullable = false, columnDefinition = "numeric(12,2)")
    private Double valorProdutos;
    
    @Min(message = "O valor dos serviços não pode ser negativo", value = 0)
    @NotNull(message = "O valor dos serviços não pode ser nulo")        
    @Column(name = "valor_servicos", nullable = false, columnDefinition = "numeric(12,2)")
    private Double valorServicos;
    
    @Min(message = "O valor total não pode ser negativo", value = 0)
    @NotNull(message = "O valor total não pode ser nulo")        
    @Column(name = "valor_total", nullable = false, columnDefinition = "numeric(12,2)")
    private Double valorTotal;
    
    @NotNull(message = "O status não pode ser nulo")    
    @Column(name = "status", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @NotNull(message = "A forma de pagamento não pode ser nula")    
    @Column(name = "forma_pagamento", nullable = false, length = 6)
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;
    
    @Min(message = "A quantidade de parcelas não pode ser negativa", value = 0)
    @NotNull(message = "A quantidade de parcelas não pode ser nula")
    @Column(name = "quantidade_parcelas", nullable = false)
    private Integer quantidadeParcelas;
    
    @NotNull(message = "O equipamento deve ser informado")
    @ManyToOne
    @JoinColumn(name = "equipamento", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_os_equipamento"))
    private Equipamento equipamento;
    
    @NotNull(message = "O usuário deve ser informado")
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "nome_usuario", nullable = false,
            foreignKey = @ForeignKey(name = "fk_os_usuario"))
    private Usuario usuario;
    
    @NotNull(message = "A pessoa deve ser informada")
    @ManyToOne
    @JoinColumn(name = "pessoa_fisica", referencedColumnName = "nome_usuario", nullable = false,
            foreignKey = @ForeignKey(name = "fk_ordem_servico_pf"))
    private PessoaFisica pessoaFisica;
    
    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemProduto> itemProdutos; //composição, ou seja, arquivo não vive sem o produto. Arquivo por sua vez, referencia Produto. É bidirecional  
    
    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemServico> itemServico; //composição, ou seja, arquivo não vive sem o produto. Arquivo por sua vez, referencia Produto. É bidirecional  
    
    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Foto> foto; //composição, ou seja, arquivo não vive sem o produto. Arquivo por sua vez, referencia Produto. É bidirecional  
    
    @OneToMany(mappedBy = "id.ordemServico", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ContaReceber> contasReceber; //composição, ou seja, arquivo não vive sem o produto. Arquivo por sua vez, referencia Produto. É bidirecional  

    public OrdemServico() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Calendar dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Calendar getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Calendar dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public String getResolucaoProblema() {
        return resolucaoProblema;
    }

    public void setResolucaoProblema(String resolucaoProblema) {
        this.resolucaoProblema = resolucaoProblema;
    }

    public Double getValorProdutos() {
        return valorProdutos;
    }

    public void setValorProdutos(Double valorProdutos) {
        this.valorProdutos = valorProdutos;
    }

    public Double getValorServicos() {
        return valorServicos;
    }

    public void setValorServicos(Double valorServicos) {
        this.valorServicos = valorServicos;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(Integer quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public List<ItemProduto> getItemProdutos() {
        return itemProdutos;
    }

    public void setItemProdutos(List<ItemProduto> itemProdutos) {
        this.itemProdutos = itemProdutos;
    }

    public List<ItemServico> getItemServico() {
        return itemServico;
    }

    public void setItemServico(List<ItemServico> itemServico) {
        this.itemServico = itemServico;
    }

    public List<Foto> getFoto() {
        return foto;
    }

    public void setFoto(List<Foto> foto) {
        this.foto = foto;
    }
    
    public List<ContaReceber> getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(List<ContaReceber> contasReceber) {
        this.contasReceber = contasReceber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrdemServico other = (OrdemServico) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }           
    
}
