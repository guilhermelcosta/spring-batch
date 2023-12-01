package com.springbatch.escritabancodedados.step;

import com.springbatch.escritabancodedados.dominio.Cliente;
import com.springbatch.escritabancodedados.dominio.Conta;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class EscritaBancoDeDadosStepConfig {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    @Bean
    public Step escritaBancoDeDadosStep(
            ItemReader<Cliente> escritaBancoDeDadosReader,
            ItemProcessor<Cliente, Conta> escritaBancoDeDadosProcessor,
            ItemWriter<Conta> escritaBancoDeDadosWriter
    ) {
        return new StepBuilder("escritaBancoDeDadosStep", jobRepository)
                .<Cliente, Conta>chunk(100, platformTransactionManager)
                .reader(escritaBancoDeDadosReader)
                .processor(escritaBancoDeDadosProcessor)
                .writer(escritaBancoDeDadosWriter)
                .build();
    }
}
