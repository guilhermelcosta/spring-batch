package com.springbatch.fatura_cartao.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FaturaCartaoJobConfig {

    @Autowired
    JobRepository jobRepository;

    @Bean
    public Job faturaCartaoJob(Step faturaCartaoStep) {
        return new JobBuilder("faturaCartaoJob", jobRepository)
                .start(faturaCartaoStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
