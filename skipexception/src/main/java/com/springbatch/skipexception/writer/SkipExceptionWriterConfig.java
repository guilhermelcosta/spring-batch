package com.springbatch.skipexception.writer;

import com.springbatch.skipexception.dominio.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SkipExceptionWriterConfig {

    @Bean
    public ItemWriter<Cliente> skipExceptionWriter() {
        return clientes -> clientes.forEach(System.out::println);
    }
}
