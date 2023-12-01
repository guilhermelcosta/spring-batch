package com.springbatch.leitorarquivo.writer;

import com.springbatch.leitorarquivo.dominio.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoLeituraFixaWriterConfig {

    @Bean
    public ItemWriter<Cliente> arquivoLeituraFixaWriter() {
        return items -> items.forEach(System.out::println);

//        return items -> {
//            for (Cliente cliente : items) {
//                if (cliente.getNome().equals("Maria"))
//                    throw new Exception();
//                else
//                    System.out.println(cliente);
//            }
//        };
    }
}
