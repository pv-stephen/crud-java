package dev.hamster.newfullstack.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name="clientes")
@Table(name="clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String nome;

    @OneToMany(mappedBy = "cliente")
    private Set<Telefone> telefones = new HashSet<>();
    @OneToMany(mappedBy = "cliente")
    private Set<Endereco> enderecos = new HashSet<>();

    public Cliente() {}

    public Cliente(Long ID, String nome) {
        this.ID = ID;
        this.nome = nome;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
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


    public void adicionarTelefone(Telefone telefone){
        telefone.setCliente(this);
        telefones.add(telefone);
    }

    public void adicionarEndereco(Endereco endereco){
        endereco.setCliente(this);
        enderecos.add(endereco);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(ID, cliente.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}