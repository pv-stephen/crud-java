package dev.hamster.newfullstack.dto;

import dev.hamster.newfullstack.entidades.Endereco;
import java.io.Serializable;

public class EnderecoDTO implements Serializable {

    private Long id;
    private String rua;
    private String bairro;
    private String complemento;
    private ClienteDTO clienteDTO;

    public EnderecoDTO(){}

    public EnderecoDTO(Endereco endereco){
        id=endereco.getId();
        rua= endereco.getRua();
        bairro= endereco.getBairro();
        complemento=endereco.getComplemento();
        clienteDTO = new ClienteDTO(endereco.getCliente());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public ClienteDTO getClienteDTO() {
        return clienteDTO;
    }

    public void setClienteDTO(ClienteDTO clienteDTO) {
        this.clienteDTO = clienteDTO;
    }
}
