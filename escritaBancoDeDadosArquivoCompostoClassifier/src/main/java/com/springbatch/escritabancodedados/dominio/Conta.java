package com.springbatch.escritabancodedados.dominio;

import lombok.Getter;
import lombok.Setter;

import java.text.NumberFormat;

@Getter
@Setter
public class Conta {

    private Integer id;
    private TipoConta tipo;
    private Double limite;
    private String clienteId;

    @Override
    public String toString() {
        return "Conta {" +
                "clienteId = " + this.clienteId +
                ", tipo = " + this.tipo +
                ", limite = " + NumberFormat.getCurrencyInstance().format(this.limite) +
                "}";
    }
}
