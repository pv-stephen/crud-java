package dev.hamster.newfullstack.dto;

import dev.hamster.newfullstack.entidades.Cliente;
import dev.hamster.newfullstack.entidades.Endereco;
import dev.hamster.newfullstack.entidades.Telefone;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ClienteDTO implements Serializable {

    private Long id;
    private String nome;

    private Set<Telefone> telefones = new HashSet<>();
    private Set<Endereco> enderecos = new HashSet<>();


    public ClienteDTO(){}
    public ClienteDTO(Cliente cliente){
        id = cliente.getId();
        nome = cliente.getNome();
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

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
