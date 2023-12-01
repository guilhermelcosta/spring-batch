package com.springbatch.escritaarquivoflat.step;

import com.springbatch.escritaarquivoflat.entities.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class EscritaArquivoFlatStepConfig {

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    @Autowired
    JobRepository jobRepository;

    @Bean
    public Step escritaArquivoFlatStep(
            ItemReader<Cliente> escritaArquivoFlatReader,
            ItemWriter<Cliente> escritaArquivoFlatWriter,
            ItemProcessor<Cliente, Cliente> escritaArquivoFlatProcessor
    ) {
        return new StepBuilder("escritaArquivoFlatStep", jobRepository)
                .<Cliente, Cliente>chunk(1, platformTransactionManager)
                .reader(escritaArquivoFlatReader)
                .processor(escritaArquivoFlatProcessor)
                .writer(escritaArquivoFlatWriter)
                .build();
    }
}
