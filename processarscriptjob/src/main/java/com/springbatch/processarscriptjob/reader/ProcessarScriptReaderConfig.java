package com.springbatch.processarscriptjob.reader;

import com.springbatch.processarscriptjob.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ProcessarScriptReaderConfig {

    @Bean
    @StepScope
    public FlatFileItemReader<Cliente> processarScriptReader(
            @Value("#{jobParameters['arquivoClientes']}") Resource resource) {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("processarScriptReader")
                .resource(resource)
                .delimited()
                .names("nome", "idade", "email")
                .targetType(Cliente.class)
                .build();
    }
}
