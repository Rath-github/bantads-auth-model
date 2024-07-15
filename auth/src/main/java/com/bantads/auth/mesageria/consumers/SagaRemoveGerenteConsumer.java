package com.bantads.auth.mesageria.consumers;

import com.bantads.auth.services.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SagaRemoveGerenteConsumer {
    @Autowired
    private UserService userService;

    @RabbitListener(queues = "${spring.rabbitmq.queue.removeGerente}")
    public void listen(@Payload String id) {
        try {
            userService.removeGerente(id);
        }catch (Exception e){
            System.out.println("Erro ao remover gerente! ");
        }
    }
}
