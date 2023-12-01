package com.springbatch.escritaarquivoflat.processor;

import com.springbatch.escritaarquivoflat.entities.GrupoLancamento;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EscritaArquivoFlatCustomizadoProcessorConfig {

    @Bean
    public ItemProcessor<GrupoLancamento, GrupoLancamento> escritaArquivoFlatCustomizadoProcessor() {
        return item -> item;
    }
}
