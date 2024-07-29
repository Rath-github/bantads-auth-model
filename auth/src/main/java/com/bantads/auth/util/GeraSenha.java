package com.bantads.auth.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class GeraSenha {

    private static final String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generatePassword() {
        int tamanho = 8;
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(tamanho);

        for (int i = 0; i < tamanho; i++) {
            int randomIndex = random.nextInt(caracteres.length());
            password.append(caracteres.charAt(randomIndex));
        }
        return password.toString();
    }
}
