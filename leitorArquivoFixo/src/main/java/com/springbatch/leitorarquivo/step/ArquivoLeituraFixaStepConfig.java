package com.springbatch.leitorarquivo.step;

import com.springbatch.leitorarquivo.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ArquivoLeituraFixaStepConfig {

    @Bean
    public Step arquivoLeituraFixaStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager, ItemReader<Cliente> reader, ItemWriter<Cliente> writer) {
        return new StepBuilder("arquivoLeituraFixaStep", jobRepository)
                .<Cliente, Cliente>chunk(1, platformTransactionManager)
                .reader(reader)
                .writer(writer)
                .build();
    }
}
