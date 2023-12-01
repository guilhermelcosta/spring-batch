package com.dti.primeiro_projeto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PropsConfig {

    /*
     * Eu poderia também criar uma classe auxiliar de application.properties, de modo a classe de
     * propriedade chame a classe auxiliar, com os dados secretos. Em seguida, adicionar essa classe
     * auxiliar ao git-ignore. O problema disso, é que quando o app subir para produção, ele não terá
     * propriedades suficientes para rodar. Para resolver isso, pode usar a propria configuração
     * da plataforma de hospedagem para que ela tenha essas variáveis de propriedade.
     */

    @Bean
    public PropertySourcesPlaceholderConfigurer config() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new FileSystemResource("src/main/java/com/dti/primeiro_projeto/config/application.properties"));
        return configurer;
    }
}
