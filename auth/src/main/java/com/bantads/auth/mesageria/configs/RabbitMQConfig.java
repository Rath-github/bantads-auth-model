package com.bantads.auth.mesageria.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.aprovacliente}")
    public String aprovacliente;
    @Value("${spring.rabbitmq.novoGerente}")
    public String novogerente;
    @Value("${spring.rabbitmq.novoAdmin}")
    public String novoadmin;
    @Value("${spring.rabbitmq.atualizaUsuario}")
    public String atualizausuario;
    @Value("${spring.rabbitmq.removeUsuario}")
    public String removeusuario;

    @Bean
    public Queue autocadastro(){
        return new Queue(aprovacliente , true);
    }

    @Bean
    public Queue novoGerente(){
        return new Queue(novogerente , true);
    }

    @Bean
    public Queue novoAdmin(){
        return new Queue(novoadmin , true);
    }

    @Bean
    public Queue atualizaUsuario(){
        return new Queue(atualizausuario , true);
    }

    @Bean
    public Queue removeUsuario(){
        return new Queue(removeusuario , true);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
