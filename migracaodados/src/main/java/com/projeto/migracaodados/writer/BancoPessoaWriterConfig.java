package com.projeto.migracaodados.writer;

import com.projeto.migracaodados.dominio.Pessoa;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Date;

@Configuration
public class BancoPessoaWriterConfig {

//    Eu estou usando o itemPreparedStatementSetter, em vez do beanMapped por conta da classe Pessoa
//    ser um objeto mais complexo, por conta do atributo Date (dataNascimento). No caso dos DadosBancarios,
//    em que todos os atributos são simples, posso usar o beanMapped direto

    @Bean
    public JdbcBatchItemWriter<Pessoa> bancoPessoaWriter(
            @Qualifier("appDataSource") DataSource dataSource
    ) {
        return new JdbcBatchItemWriterBuilder<Pessoa>()
                .dataSource(dataSource)
                .sql("INSERT INTO migracao_dados.pessoa (id, nome, email, data_nascimento, idade) VALUES (?, ?, ?, ?, ?)")
                .itemPreparedStatementSetter(itemPreparedStatementSetter())
                .build();
    }

    private ItemPreparedStatementSetter<Pessoa> itemPreparedStatementSetter() {
        return (pessoa, ps) -> {
            ps.setInt(1, pessoa.getId());
            ps.setString(2, pessoa.getNome());
            ps.setString(3, pessoa.getEmail());
            ps.setDate(4, new Date(pessoa.getDataNascimento().getTime()));
            ps.setInt(5, pessoa.getIdade());
        };
    }
}
