package com.springbatch.leitorarquivo.reader;

import com.springbatch.leitorarquivo.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ArquivoLeituraFixaReaderConfig {

    @StepScope
    @Bean(name = "arquivoLeituraFixaReader")
    public FlatFileItemReader<Cliente> arquivoLeituraFixaReader(@Value("#{jobParameters['arquivoClientes']}") Resource arquivoClientes) {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("reader")
                .resource(arquivoClientes)
                .fixedLength()
                .columns(new Range[]{new Range(1, 10), new Range(11, 20), new Range(21, 23), new Range(24, 43)})
                .names("nome", "sobrenome", "idade", "email")
                .targetType(Cliente.class)
                .build();
    }
}
