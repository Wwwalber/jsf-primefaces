package com.mavenproject.erp.controller;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped // se inicia quando o usuário entrar na página. Ou seja, mesmo enviando chamadas post não há mais iteração
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
