package com.springbatch.escritaarquivoflat.writer;

import com.springbatch.escritaarquivoflat.entities.GrupoLancamento;
import com.springbatch.escritaarquivoflat.entities.Lancamento;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.WritableResource;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

@Configuration
public class EscritaArquivoFlatCustomizadoWriterConfig {

    @Bean
    @StepScope
    public FlatFileItemWriter<GrupoLancamento> escritaArquivoFlatCustomizadoWriter(
            @Value("#{jobParameters['demonstrativoOrcamentario']}") WritableResource demonstrativoOrcamentario,
            FooterCallback footerCallback
    ) {
        return new FlatFileItemWriterBuilder<GrupoLancamento>()
                .name("escritaArquivoFlatCustomizadoWriter")
                .resource(demonstrativoOrcamentario)
                .lineAggregator(lineAggregator())
                .headerCallback(headerCallback())
                .footerCallback(footerCallback)
                .build();
    }

    private FlatFileHeaderCallback headerCallback() {
        return writer -> {
            writer.append(String.format("SISTEMA INTEGRADO: GUILHERME \t\t\t DATA: %s", LocalDate.now()));
            writer.append(String.format("\nHORA DE IMPRESSÃO: %s:%s \t\t\t\t LOCAL: BELO HORIZONTE", LocalDateTime.now().getHour(), LocalDateTime.now().getMinute()));
            writer.append("\n-------------------------------------------------------------------");
            writer.append("\n[CODIGO] NOME - VALOR");
            writer.append("\n\t [Data] Descrição - Valor");
            writer.append("\n-------------------------------------------------------------------");

        };
    }

    private LineAggregator<GrupoLancamento> lineAggregator() {
        return grupoLancamento -> {
            String formatGrupoLancamento = String.format("\n [%s] %s - %s",
                    grupoLancamento.getCodigoNaturezaDespesa(),
                    grupoLancamento.getDescricaoNaturezaDespesa(),
                    NumberFormat.getCurrencyInstance(Locale.CANADA).format(grupoLancamento.getTotal()));
            StringBuilder stringBuilder = new StringBuilder();
            for (Lancamento lancamento : grupoLancamento.getLancamentos()) {
                stringBuilder.append(String.format("\n\t [%s] %s - %s",
                        new SimpleDateFormat("dd/MM/yyyy").format(lancamento.getData()),
                        lancamento.getDescricao(),
                        NumberFormat.getCurrencyInstance(Locale.CANADA).format(lancamento.getValor())));
            }
            String formatLancamento = stringBuilder.toString();
            return formatGrupoLancamento + formatLancamento;
        };
    }
}
