package com.springbatch.leitorarquivo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoLeituraMultiplosJobConfig {

    @Bean
    public Job arquivoLeituraFixaJob(JobRepository jobRepository, Step arquivoLeituraFixaStep) {
        return new JobBuilder("arquivoLeituraFixaJob", jobRepository)
                .start(arquivoLeituraFixaStep)
                /*o incrementar nao permite que o Job seja reiniciado*/
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
