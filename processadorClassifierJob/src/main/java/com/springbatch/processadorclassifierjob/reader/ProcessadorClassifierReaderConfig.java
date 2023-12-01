package com.springbatch.processadorclassifierjob.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ProcessadorClassifierReaderConfig {

//    Se quiser obter o nome do metodo que esta sendo utilizado atualmente
//    utilizar a funcao:
//    String nameofCurrMethod = new Throwable()
//                                 .getStackTrace()[0]
//                                 .getMethodName();

    @Bean
    @StepScope
    @SuppressWarnings({"unchecked", "rawtypes"})
    public FlatFileItemReader processadorClassifierReader(
            @Value("#{jobParameters['arquivoClientes']}") Resource resource,
            LineMapper lineMapper) {
        return new FlatFileItemReaderBuilder()
                .name("processadorClassifierReader")
                .resource(resource)
                .lineMapper(lineMapper)
                .build();
    }

}
