package dev.hamster.newfullstack.entidades.excecao;

import org.springframework.stereotype.Component;

@Component
public class Mensagem {

    private String mensagem;

    public Mensagem(){}

    public Mensagem(String mensagem){
        this.mensagem = mensagem;
    }
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
