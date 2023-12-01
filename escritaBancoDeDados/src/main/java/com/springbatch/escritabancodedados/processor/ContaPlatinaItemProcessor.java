package com.springbatch.escritabancodedados.processor;

import com.springbatch.escritabancodedados.dominio.Cliente;
import com.springbatch.escritabancodedados.dominio.Conta;
import com.springbatch.escritabancodedados.dominio.TipoConta;
import org.springframework.batch.item.ItemProcessor;

public class ContaPlatinaItemProcessor implements ItemProcessor<Cliente, Conta> {

    @Override
    public Conta process(Cliente cliente) throws Exception {
        Conta conta = new Conta();
        conta.setClienteId(cliente.getEmail());
        conta.setTipo(TipoConta.PLATINA);
        conta.setLimite(2500.0);
        return conta;
    }
}
