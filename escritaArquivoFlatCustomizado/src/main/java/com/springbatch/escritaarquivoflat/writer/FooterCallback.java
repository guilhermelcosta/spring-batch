package com.springbatch.escritaarquivoflat.writer;

import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.Locale;

@Component
public class FooterCallback implements FlatFileFooterCallback {

    private Double totalGeral = 0.0;

    @Override
    public void writeFooter(Writer writer) throws IOException {
        writer.append("\n-------------------------------------------------------------------");
        writer.append(String.format("\nTOTAL GERAL: %s", NumberFormat.getCurrencyInstance(Locale.CANADA).format(totalGeral)));
    }

//    Esse listener esta tendo incompatibilidade com o chunk
//    @BeforeWrite
//    public void beforeWrite(List<GrupoLancamento> grupoLancamento) {
//        for (GrupoLancamento gl : grupoLancamento) {
//            totalGeral += gl.getTotal();
//        }
//    }
}
