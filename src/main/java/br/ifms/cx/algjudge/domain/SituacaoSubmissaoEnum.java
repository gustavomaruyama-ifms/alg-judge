/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.domain;

/**
 *
 * @author Gustavo
 */
public enum SituacaoSubmissaoEnum {

    ACEITO("Aceito"), 
    RESPOSTA_ERRADA("Resposta Errada"), 
    ERRO_DE_SINTAX ("Erro de Sintax"), 
    NOVA_SITUACAO ("Nova Situacao");
    
    private String valor;

    private SituacaoSubmissaoEnum(String valor) {
        this.valor = valor;
    }
}