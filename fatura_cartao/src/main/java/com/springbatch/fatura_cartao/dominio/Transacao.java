package com.springbatch.fatura_cartao.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {
    private int id;
    private CartaoCredito cartaoCredito;
    private String descricao;
    private Double valor;
    private Date data;
}
