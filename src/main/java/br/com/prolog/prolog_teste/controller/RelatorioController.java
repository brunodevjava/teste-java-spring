package br.com.prolog.prolog_teste.controller;

import br.com.prolog.prolog_teste.dto.relatorio.RelatorioDTO;
import br.com.prolog.prolog_teste.services.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping
    public RelatorioDTO gerarRelatorio(
            @RequestParam String cpf,
            @RequestParam String inicio,
            @RequestParam String fim) {
        return relatorioService.gerarRelatorio(cpf, inicio, fim);
    }
}
