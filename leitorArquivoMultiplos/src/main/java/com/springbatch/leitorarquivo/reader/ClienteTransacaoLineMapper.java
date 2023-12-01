package com.springbatch.leitorarquivo.reader;

import com.springbatch.leitorarquivo.dominio.Cliente;
import com.springbatch.leitorarquivo.dominio.Transacao;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ClienteTransacaoLineMapper {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public PatternMatchingCompositeLineMapper lineMapper() {
        PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper<>();
        lineMapper.setTokenizers(tokenizers());
        lineMapper.setFieldSetMappers(fieldSetMapper());
        return lineMapper;
    }

    @SuppressWarnings({"rawtypes"})
    private Map<String, FieldSetMapper> fieldSetMapper() {
        Map<String, FieldSetMapper> fieldSetMapperMappers = new HashMap<>();
        fieldSetMapperMappers.put("0*", fieldSetMapperMapper(Cliente.class));
        fieldSetMapperMappers.put("1*", fieldSetMapperMapper(Transacao.class));
        return fieldSetMapperMappers;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private FieldSetMapper fieldSetMapperMapper(Class classe) {
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(classe);
        return fieldSetMapper;
    }

    private Map<String, LineTokenizer> tokenizers() {
        Map<String, LineTokenizer> tokenizers = new HashMap<>();
        tokenizers.put("0*", clienteLineTokenizer());
        tokenizers.put("1*", transacaoLineTokenizer());
        return tokenizers;
    }

    private LineTokenizer transacaoLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("id", "descricao", "valor");
//        para pular a primeira coluna do .txt, que é 0 ou 1
        lineTokenizer.setIncludedFields(1, 2, 3);
        return lineTokenizer;
    }

    private LineTokenizer clienteLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("nome", "sobrenome", "idade", "email");
//        para pular a primeira coluna do .txt, que é 0 ou 1
        lineTokenizer.setIncludedFields(1, 2, 3, 4);
        return lineTokenizer;
    }
}
