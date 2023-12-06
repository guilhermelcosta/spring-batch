package com.springbatch.fatura_cartao.writer;

import com.springbatch.fatura_cartao.dominio.FaturaCartaoCredito;
import com.springbatch.fatura_cartao.dominio.Transacao;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.batch.item.file.ResourceSuffixCreator;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ArquivoFaturaCartaoCreditoWriterConfig {

    @Bean
    public MultiResourceItemWriter<FaturaCartaoCredito> arquivoFaturaCartaoCreditoWriter() {
        return new MultiResourceItemWriterBuilder<FaturaCartaoCredito>()
                .name("arquivoFaturaCartaoCreditoWriter")
                .resource(new FileSystemResource("files/fatura"))
                .itemCountLimitPerResource(1)
                .resourceSuffixCreator(suffixCreator())
                .delegate(arquivoFaturaCartaoCredito())
                .build();
    }

    private FlatFileItemWriter<FaturaCartaoCredito> arquivoFaturaCartaoCredito() {
        return new FlatFileItemWriterBuilder<FaturaCartaoCredito>()
                .name("arquivoFaturaCartaoCredito")
                .resource(new FileSystemResource("files/fatura.txt"))
                .lineAggregator(lineAggregator())
                .footerCallback(footerCallback())
                .build();
    }

    @Bean
    public FlatFileFooterCallback footerCallback() {
        return new TotalTransacoesFooterCallback();
    }

    //    todo: ajustar formato
    private LineAggregator<FaturaCartaoCredito> lineAggregator() {
        return faturaCartaoCredito -> {
            StringBuilder writer = new StringBuilder();
            writer.append("------------------------");
            for (Transacao transacao : faturaCartaoCredito.getTransacoes())
                writer.append(transacao);
            return writer.toString();
        };
    }

    private ResourceSuffixCreator suffixCreator() {
        return index -> index + ".txt";
    }
}
