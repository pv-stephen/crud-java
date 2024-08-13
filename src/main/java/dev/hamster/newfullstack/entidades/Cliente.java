package dev.hamster.newfullstack.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity(name="clientes")
@Table(name="clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private Set<Endereco> enderecos = new HashSet<>();


    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private Set<Telefone> telefones = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Orcamento> orcamentos = new ArrayList<>();


    public Cliente(){}
    public Cliente(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Orcamento> getOrcamentos() {
        return orcamentos;
    }



    public void adicionarTelefone(Telefone telefone) {
        this.telefones.add(telefone);
    }
    public void adicionarEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
    }
    public void adicionarOrcamento(Orcamento orcamento) {
        this.orcamentos.add(orcamento);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
