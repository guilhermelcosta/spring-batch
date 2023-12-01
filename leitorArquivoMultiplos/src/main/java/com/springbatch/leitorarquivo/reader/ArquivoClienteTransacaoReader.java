package com.springbatch.leitorarquivo.reader;

import com.springbatch.leitorarquivo.dominio.Cliente;
import com.springbatch.leitorarquivo.dominio.Transacao;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

public class ArquivoClienteTransacaoReader implements ItemStreamReader<Cliente>, ResourceAwareItemReaderItemStream<Cliente> {

    private final FlatFileItemReader<Object> delegate;
    private Object objetoAtual;

    public ArquivoClienteTransacaoReader(FlatFileItemReader<Object> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void open(ExecutionContext executionContext) {
        delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) {
        delegate.update(executionContext);
    }

    @Override
    public void close() {
        delegate.close();
    }

    @Override
    public Cliente read() throws Exception {

        if (objetoAtual == null)
            objetoAtual = delegate.read();

        Cliente cliente = (Cliente) objetoAtual;
        objetoAtual = null;

        if (cliente != null)
            while (peek() instanceof Transacao)
                cliente.getTransacoes().add((Transacao) objetoAtual);


        return cliente;
    }

    private Object peek() throws Exception {

        objetoAtual = delegate.read();
        return objetoAtual;
    }

    @Override
    public void setResource(Resource resource) {
        delegate.setResource(resource);
    }
}
