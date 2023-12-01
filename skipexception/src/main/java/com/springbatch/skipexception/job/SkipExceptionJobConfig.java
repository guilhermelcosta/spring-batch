package com.springbatch.skipexception.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SkipExceptionJobConfig {

    @Bean
    public Job skipExceptionJob(Step skipExceptionStep, JobRepository jobRepository) {
        return new JobBuilder("skipExceptionJobConfig", jobRepository)
                .start(skipExceptionStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
