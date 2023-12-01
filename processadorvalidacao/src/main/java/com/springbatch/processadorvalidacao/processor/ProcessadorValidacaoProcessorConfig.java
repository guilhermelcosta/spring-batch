package com.springbatch.processadorvalidacao.processor;

import com.springbatch.processadorvalidacao.dominio.Cliente;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ProcessadorValidacaoProcessorConfig {

    private final Set<String> emails = new HashSet<>();

    @Bean
    public ItemProcessor<Cliente, Cliente> processadorValidacaoProcessor() throws Exception {
//        Processador composto
        return new CompositeItemProcessorBuilder<Cliente, Cliente>()
                .delegates(beanValidatingItemProcessor(), validatingEmailProcessor())
                .build();
    }

    private BeanValidatingItemProcessor<Cliente> beanValidatingItemProcessor() throws Exception {
        BeanValidatingItemProcessor<Cliente> processor = new BeanValidatingItemProcessor<>();
        processor.setFilter(true);
        processor.afterPropertiesSet();
        return processor;
    }

    private ValidatingItemProcessor<Cliente> validatingEmailProcessor() {
        ValidatingItemProcessor<Cliente> processor = new ValidatingItemProcessor<>();
        processor.setFilter(true);
        processor.setValidator(validator());
        return processor;
    }

    private Validator<Cliente> validator() {
//        Pode ser substituído por expressão lambda
        return new Validator<Cliente>() {

            @Override
            public void validate(Cliente cliente) throws ValidationException {
                if (emails.contains(cliente.getEmail()))
                    throw new ValidationException(String.format("E-mail ja cadastrado: %s", cliente.getEmail()));
                else
                    emails.add(cliente.getEmail());
            }
        };
    }
}
