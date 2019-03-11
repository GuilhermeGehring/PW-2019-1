/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 20171pf.cc0178
 */
@Entity
@Table(name = "conta_receber")
public class ContaReceber implements Serializable {
    
    @EmbeddedId
    private ContaReceberID id;
            
    @Min(message = "O valor não pode ser negativo", value = 0)
    @NotNull(message = "O valor não pode ser nulo")        
    @Column(name = "valor", nullable = false, columnDefinition = "numeric(12,2)")
    private Double valor;
    
    @Temporal(TemporalType.DATE)
    @NotNull(message = "A data de vencimento não pode ser nula")
    @Column(name = "vencimento", nullable = false)
    private Calendar vencimento;
    
    @Min(message = "O valor pago não pode ser negativo", value = 0)    
    @Column(name = "valor_pago", columnDefinition = "numeric(12,2)")
    private Double valorPago;
    
    @Temporal(TemporalType.DATE)    
    @Column(name = "data_pagamento")
    private Calendar dataPagamento;
    
    @NotNull(message = "A ordem do serviço deve ser informado")
    @ManyToOne
    @JoinColumn(name = "ordem_servico", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_contareceber_ordemservico"))
    private OrdemServico ordemServico;

    public ContaReceber() {
    }

    public ContaReceberID getId() {
        return id;
    }

    public void setId(ContaReceberID id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Calendar getVencimento() {
        return vencimento;
    }

    public void setVencimento(Calendar vencimento) {
        this.vencimento = vencimento;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public Calendar getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Calendar dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final ContaReceber other = (ContaReceber) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
        
}
