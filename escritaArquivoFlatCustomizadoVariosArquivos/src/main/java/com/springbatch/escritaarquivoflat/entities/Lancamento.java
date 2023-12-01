package com.springbatch.escritaarquivoflat.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Lancamento {
    private String descricao;
    private Date data;
    private Double valor;
}
