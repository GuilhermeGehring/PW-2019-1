/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author 20171pf.cc0178
 */
@Entity
@Table(name = "produto")
public class Produto implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_produto", sequenceName = "seq_id_produto", 
            allocationSize = 1)
    @GeneratedValue(generator = "seq_produto", strategy = GenerationType.SEQUENCE)    
    private Integer id;
    
    @NotNull(message = "O nome não pode ser nulo")
    @Length(max = 50, message = "O nome não pode ter mais que {max} caracteres")
    @NotBlank(message = "O nome não pode ser em branco")
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;
    
    @NotNull(message = "A descrição não pode ser nulo")
    @NotBlank(message = "A descrição não pode ser em branco")
    @Length(max = 50, message = "A descrição não pode ter mais que {max} caracteres")
    @Column(name = "descricao", nullable = false, length = 50)    
    private String descricao;
    
    @Min(message = "O preço não pode ser negativo", value = 0)
    @NotNull(message = "O preço não pode ser nulo")        
    @Column(name = "preco", nullable = false, columnDefinition = "numeric(12,2)")
    private Double preco;
    
    @NotNull(message = "A marca deve ser informada")
    @ManyToOne
    @JoinColumn(name = "marca", referencedColumnName = "id", nullable = false)
    private Marca marca;
    
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Arquivo> arquivos; //composição, ou seja, arquivo não vive sem o produto. Arquivo por sua vez, referencia Produto. É bidirecional  

    public Produto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }           
    
}
