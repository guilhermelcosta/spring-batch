package com.projeto.migracaodados.writer;

import com.projeto.migracaodados.dominio.DadosBancarios;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ArquivoDadosBancariosInvalidosWriterConfig {

    @Bean
    public FlatFileItemWriter<DadosBancarios> arquivoDadosBancariosInvalidosWriter() {
        return new FlatFileItemWriterBuilder<DadosBancarios>()
                .name("arquivoDadosBancariosInvalidosWriter")
                .resource(new FileSystemResource("files/dados_bancarios_invalidos.csv"))
                .headerCallback(headerCallback())
                .delimited()
                .names("pessoaId", "agencia", "conta", "banco", "id")
                .build();
    }

    private FlatFileHeaderCallback headerCallback() {
        return writer -> writer.append("-- pessoaId, agencia, conta, banco, id");
    }
}
