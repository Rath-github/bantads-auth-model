package com.bantads.auth.mesageria.consumers;

import com.bantads.auth.dtos.DadosAuthDto;
import com.bantads.auth.dtos.UserResponseDto;
import com.bantads.auth.services.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SagaRemoveClienteConsumer {
    @Autowired
    private UserService userService;

    @RabbitListener(queues = "${spring.rabbitmq.queue.removeCliente}")
    public void listen(@Payload String id) {
        try {
            userService.removeCliente(id);
        }catch (Exception e){
            System.out.println("Erro ao remover cliente! ");
        }
    }
}
