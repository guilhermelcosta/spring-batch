package com.dti.primeiro_projeto.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ImprimeOlaStepConfig {

    @Bean
    public Step imprimeOlaStep(Tasklet imprimeOlaTasklet, PlatformTransactionManager platformTransactionManager, JobRepository jobRepository) {
        return new StepBuilder("ImprimeOlaStepConfig", jobRepository)
                .tasklet(imprimeOlaTasklet, platformTransactionManager)
                .build();
    }
}
