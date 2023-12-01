package com.springbatch.processadorclassifierjob.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ProcessadorClassifierStepConfig {

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    @Bean
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Step processadorClassifierStep(
            JobRepository jobRepository,
            FlatFileItemReader processadorClassifierReader,
            ItemProcessor processadorClassifierProcessor,
            ItemWriter processadorClassifierWriter) {
        return new StepBuilder("processadorClassifierStep", jobRepository)
                .chunk(1, platformTransactionManager)
                .reader(processadorClassifierReader)
                .processor(processadorClassifierProcessor)
                .writer(processadorClassifierWriter)
                .build();
    }
}
