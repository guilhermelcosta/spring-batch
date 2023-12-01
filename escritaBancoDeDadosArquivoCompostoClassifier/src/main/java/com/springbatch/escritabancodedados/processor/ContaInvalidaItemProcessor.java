package com.springbatch.escritabancodedados.processor;

import com.springbatch.escritabancodedados.dominio.Cliente;
import com.springbatch.escritabancodedados.dominio.Conta;
import org.springframework.batch.item.ItemProcessor;

public class ContaInvalidaItemProcessor implements ItemProcessor<Cliente, Conta> {

    @Override
    public Conta process(Cliente cliente) {
        Conta conta = new Conta();
        conta.setClienteId(cliente.getEmail());
        return conta;
    }
}
