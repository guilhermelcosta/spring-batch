package com.springbatch.processarscriptjob.writer;

import com.springbatch.processarscriptjob.dominio.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessarScriptWriterConfig {

    @Bean
    public ItemWriter<Cliente> processarScriptWriter() {
        return clientes -> clientes.forEach(System.out::println);
    }
}
