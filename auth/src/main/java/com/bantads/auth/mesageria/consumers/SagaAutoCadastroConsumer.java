package com.bantads.auth.mesageria.consumers;

import com.bantads.auth.dtos.DadosAuthDto;
import com.bantads.auth.dtos.UserResponseDto;
import com.bantads.auth.services.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SagaAutoCadastroConsumer {
    @Autowired
    private UserService userService;

    @RabbitListener(queues = "${spring.rabbitmq.queue.autocadastro}")
    public void listen(@Payload DadosAuthDto novoCliente) {
        try {
            UserResponseDto userCriado = userService.create(novoCliente);
            System.out.println("Usuario criado!  " + userCriado);
        }catch (Exception e){
            System.out.println("Erro ao criar usuario! " + novoCliente);
        }
    }
}
