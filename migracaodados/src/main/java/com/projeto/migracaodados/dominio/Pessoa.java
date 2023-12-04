package com.projeto.migracaodados.dominio;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import java.util.Date;

@Getter
@Setter
public class Pessoa {

    private Integer id;
    private Integer idade;
    private String nome;
    private String email;
    private Date dataNascimento;

    public boolean isValida() {
        return !Strings.isBlank(nome) &&  !Strings.isBlank(email);
    }
}
