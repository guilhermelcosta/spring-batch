package com.springbatch.escritabancodedados.writer;

import com.springbatch.escritabancodedados.dominio.Conta;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EscritaWriterConfig {

    @Bean
    public CompositeItemWriter<Conta> escritaWriter(
            JdbcBatchItemWriter<Conta> escritaBancoDeDadosWriter,
            @Qualifier("escritaArquivosWriter") FlatFileItemWriter<Conta> escritaArquivosWriter
    ) {
        return new CompositeItemWriterBuilder<Conta>()
                .delegates(escritaArquivosWriter, escritaBancoDeDadosWriter)
                .build();
    }
}
