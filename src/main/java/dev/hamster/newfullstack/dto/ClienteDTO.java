package dev.hamster.newfullstack.dto;

import dev.hamster.newfullstack.entidades.Cliente;

import java.io.Serializable;
import java.util.List;

public class ClienteDTO implements Serializable {

    private Long id;
    private String nome;

    private List<TelefoneDTO> telefones;
    private List<EnderecoDTO> enderecos;

    public ClienteDTO() {}
    public ClienteDTO(Cliente cliente) {
        id= cliente.getID();
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

    public List<TelefoneDTO> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneDTO> telefones) {
        this.telefones = telefones;
    }

    public List<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }
}


