package com.springbatch.processadorclassifierjob.processor;

import com.springbatch.processadorclassifierjob.dominio.Transacao;
import org.springframework.batch.item.ItemProcessor;

public class TransacaoProcessor implements ItemProcessor<Transacao, Transacao> {
    @Override
    public Transacao process(Transacao transacao) throws Exception {
        System.out.printf("Aplicando regra de negocio na transacao %s%n", transacao.getId());
        return transacao;
    }
}
