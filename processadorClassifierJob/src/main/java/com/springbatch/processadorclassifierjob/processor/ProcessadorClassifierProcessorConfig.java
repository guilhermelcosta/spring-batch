package com.springbatch.processadorclassifierjob.processor;

import com.springbatch.processadorclassifierjob.dominio.Cliente;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemProcessorBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessadorClassifierProcessorConfig {

    @Bean
    @SuppressWarnings({"rawtypes", "unchecked"})
    public ItemProcessor processadorClassifierProcessor() {
        return new ClassifierCompositeItemProcessorBuilder<>()
                .classifier(classifier())
                .build();
    }

    @SuppressWarnings("rawtypes")
    private Classifier classifier() {
        return (Classifier<Object, ItemProcessor>) obj -> {
            if (obj instanceof Cliente)
                return new ClienteProcessor();
            else
                return new TransacaoProcessor();
        };
    }


}
