package com.springbatch.escritabancodedados.dominio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {
    private String email;
    private String nome;
    private Integer idade;
    private Double faixaSalarial;
}
