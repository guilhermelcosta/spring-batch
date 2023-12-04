package com.projeto.migracaodados.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class MigracaoDadosJobConfig {

    @Autowired
    private JobRepository jobRepository;

    @Bean
    public Job migracaoDadosJob(
            @Qualifier("migracaoPessoasStep") Step migracaoPessoasStep,
            @Qualifier("migracaoDadosBancariosStep") Step migracaoDadosBancariosStep
    ) {
        return new JobBuilder("migracaoDadosJob", jobRepository)
//                Versão original sem steps ocorrendo em paralelo
//                .start(migracaoDadosBancariosStep)
//                .next(migracaoPessoasStep)
//                Implementação para que os steps sejam executados em paralelo
                .start(stepsParalelos(migracaoPessoasStep, migracaoDadosBancariosStep))
//                Necessário para finalizar steps em paralelo
                .end()
                .incrementer(new RunIdIncrementer())
                .build();
    }

//    Otimização para execução de steps em paralelo
    private Flow stepsParalelos(Step migracaoPessoasStep, Step migracaoDadosBancariosStep) {
        Flow migrarDadosBancariosFlow = new FlowBuilder<Flow>("migrarDadosBancariosFlow")
                .start(migracaoDadosBancariosStep)
                .build();

        return new FlowBuilder<Flow>("stepsParalelos")
                .start(migracaoPessoasStep)
                .split(new SimpleAsyncTaskExecutor())
                .add(migrarDadosBancariosFlow)
                .build();
    }
}
