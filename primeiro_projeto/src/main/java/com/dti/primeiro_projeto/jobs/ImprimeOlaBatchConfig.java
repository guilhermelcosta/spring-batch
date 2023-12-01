package com.dti.primeiro_projeto.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImprimeOlaBatchConfig {

    @Bean
    public Job imprimeOlaJob(Step imprimeOlaStep, JobRepository jobRepository) {
        return new JobBuilder("imprimeOlaJob", jobRepository)
                .start(imprimeOlaStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
