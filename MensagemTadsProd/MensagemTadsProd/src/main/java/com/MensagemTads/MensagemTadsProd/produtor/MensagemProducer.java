package com.MensagemTads.MensagemTadsProd.produtor;

import com.MensagemTads.MensagemTadsProd.model.DadosAuthDto;
import com.MensagemTads.MensagemTadsProd.model.DadosEditDto;
import com.MensagemTads.MensagemTadsProd.model.DadosNovoClienteDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MensagemProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.aprovacliente}")
    public String aprovacliente;
    @Value("${spring.rabbitmq.novoGerente}")
    public String novogerente;
    @Value("${spring.rabbitmq.novoAdmin}")
    public String novoadmin;
    @Value("${spring.rabbitmq.atualizaUsuario}")
    public String atualizauser;
    @Value("${spring.rabbitmq.removeUsuario}")
    public String removeuser;

    @Autowired
    public MensagemProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void aprovacliente(DadosNovoClienteDto dadosAuthDto){
        rabbitTemplate.convertAndSend(aprovacliente, dadosAuthDto);
        System.out.println("Cadastro de Cliente efetuado: ");
    }
    public void novoGerente(DadosAuthDto dadosAuthDto){
        rabbitTemplate.convertAndSend(novogerente, dadosAuthDto);
        System.out.println("Mensagem enviada autoca: " + dadosAuthDto);
    }
    public void novoAdmin(DadosAuthDto dadosAuthDto){
        rabbitTemplate.convertAndSend(novoadmin, dadosAuthDto);
        System.out.println("Mensagem enviada autoca: " + dadosAuthDto);
    }
    public void atualizaUsuario(DadosEditDto dadosAuthDto){
        rabbitTemplate.convertAndSend(atualizauser, dadosAuthDto);
        System.out.println("atualiza u: " + dadosAuthDto);
    }
    public void removeUsuario(DadosEditDto user){
        rabbitTemplate.convertAndSend(removeuser, user);
        System.out.println("Usuario removido: ");
    }
}