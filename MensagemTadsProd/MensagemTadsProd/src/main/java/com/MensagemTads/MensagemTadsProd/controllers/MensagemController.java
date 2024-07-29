package com.MensagemTads.MensagemTadsProd.controllers;

import com.MensagemTads.MensagemTadsProd.model.DadosAuthDto;
import com.MensagemTads.MensagemTadsProd.model.DadosEditDto;
import com.MensagemTads.MensagemTadsProd.model.DadosNovoClienteDto;
import com.MensagemTads.MensagemTadsProd.produtor.MensagemProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MensagemController {
    @Autowired
    private MensagemProducer mensagemProducer;

    @PostMapping("/aprovacliente")
    public void autocadastro(@RequestBody DadosNovoClienteDto dadosAuthDto) {
        mensagemProducer.aprovacliente(dadosAuthDto);
    }
    @PostMapping("/novogerente")
    public void novoGerente(@RequestBody DadosAuthDto dadosAuthDto) {
        mensagemProducer.novoGerente(dadosAuthDto);
    }
    @PostMapping("/novoadmin")
    public void novoAdmin(@RequestBody DadosAuthDto dadosAuthDto) {
        mensagemProducer.novoAdmin(dadosAuthDto);
    }
    @PostMapping("/atualizausuario")
    public void atualizaUsuario(@RequestBody DadosEditDto dadosAuthDto) {
        mensagemProducer.atualizaUsuario(dadosAuthDto);
    }
    @PostMapping("/deletausuario")
    public void removeUsuario(@RequestBody DadosEditDto user) {
        mensagemProducer.removeUsuario(user);
    }
}
