package com.springbatch.escritabancodedados.processor;

import com.springbatch.escritabancodedados.dominio.Cliente;
import com.springbatch.escritabancodedados.dominio.Conta;
import com.springbatch.escritabancodedados.dominio.TipoConta;
import org.springframework.batch.item.ItemProcessor;

public class ContaPrataItemProcessor implements ItemProcessor<Cliente, Conta> {

    @Override
    public Conta process(Cliente cliente) {
        Conta conta = new Conta();
        conta.setClienteId(cliente.getEmail());
        conta.setTipo(TipoConta.PRATA);
        conta.setLimite(500.0);
        return conta;
    }
}
