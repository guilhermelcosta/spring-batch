package com.springbatch.escritaarquivoflat.reader;

import com.springbatch.escritaarquivoflat.entities.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class EscritaArquivoFlatReaderConfig {

    @Bean
    @StepScope
    public FlatFileItemReader<Cliente> escritaArquivoFlatReader(
            @Value("#{jobParameters['arquivoClientes']}") Resource arquivoClientesEntrada
    ) {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("escritaArquivoFlatReader")
                .resource(arquivoClientesEntrada)
                .fixedLength()
                .columns(new Range(1, 10), new Range(11, 20), new Range(21, 23), new Range(24, 43))
                .names("nome", "sobrenome", "idade", "email")
                .targetType(Cliente.class)
                .build();
    }
}
