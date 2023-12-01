package com.springbatch.processadorclassifierjob.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessadorClassifierWriterConfig {

    @Bean
    @SuppressWarnings({"unchecked", "rawtypes"})
    public ItemWriter processadorClassifierWriter() {
        return itens -> itens.forEach(System.out::println);
    }
}
