package com.springbatch.escritaarquivoflat.processor;

import com.springbatch.escritaarquivoflat.entities.Cliente;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EscritaArquivoFlatProcessorConfig {

    /*
     * Essa classe seria criada caso eu fosse fazer um processamento específico com os dados,
     * como eu não quero fazer nada nesse exemplo, apenas retorno os próprios items processados.
     * Se eu quiser filtrar items, por exemplo, posso dar um 'return items -> null'
     */

    @Bean
    public ItemProcessor<Cliente, Cliente> escritaArquivoFlatProcessor() {
        return items -> items;
    }
}
