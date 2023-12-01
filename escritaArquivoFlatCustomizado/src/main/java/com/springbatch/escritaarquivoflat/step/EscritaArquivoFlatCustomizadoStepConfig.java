package com.springbatch.escritaarquivoflat.step;

import com.springbatch.escritaarquivoflat.entities.GrupoLancamento;
import com.springbatch.escritaarquivoflat.reader.GrupoLancamentoReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class EscritaArquivoFlatCustomizadoStepConfig {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    @Bean
    public Step escritaArquivoFlatCustomizadoStep(
            GrupoLancamentoReader escritaArquivoFlatCustomizadoReader,
            ItemProcessor<GrupoLancamento, GrupoLancamento> escritaArquivoFlatCustomizadoProcessor,
            ItemWriter<GrupoLancamento> escritaArquivoFlatCustomizadoWriter
//            Ocultado por conta dos problemas com o chunk
//            FooterCallback footerCallback
    ) {
        return new StepBuilder("escritaArquivoFlatCustomizadoStep", jobRepository)
                .<GrupoLancamento, GrupoLancamento>chunk(100, platformTransactionManager)
                .reader(escritaArquivoFlatCustomizadoReader)
                .processor(escritaArquivoFlatCustomizadoProcessor)
                .writer(escritaArquivoFlatCustomizadoWriter)
//                Esse listener esta tendo incompatibilidade com o chunk
//                .listener(footerCallback)
                .build();
    }
}
