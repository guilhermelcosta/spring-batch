package com.springbatch.processarscriptjob.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessarScriptJobConfig {

    @Bean
    public Job processarScriptJob(JobRepository jobRepository, Step processarScriptStep) {
        return new JobBuilder("processarScriptJob", jobRepository)
                .start(processarScriptStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
