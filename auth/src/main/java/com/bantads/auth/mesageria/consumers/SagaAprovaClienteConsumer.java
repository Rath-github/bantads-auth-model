package com.bantads.auth.mesageria.consumers;

import com.bantads.auth.dtos.DadosAuthDto;
import com.bantads.auth.dtos.DadosNovoClienteDto;
import com.bantads.auth.dtos.UserResponseDto;
import com.bantads.auth.services.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SagaAprovaClienteConsumer {
    @Autowired
    private UserService userService;

    @RabbitListener(queues = "${spring.rabbitmq.aprovacliente}")
    public void listen(@Payload DadosNovoClienteDto novoCliente) {
        try {
            UserResponseDto userCriado = userService.novoCliente(novoCliente);
            System.out.println("Usuario criado!  " + userCriado.username());
        }catch (Exception e){
            System.out.println("Erro ao criar usuario! " + novoCliente.username());
        }
    }
}
