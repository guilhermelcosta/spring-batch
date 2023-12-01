package com.springbatch.processadorvalidacao.reader;

import com.springbatch.processadorvalidacao.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ProcessadorValidacaoReaderConfig {

    @StepScope
    @Bean(name = "processadorValidacaoReader")
    public FlatFileItemReader<Cliente> processadorValidacaoReader(@Value("#{jobParameters['arquivoClientes']}") Resource resource) {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("processadorValidacaoReader")
                .resource(resource)
                .delimited()
                .names("nome", "idade", "email")
                .targetType(Cliente.class)
                .build();
    }
}
