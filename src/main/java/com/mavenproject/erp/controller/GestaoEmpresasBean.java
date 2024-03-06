package com.mavenproject.erp.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped // instância criada/encerrada com a requisição
public class GestaoEmpresasBean {
    
    private static Integer NUMERO = 0;

    public GestaoEmpresasBean(){
        NUMERO++; //sempre passa por aqui ao instanciar essa classe
    }

    public Integer getNumero() {
        return NUMERO;
    }
    
    
    
}
