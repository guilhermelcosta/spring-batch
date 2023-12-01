package com.springbatch.escritabancodedados.writer;

import com.springbatch.escritabancodedados.dominio.Conta;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ClienteInvalidoWriterConfig {

    @Bean
    public FlatFileItemWriter<Conta> clienteInvalidoWriter() {
        return new FlatFileItemWriterBuilder<Conta>()
                .name("escritaArquivosWriter")
                .resource(new FileSystemResource("./files/clientesInvalidas.txt"))
                .delimited()
                .names("clienteId")
                .build();
    }
}
