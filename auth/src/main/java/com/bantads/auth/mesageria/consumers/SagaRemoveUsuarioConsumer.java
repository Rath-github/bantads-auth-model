package com.bantads.auth.mesageria.consumers;

import com.bantads.auth.dtos.DadosEditDto;
import com.bantads.auth.models.User;
import com.bantads.auth.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SagaRemoveUsuarioConsumer {
    @Autowired
    private UserService userService;

    @RabbitListener(queues = "${spring.rabbitmq.removeUsuario}")
    public void listen(@Payload DadosEditDto user) {
        try {
            userService.removeUsuario(user);
        }catch (Exception e){
            System.out.println("Erro ao remover usuario! ");
        }
    }
}
