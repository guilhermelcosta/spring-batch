package com.springbatch.escritaarquivoflat.reader;

import com.springbatch.escritaarquivoflat.entities.GrupoLancamento;
import com.springbatch.escritaarquivoflat.entities.Lancamento;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

@Configuration
public class BancoLancamentoReaderConfig {

    @Bean
    public JdbcCursorItemReader<GrupoLancamento> bancoLancamentoReader(
            @Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<GrupoLancamento>()
                .name("bancoLancamentoReader")
                .dataSource(dataSource)
                .sql("select * from lancamento")
                .rowMapper(rowMapper())
                .build();
    }

    private RowMapper<GrupoLancamento> rowMapper() {
        return (rs, rowNum) -> {
            GrupoLancamento grupo = new GrupoLancamento();
            grupo.setCodigoNaturezaDespesa(rs.getInt("codigoNaturezaDespesa"));
            grupo.setDescricaoNaturezaDespesa(rs.getString("descricaoNaturezaDespesa"));
            grupo.setLancamentoTmp(new Lancamento());
            grupo.getLancamentoTmp().setData(rs.getDate("dataLancamento"));
            grupo.getLancamentoTmp().setDescricao(rs.getString("descricaoLancamento"));
            grupo.getLancamentoTmp().setValor(rs.getDouble("valorLancamento"));
            return grupo;
        };
    }
}
