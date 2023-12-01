package com.springbatch.escritaarquivoflat.step;

import com.springbatch.escritaarquivoflat.entities.GrupoLancamento;
import com.springbatch.escritaarquivoflat.reader.GrupoLancamentoReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.MultiResourceItemWriter;
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
//            Esse aqui trabalha com a escrita em um único arquivo
//            ItemWriter<GrupoLancamento> escritaArquivoFlatCustomizadoWriter
//            Esse trabalha com escrita em múltiplos arquivos
            MultiResourceItemWriter<GrupoLancamento> escritaArquivoFlatCustomizadoWriter
    ) {
        return new StepBuilder("escritaArquivoFlatCustomizadoStep", jobRepository)
//                Chunk reduzido para que o limite de itens por arquivo seja validado
//                Ver "EscritaArquivoFlatCustomizadoWriterConfig" para referência
                .<GrupoLancamento, GrupoLancamento>chunk(1, platformTransactionManager)
                .reader(escritaArquivoFlatCustomizadoReader)
                .processor(escritaArquivoFlatCustomizadoProcessor)
                .writer(escritaArquivoFlatCustomizadoWriter)
                .build();
    }
}
