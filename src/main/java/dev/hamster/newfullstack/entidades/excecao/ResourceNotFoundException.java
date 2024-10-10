package dev.hamster.newfullstack.entidades.excecao;


public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException() {super("Objeto não encontrado.");}

    public ResourceNotFoundException(String mensagem) {super(mensagem);}
}
