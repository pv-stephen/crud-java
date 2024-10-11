package dev.hamster.newfullstack.entidades.excecao;

import org.springframework.dao.DataIntegrityViolationException;

public class ViolacaoIntegridadeBD extends DataIntegrityViolationException {

    public ViolacaoIntegridadeBD(String msg) {
        super(msg);
    }

    public ViolacaoIntegridadeBD(String msg, Throwable cause) {
        super(msg, cause);
    }
}
