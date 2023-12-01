package com.springbatch.escritaarquivoflat.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {

    private String nome;
    private String sobrenome;
    private String idade;
    private String email;

    @Override
    public String toString() {
        return "Cliente: {" +
                "nome = " + this.getNome() +
                ", sobrenome = " + this.getSobrenome() +
                ", idade = " + this.getIdade() +
                ", email = " + this.getEmail() +
                "}";
    }

}
