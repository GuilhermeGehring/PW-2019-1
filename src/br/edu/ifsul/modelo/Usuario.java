/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author 20171pf.cc0178
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)

@Table(name = "usuario")
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING,
        length = 2)
@DiscriminatorValue(value = "US")
//@NamedQuery(name = "todosUsuarioOrdemNome", query = "from Usuario where email = :email and senha = :senha order by nome asc")
@NamedQuery(name = "todosUsuarioOrdemNome", query = "from Usuario where email = :email and senha = :senha order by nome asc")

//definir uma Namequery para fazer a autenticação do Usuario na aplicação web
public class Usuario implements Serializable {

    @Id
    @NotBlank(message = "O nome de usuário deve ser informado")
    @Length(max = 20, message = "O usuário não pode ter mais que {max} caracteres")
    @Column(name = "nome_usuario", length = 20, nullable = false)
    private String nomeUsuario;

    @NotBlank(message = "A senha deve ser informada")
    @Length(max = 20, message = "A senha não pode ter mais que {max} caracteres")
    @Column(name = "senha", length = 20, nullable = false)
    private String senha;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "A data de cadastro não pode ser nula")
    @Column(name = "data_cadastro", nullable = false)
    private Calendar dataCadastro;

    @NotBlank(message = "O nome deve ser informado")
    @Length(max = 50, message = "O nome não pode ter mais que {max} caracteres")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @NotBlank(message = "O email deve ser informado")
    @Length(max = 50, message = "O email não pode ter mais que {max} caracteres")
    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @NotBlank(message = "O telefone principal deve ser informado")
    @Length(max = 14, message = "O telefone principal não pode ter mais que {max} caracteres")
    @Column(name = "telefone_principal", length = 14, nullable = false)
    private String telefonePrincipal;

    @Length(max = 14, message = "O telefone alternativo não pode ter mais que {max} caracteres")
    @Column(name = "telefone_alternativo", length = 14)
    private String telefoneAlternativo;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "permissões",
            joinColumns = 
                @JoinColumn(name = "nome_usuario", referencedColumnName = "nome_usuario", nullable = false),
            inverseJoinColumns =
                @JoinColumn(name = "permissao", referencedColumnName = "nome", nullable = false)
    )
    private Set<Permissao> permissoes; //associação bidirecional

    public Usuario() {
        this.dataCadastro = Calendar.getInstance();
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonePrincipal() {
        return telefonePrincipal;
    }

    public void setTelefonePrincipal(String telefonePrincipal) {
        this.telefonePrincipal = telefonePrincipal;
    }

    public String getTelefoneAlternativo() {
        return telefoneAlternativo;
    }

    public void setTelefoneAlternativo(String telefoneAlternativo) {
        this.telefoneAlternativo = telefoneAlternativo;
    }
        
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.nomeUsuario);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nomeUsuario, other.nomeUsuario)) {
            return false;
        }
        return true;
    }

    public void setBairro(String centro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Set<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Set<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

}
