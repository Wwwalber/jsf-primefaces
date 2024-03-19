package com.mavenproject.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mavenproject.erp.model.Empresa;
import com.mavenproject.erp.model.TipoEmpresa;
import com.mavenproject.erp.repository.Empresas;
import com.mavenproject.erp.util.FacesMessages;

@Named
@ViewScoped // se inicia quando o usuário entrar na página. Ou seja, mesmo enviando chamadas post não há mais iteração
public class GestaoEmpresasBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private Empresas empresas;
    
    @Inject
    private FacesMessages messages;
    private List<Empresa> listaEmpresas;

    private String termoPesquisa;

    public void pesquisar(){
        listaEmpresas = empresas.pesquisar(termoPesquisa);
        if (listaEmpresas.isEmpty()) {
            messages.info("Sua consulta não retornou registros.");
        }
    }
    public void todasEmpresas(){
        listaEmpresas = empresas.todas();
    }

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public TipoEmpresa[] getTipoEmpresa(){
        return TipoEmpresa.values();
    }

}
