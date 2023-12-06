package com.springbatch.fatura_cartao.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FaturaCartaoCredito {
    private Cliente cliente;
    private CartaoCredito cartaoCredito;
    private List<Transacao> transacoes;

    public Double getTotal() {
        return transacoes.stream()
                .mapToDouble(Transacao::getValor)
                .reduce(0.0, Double::sum);
    }
}
