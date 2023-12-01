package com.springbatch.leitorarquivo.reader;

import com.springbatch.leitorarquivo.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ArquivoLeituraMultiplosReaderConfig {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @StepScope
    @Bean(name = "arquivoLeituraFixaReader")
    public FlatFileItemReader<Cliente> arquivoLeituraFixaReader(@Value("#{jobParameters['arquivoClientes']}") Resource arquivoClientes, LineMapper lineMapper) {
        return new FlatFileItemReaderBuilder()
                .name("arquivoLeituraFixaReader")
                .resource(arquivoClientes)
                .lineMapper(lineMapper)
                .build();
    }
}
