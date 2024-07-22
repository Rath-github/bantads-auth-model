package com.bantads.auth.mesageria.consumers;

import com.bantads.auth.dtos.DadosAuthDto;
import com.bantads.auth.dtos.UserResponseDto;
import com.bantads.auth.services.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SagaNovoAdminConsumer {
    @Autowired
    private UserService userService;

    @RabbitListener(queues = "${spring.rabbitmq.novoAdmin}")
    public void listen(@Payload DadosAuthDto novoAdmin) {
        try {
            UserResponseDto userCriado = userService.create(novoAdmin);
            System.out.println("Administrador criado!  " + userCriado);
        }catch (Exception e){
            System.out.println("Erro ao criar Administrador! " + novoAdmin);
        }
    }
}
