package com.mavenproject.erp.controller;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped // se inicia somente uma instância do escopo durante toda a aplicação
public class GestaoEmpresasBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
        
    private static Integer NUMERO = 0;

    public GestaoEmpresasBean(){
        NUMERO++; //sempre passa por aqui ao instanciar essa classe
    }

    public Integer getNumero() {
        return NUMERO;
    }
    
    
    
}
