package dev.hamster.newfullstack.dto;

import dev.hamster.newfullstack.entidades.Telefone;

import java.io.Serializable;

public class TelefoneDTO implements Serializable {

    private Long id;
    private String numero;

    public TelefoneDTO() {
    }
    public TelefoneDTO(Telefone telefone) {
        id = telefone.getId();
        numero = telefone.getNumero();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
