package com.springbatch.processadorclassifierjob.processor;

import com.springbatch.processadorclassifierjob.dominio.Cliente;
import org.springframework.batch.item.ItemProcessor;

public class ClienteProcessor implements ItemProcessor<Cliente, Cliente> {
    @Override
    public Cliente process(Cliente cliente) {
        System.out.printf("Aplicando regra de negocio no cliente %s%n", cliente.getEmail());
        return cliente;
    }
}
