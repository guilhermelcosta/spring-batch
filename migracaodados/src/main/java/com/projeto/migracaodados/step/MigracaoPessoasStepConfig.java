package com.projeto.migracaodados.step;

import com.projeto.migracaodados.dominio.Pessoa;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MigracaoPessoasStepConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Bean
    public Step migracaoPessoasStep(
            ItemReader<Pessoa> arquivoPessoaReader,
            ClassifierCompositeItemWriter<Pessoa> pessoasClassifierWriter,
            FlatFileItemWriter<Pessoa> arquivoPessoasInvalidasWriter
    ) {
        return new StepBuilder("migracaoPessoasStep", jobRepository)
//                O tamanho do chunk impacta diretamente na performance do batch. Definindo para 10.000,
//                o aumento de performance foi próximo a 50%, em relação a chunk(1)
                .<Pessoa, Pessoa>chunk(10000, platformTransactionManager)
                .reader(arquivoPessoaReader)
                .writer(pessoasClassifierWriter)
                .stream(arquivoPessoasInvalidasWriter)
                .build();
    }
}
