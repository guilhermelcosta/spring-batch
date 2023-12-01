package com.springbatch.escritaarquivoflat.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GrupoLancamento {

    private Integer codigoNaturezaDespesa;
    private String descricaoNaturezaDespesa;
    private Lancamento lancamentoTmp;
    private List<Lancamento> lancamentos = new ArrayList<>();

    public Double getTotal() {
        return lancamentos
                .stream()
                .mapToDouble(Lancamento::getValor)
                .reduce(0.0, Double::sum);
    }
}
