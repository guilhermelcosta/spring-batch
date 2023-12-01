package com.springbatch.escritabancodedados.writer;

import com.springbatch.escritabancodedados.dominio.Conta;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class EscritaBancoDeDadosWriterConfig {

    @Bean
    public JdbcBatchItemWriter<Conta> escritaBancoDeDadosWriter(
            @Qualifier("appDataSource") DataSource dataSource
    ) {
        return new JdbcBatchItemWriterBuilder<Conta>()
                .dataSource(dataSource)
                .sql("INSERT INTO conta (tipo, limite, cliente_id) VALUES (?, ?, ?)")
                .itemPreparedStatementSetter(itemPreparedStatementSetter())
                .build();
    }

    private ItemPreparedStatementSetter<Conta> itemPreparedStatementSetter() {
        return (conta, ps) -> {
//                setString(1, ...), o 1 indica o índice desse valor no meu SQL.
//                No SQL acima: "INSERT INTO conta (tipo, limite, clienteId) VALUES (?, ?, ?)", o índice 1
//                indica o primeiro ? de VALUES
            ps.setString(1, conta.getTipo().name());
            ps.setDouble(2, conta.getLimite());
            ps.setString(3, conta.getClienteId());
        };
    }
}
