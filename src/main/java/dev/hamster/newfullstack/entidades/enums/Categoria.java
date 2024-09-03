package dev.hamster.newfullstack.entidades.enums;

import java.io.Serializable;

public enum Categoria implements Serializable {
    TAPETE(1),
    CORTINA(2),
    ESTOFADO(3),
    ESTOFADO_COURO(4);

    private int code;

    Categoria(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static Categoria valueOf(int code){
        for(Categoria valor : Categoria.values()){
            if(valor.getCode() == code){
                return valor;
            }
        } throw new IllegalArgumentException("Código de categoria inválido");
    }
}
