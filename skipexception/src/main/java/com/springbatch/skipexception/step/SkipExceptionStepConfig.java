package com.springbatch.skipexception.step;

import com.springbatch.skipexception.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SkipExceptionStepConfig {

    @Bean
    public Step skipExceptionStep(
            JobRepository jobRepository,
            PlatformTransactionManager platformTransactionManager,
            ItemReader<Cliente> skipExceptionReader,
            ItemWriter<Cliente> skipExceptionWriter) {
        return new StepBuilder("skipExceptionStepConfig", jobRepository)
                .<Cliente, Cliente>chunk(11, platformTransactionManager)
                .reader(skipExceptionReader)
                .writer(skipExceptionWriter)
//                Configuração de erros do batch. No caso abaixo, ele skipa até 2 registros
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(2)
                .build();
    }
}
