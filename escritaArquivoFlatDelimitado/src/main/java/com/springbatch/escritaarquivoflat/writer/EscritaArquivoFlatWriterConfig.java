package com.springbatch.escritaarquivoflat.writer;

import com.springbatch.escritaarquivoflat.entities.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.WritableResource;

@Configuration
public class EscritaArquivoFlatWriterConfig {

    @Bean
    @StepScope
    public FlatFileItemWriter<Cliente> escritaArquivoFlatWriter(
            @Value("#{jobParameters['arquivoClientesSaida']}") WritableResource arquivoClientesSaida
    ) {
        return new FlatFileItemWriterBuilder<Cliente>()
                .name("escritaArquivoFlatWriter")
                .resource(arquivoClientesSaida)
//                única mudança para arquivos delimitados, em relação aos de largura fixa
                .delimited()
//                para configurar um delimitador personalizado
                .delimiter(";")
                .names("nome", "sobrenome", "idade", "email")
                .build();
    }
}
