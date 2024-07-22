package com.bantads.auth.mesageria.consumers;

import com.bantads.auth.dtos.DadosAuthDto;
import com.bantads.auth.dtos.UserResponseDto;
import com.bantads.auth.services.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
@Component
public class SagaNovoGerenteConsumer {
    @Autowired
    private UserService userService;

    @RabbitListener(queues = "${spring.rabbitmq.novoGerente}")
    public void listen(@Payload DadosAuthDto novoGerente) {
        try {
            UserResponseDto userCriado = userService.create(novoGerente);
            System.out.println("Gerente criado!  " + userCriado);
        }catch (Exception e){
            System.out.println("Erro ao criar gerente! " + novoGerente);
        }
    }
}
