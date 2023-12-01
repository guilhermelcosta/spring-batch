package com.springbatch.escritaarquivoflat.reader;

import com.springbatch.escritaarquivoflat.entities.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
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
//                única mudança para arquivos delimitados, em relação aos de largura fixa
                .delimited()
//                para configurar um delimitador personalizado
                .delimiter(",")
                .names("nome", "sobrenome", "idade", "email")
                .targetType(Cliente.class)
                .build();
    }
}
