package com.projeto.migracaodados.dominio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosBancarios {

    private Integer id;
    private Integer pessoaId;
    private Integer agencia;
    private Integer conta;
    private Integer banco;

    public boolean isValido() {
        return conta != null && banco != null;
    }
}
