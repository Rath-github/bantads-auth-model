package com.bantads.auth.mesageria.consumers;

import com.bantads.auth.services.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SagaRemoveUsuarioConsumer {
    @Autowired
    private UserService userService;

    @RabbitListener(queues = "${spring.rabbitmq.queue.removeUsuario}")
    public void listen(@Payload String id) {
        try {
            userService.removeUsuario(id);
        }catch (Exception e){
            System.out.println("Erro ao remover usuario! ");
        }
    }
}
