package com.springbatch.escritabancodedados.processor;

import com.springbatch.escritabancodedados.dominio.Cliente;
import com.springbatch.escritabancodedados.dominio.Conta;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemProcessorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EscritaBancoDeDadosProcessorConfig {

    @Bean
    public ItemProcessor<Cliente, Conta> escritaBancoDeDadosProcessor() {
        return new ClassifierCompositeItemProcessorBuilder<Cliente, Conta>()
                .classifier(new EscritaBancoDeDadosProcessorClassifier())
                .build();
    }
}
