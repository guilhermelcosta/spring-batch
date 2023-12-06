package com.springbatch.fatura_cartao.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartaoCredito {
    private int numeroCartaoCredito;
    private Cliente cliente;
}
