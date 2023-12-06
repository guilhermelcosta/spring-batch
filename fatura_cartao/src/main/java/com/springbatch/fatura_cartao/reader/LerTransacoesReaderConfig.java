package com.springbatch.fatura_cartao.reader;

import com.springbatch.fatura_cartao.dominio.CartaoCredito;
import com.springbatch.fatura_cartao.dominio.Cliente;
import com.springbatch.fatura_cartao.dominio.Transacao;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.Date;

@Configuration
public class LerTransacoesReaderConfig {

    @Bean
    public JdbcCursorItemReader<Transacao> lerTransacoesReader(
            @Qualifier("appDataSource") DataSource dataSource
    ) {
        return new JdbcCursorItemReaderBuilder<Transacao>()
                .name("faturaCartaoReader")
                .dataSource(dataSource)
                .sql("SELECT * FROM fatura_cartao_credito.transacao JOIN cartao_credito USING (numero_cartao_credito) ORDER BY numero_cartao_credito")
                .rowMapper(rowMapperTransacao())
                .build();
    }

    private RowMapper<Transacao> rowMapperTransacao() {
        return (rs, rowNum) -> {
            CartaoCredito cartaoCredito = new CartaoCredito();
            cartaoCredito.setNumeroCartaoCredito(rs.getInt("numero_cartao_credito"));

            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("cliente"));

            Transacao transacao = new Transacao();
            transacao.setId(rs.getInt("id"));
            transacao.setCartaoCredito(cartaoCredito);
            transacao.setDescricao(rs.getString("descricao"));
            transacao.setValor(rs.getDouble("valor"));
            transacao.setData(new Date(rs.getDate("data").getTime()));
            return transacao;
        };
    }
}
