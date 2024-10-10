package dev.hamster.newfullstack.entidades.excecao;

public class ExcecaoCampoObrigatorio extends RuntimeException{

    public ExcecaoCampoObrigatorio(String mensagem){
        super(mensagem);
    }
}
