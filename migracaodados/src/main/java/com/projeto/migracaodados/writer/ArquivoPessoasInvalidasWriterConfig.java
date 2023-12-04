package com.projeto.migracaodados.writer;

import com.projeto.migracaodados.dominio.Pessoa;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ArquivoPessoasInvalidasWriterConfig {

    @Bean
    public FlatFileItemWriter<Pessoa> arquivoPessoasInvalidasWriter() {
        return new FlatFileItemWriterBuilder<Pessoa>()
                .name("arquivoPessoasInvalidasWriter")
                .resource(new FileSystemResource("files/pessoas_invalidas.csv"))
                .headerCallback(headerCallback())
                .delimited()
                .names("nome", "email", "dataNascimento", "idade", "id")
                .build();
    }

    private FlatFileHeaderCallback headerCallback() {
        return writer -> writer.append("-- nome, email, dataNascimento, idade, id");
    }
}
