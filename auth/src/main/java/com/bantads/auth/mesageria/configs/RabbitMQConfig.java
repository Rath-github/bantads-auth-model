package com.bantads.auth.mesageria.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.queue.autocadastro}")
    public String filaAutocadastro;
    @Value("${spring.rabbitmq.queue.novoGerente}")
    public String filaNovoGerente;
    @Value("${spring.rabbitmq.queue.novoAdmin}")
    public String filaNovoAdmin;
    @Value("${spring.rabbitmq.queue.removeCliente}")
    public String filaRemoveCliente;
    @Value("${spring.rabbitmq.queue.removeGerente}")
    public String filaRemoveGerente;


    @Bean
    public Queue autocadastro(){
        return new Queue(filaAutocadastro , true);
    }

    @Bean
    public Queue novoAdmin(){
        return new Queue(filaNovoAdmin , true);
    }

    @Bean
    public Queue novoGerente(){
        return new Queue(filaNovoGerente , true);
    }

    @Bean
    public Queue removeCliente(){
        return new Queue(filaRemoveCliente , true);
    }

    @Bean
    public Queue removeGerente(){
        return new Queue(filaRemoveGerente , true);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
