package com.springbatch.leitorarquivo.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoLeituraMultiplosWriterConfig {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public ItemWriter arquivoLeituraFixaWriter() {
        return items -> items.forEach(System.out::println);
    }
}
