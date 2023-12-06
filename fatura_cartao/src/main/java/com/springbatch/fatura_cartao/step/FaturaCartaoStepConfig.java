package com.springbatch.fatura_cartao.step;

import com.springbatch.fatura_cartao.dominio.FaturaCartaoCredito;
import com.springbatch.fatura_cartao.dominio.Transacao;
import com.springbatch.fatura_cartao.reader.FaturaCartaoCreditoReader;
import com.springbatch.fatura_cartao.writer.TotalTransacoesFooterCallback;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class FaturaCartaoStepConfig {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    @Bean
    public Step faturaCartaoStep(
            ItemStreamReader<Transacao> lerTransacoesReader,
            ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito> faturaCartaoProcessor,
            ItemWriter<FaturaCartaoCredito> faturaCartaoWriter,
            TotalTransacoesFooterCallback listener
    ) {
        return new StepBuilder("faturaCartaoStep", jobRepository)
                .<FaturaCartaoCredito, FaturaCartaoCredito>chunk(1, platformTransactionManager)
                .reader(new FaturaCartaoCreditoReader(lerTransacoesReader))
                .processor(faturaCartaoProcessor)
                .writer(faturaCartaoWriter)
                .listener(listener)
                .build();
    }
}
