/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 20171pf.cc0178
 */
@Entity
@Table(name = "item_servico")
public class ItemServico implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_item_servico", sequenceName = "seq_id_item_servico", 
            allocationSize = 1)
    @GeneratedValue(generator = "seq_item_servico", strategy = GenerationType.SEQUENCE)    
    private Integer id;
    
    @Min(message = "A quantidade não pode ser negativa", value = 0)
    @NotNull(message = "A quantidade unitário não pode ser nula")
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
            
    @Min(message = "O valor unitário não pode ser negativo", value = 0)
    @NotNull(message = "O valor unitário não pode ser nulo")        
    @Column(name = "valor_unitario", nullable = false, columnDefinition = "numeric(12,2)")
    private Double valorUnitario;
    
    @Min(message = "O valor total não pode ser negativo", value = 0)
    @NotNull(message = "O valor total não pode ser nulo")        
    @Column(name = "valor_total", nullable = false, columnDefinition = "numeric(12,2)")
    private Double valorTotal;
    
    @NotNull(message = "O serviço deve ser informado")
    @ManyToOne
    @JoinColumn(name = "servico", referencedColumnName = "id", nullable = false)
    private Servico servico;
    
    @NotNull(message = "A ordem do serviço deve ser informado")
    @ManyToOne
    @JoinColumn(name = "ordem_servico", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_item_servico_os"))
    private OrdemServico ordemServico;

    public ItemServico() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
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
        hash = 73 * hash + Objects.hashCode(this.id);
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
        final ItemServico other = (ItemServico) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
        
}
