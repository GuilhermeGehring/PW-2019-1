/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author 20171pf.cc0178
 */
@Entity
@Table(name = "pessoa_fisica")
@DiscriminatorValue(value = "PF")
public class PessoaFisica extends Usuario implements Serializable {

    @CPF(message = "O CPF deve ser válido")
    @NotBlank(message = "O CPF não pode ser nulo")
    @Length(max = 14, message = "O CPF deve ter {max} caracteres")
    @Column(name = "cpf", length = 14, nullable = false)
    private String cpf;

    @NotBlank(message = "O RG não pode ser nulo")
    @Length(max = 10, message = "O RG deve ter no máximo {max} caracteres")
    @Column(name = "rg", length = 10, nullable = false)
    private String rg;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "O nascimento deve ser informado")
    @Column(name = "nascimento", nullable = false)
    private Calendar nascimento;

    @NotBlank(message = "O endereço não pode ser nulo")
    @Length(max = 50, message = "O endereço deve ter no máximo {max} caracteres")
    @Column(name = "endereco", length = 50, nullable = false)
    private String endereco;

    @NotBlank(message = "O número não pode ser nulo")
    @Length(max = 10, message = "O número deve ter no máximo {max} caracteres")
    @Column(name = "numero", length = 10, nullable = false)
    private String numero;

    @Length(max = 20, message = "O complemento deve ter no máximo {max} caracteres")
    @Column(name = "complemento", length = 20)
    private String complemento;

    @NotBlank(message = "O CEP não pode ser nulo")
    @Length(max = 9, message = "O CEP deve ter no máximo {max} caracteres")
    @Column(name = "cep", length = 9, nullable = false)
    private String cep;

    @NotBlank(message = "O bairro não pode ser nulo")
    @Length(max = 50, message = "O bairro deve ter no máximo {max} caracteres")
    @Column(name = "bairro", length = 50, nullable = false)
    private String bairro;

    @Length(max = 20, message = "A Referência deve ter no máximo {max} caracteres")
    @Column(name = "referencia", length = 20)
    private String referencia;

    @ManyToOne
    @JoinColumn(name = "cidade", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_pf_cidade"))
    private Cidade cidade;

    public PessoaFisica() {
        super();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Calendar getNascimento() {
        return nascimento;
    }

    public void setNascimento(Calendar nascimento) {
        this.nascimento = nascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
