package com.bantads.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public String enviarCredencialEmail(String destinatario, String assunto, String mensagem){
        try {
            System.out.println("email entrou" + mensagem);

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(mensagem);

            javaMailSender.send(simpleMailMessage);

            System.out.println("email enviado" + destinatario);
            return "Email enviado com sucesso!";
        }catch (Exception e){
            return  "Erro ao enviar email com credenciais " + e.getLocalizedMessage();
        }
    }
}
