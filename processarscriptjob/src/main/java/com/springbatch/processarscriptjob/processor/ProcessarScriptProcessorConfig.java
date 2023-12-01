package com.springbatch.processarscriptjob.processor;

import com.springbatch.processarscriptjob.dominio.Cliente;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ScriptItemProcessorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessarScriptProcessorConfig {

    @Bean
    public ItemProcessor<Cliente, Cliente> processarScriptProcessor() {
        return new ScriptItemProcessorBuilder<Cliente, Cliente>()
                .language("nashorn")
                .scriptSource(
                        "var email = item.getEmail();"
                                + "var arquivoExiste = `ls | grep ${email}.txt`;"
                                + "(!arquivoExiste) item; else null;"
                ).build();
    }
}
