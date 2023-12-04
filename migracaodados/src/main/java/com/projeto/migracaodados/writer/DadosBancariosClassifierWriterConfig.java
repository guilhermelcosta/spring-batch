package com.projeto.migracaodados.writer;

import com.projeto.migracaodados.dominio.DadosBancarios;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DadosBancariosClassifierWriterConfig {

    @Bean
    public ClassifierCompositeItemWriter<DadosBancarios> dadosBancariosClassifierWriter(
            JdbcBatchItemWriter<DadosBancarios> bancoDadosBancariosWriter,
            FlatFileItemWriter<DadosBancarios> arquivoDadosBancariosInvalidosWriter
    ) {
        return new ClassifierCompositeItemWriterBuilder<DadosBancarios>()
                .classifier(classifier(bancoDadosBancariosWriter, arquivoDadosBancariosInvalidosWriter))
                .build();
    }

    private Classifier<DadosBancarios, ItemWriter<? super DadosBancarios>> classifier(
            JdbcBatchItemWriter<DadosBancarios> bancoDadosBancariosWriter,
            FlatFileItemWriter<DadosBancarios> arquivoDadosBancariosInvalidosWriter
    ) {
        return dadosBancarios -> {
                if (dadosBancarios.isValido())
                    return bancoDadosBancariosWriter;
                else
                    return arquivoDadosBancariosInvalidosWriter;
        };
    }
}
