package com.springbatch.escritabancodedados.processor;

import com.springbatch.escritabancodedados.dominio.Cliente;
import com.springbatch.escritabancodedados.dominio.Conta;
import com.springbatch.escritabancodedados.dominio.TipoConta;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.classify.Classifier;

import java.util.HashMap;
import java.util.Map;

public class EscritaBancoDeDadosProcessorClassifier implements Classifier<Cliente, ItemProcessor<?, ? extends Conta>> {

    public static final Map<TipoConta, ItemProcessor<Cliente, Conta>> processadores = new HashMap<>() {{
        put(TipoConta.PRATA, new ContaPrataItemProcessor());
        put(TipoConta.OURO, new ContaOuroItemProcessor());
        put(TipoConta.PLATINA, new ContaPlatinaItemProcessor());
        put(TipoConta.DIAMANTE, new ContaDiamanteItemProcessor());
    }};

    @Override
    public ItemProcessor<?, ? extends Conta> classify(Cliente cliente) {
        return processadores.get(TipoConta.fromFaixaSalarial(cliente.getFaixaSalarial()));
    }
}
