package com.springbatch.escritaarquivoflat.reader;

import com.springbatch.escritaarquivoflat.entities.GrupoLancamento;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class EscritaArquivoFlatCustomizadoReaderConfig {

    @Bean
    @StepScope
    public MultiResourceItemReader<GrupoLancamento> escritaArquivoFlatCustomizadoReader(
            @Value("#{jobParameters['arquivosLancamento']}")Resource arquivosLancamento,
            GrupoLancamentoReader grupoLancamentoReader
            ) {
        return new MultiResourceItemReaderBuilder<GrupoLancamento>()
                .name("escritaArquivoFlatCustomizadoReader")
                .resources(arquivosLancamento)
                .delegate(grupoLancamentoReader)
                .build();
    }
}
