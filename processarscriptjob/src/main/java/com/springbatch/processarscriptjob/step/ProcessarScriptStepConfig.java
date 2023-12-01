package com.springbatch.processarscriptjob.step;

import com.springbatch.processarscriptjob.dominio.Cliente;
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
public class ProcessarScriptStepConfig {

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    @Bean
    public Step processarScriptStep(
            JobRepository jobRepository,
            ItemReader<Cliente> processarScriptReader,
            ItemProcessor<Cliente, Cliente> processarScriptProcessor,
            ItemWriter<Cliente> processarScriptWriter) {
        return new StepBuilder("processarScriptStep", jobRepository)
                .<Cliente, Cliente>chunk(1, platformTransactionManager)
                .reader(processarScriptReader)
                .processor(processarScriptProcessor)
                .writer(processarScriptWriter)
                .build();
    }
}
