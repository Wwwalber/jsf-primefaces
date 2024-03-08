package com.mavenproject.erp.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.mavenproject.erp.model.Empresa;
import com.mavenproject.erp.model.TipoEmpresa;

@Named
@ViewScoped // se inicia quando o usuário entrar na página. Ou seja, mesmo enviando chamadas post não há mais iteração
public class GestaoEmpresasBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
        
    private Empresa empresa = new Empresa();

    public Empresa getEmpresa() {
        return empresa;
    }
    
    public TipoEmpresa[] getTiposEmpresa(){
        return TipoEmpresa.values();// busca no Enum
    }
    public void salvar(){
        System.out.println("Razão Social: " + empresa.getRazaoSocial()
        + " - Nome Fantasia: "+ empresa.getNomeFantasia()
        + " - Tipo: "+ empresa.getTipo());
    }
    
    public String ajuda(){
        return "AjudaGestaoEmpresas?faces-redirect=true";
    }
}
