package com.springbatch.processadorvalidacao.step;

import com.springbatch.processadorvalidacao.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ProcessadorValidacaoStepConfig {

    @Bean
    public Step processadorValidacaoStep(
            JobRepository jobRepository,
            PlatformTransactionManager platformTransactionManager,
            ItemReader<Cliente> processadorValidacaoReader,
            ItemWriter<Cliente> processadorValidacaoWriter,
            ItemProcessor<Cliente, Cliente> processadorValidacaoProcessor) {
        return new StepBuilder("processadorValidacaoStep", jobRepository)
                .<Cliente, Cliente>chunk(1, platformTransactionManager)
                .reader(processadorValidacaoReader)
                .processor(processadorValidacaoProcessor)
                .writer(processadorValidacaoWriter)
                .build();
    }
}
