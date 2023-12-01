package com.springbatch.escritabancodedados.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EscritaBancoDeDadosJobConfig {

    @Autowired
    JobRepository jobRepository;

    @Bean
    public Job escritaBancoDeDadosJob(Step escritaBancoDeDadosStep) {
        return new JobBuilder("escritaBancoDeDadosJob", jobRepository)
                .start(escritaBancoDeDadosStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
