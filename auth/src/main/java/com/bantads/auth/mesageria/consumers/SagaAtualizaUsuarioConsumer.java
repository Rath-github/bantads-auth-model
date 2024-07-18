package com.bantads.auth.mesageria.consumers;

import com.bantads.auth.dtos.DadosAuthDto;
import com.bantads.auth.services.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SagaAtualizaUsuarioConsumer {
    @Autowired
    private UserService userService;

    @RabbitListener(queues = "${spring.rabbitmq.queue.atualizaUsuario}")
    public void listen(@Payload String id, DadosAuthDto user) {
        try {
            userService.atualizarUsuario(id, user);
        }catch (Exception e){
            System.out.println("Erro ao atualizar usuario! ");
        }
    }
}
