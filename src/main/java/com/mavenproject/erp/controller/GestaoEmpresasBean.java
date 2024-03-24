package com.mavenproject.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.mavenproject.erp.model.Empresa;
import com.mavenproject.erp.model.RamoAtividade;
import com.mavenproject.erp.model.TipoEmpresa;
import com.mavenproject.erp.repository.Empresas;
import com.mavenproject.erp.repository.RamoAtividades;
import com.mavenproject.erp.service.CadastroEmpresaService;
import com.mavenproject.erp.util.FacesMessages;

@Named
@ViewScoped // se inicia quando o usuário entrar na página. Ou seja, mesmo enviando chamadas post não há mais iteração
public class GestaoEmpresasBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private Empresas empresas;
    
    @Inject // CDI vai criar a instância e injetar
    private FacesMessages messages;

    private List<Empresa> listaEmpresas;

    @Inject
    private RamoAtividades ramoAtividades;

    @Inject
    private CadastroEmpresaService cadastroEmpresaService;

    private String termoPesquisa;

    private Converter ramoAtividadConverter;

    private Empresa empresa;

    public void prepararNovaEmpresa(){
        empresa = new Empresa();
    }

    public void salvar(){
        cadastroEmpresaService.salvar(empresa);// para salvar basta esse código
        // acréscimo - ver se o usuário já disparou essa pesquisa
        if (jaHouverPesquisa()) {
            pesquisar();     // para atualizar        
        }else{
            todasEmpresas();
        }
        messages.info("Empresa salva com sucuesso!");
        PrimeFaces instance = PrimeFaces.current();
        instance.ajax().update("frm:empresasDataTable", "frm:messages");
        }

    public void pesquisar(){
        listaEmpresas = empresas.pesquisar(termoPesquisa);
        if (listaEmpresas.isEmpty()) {
            messages.info("Sua consulta não retornou registros.");
        }
    }
    public void todasEmpresas(){
        listaEmpresas = empresas.todas();
    }

    public List<RamoAtividade> completarRamoAtividade(String termo){
        List<RamoAtividade> listaRamoAtividades = ramoAtividades.pesquisar(termo);

        ramoAtividadConverter = new RamoAtividadeConverter(listaRamoAtividades);

        return listaRamoAtividades;
    }

    private boolean jaHouverPesquisa(){
        return termoPesquisa != null && !"".equals(termoPesquisa);
        // ... e(&&) se uma string vazia é diferente da propriedade termo pesquisa, então alguma pesquisa já foi disparada
        // então vou dispará-la novamente para atualizar os dados presentes na DataTable
    }

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }    
    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public TipoEmpresa[] getTiposEmpresa(){
        return TipoEmpresa.values();
    }

    public Converter getRamoAtividadConverter() {
        return ramoAtividadConverter;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public boolean  isEmpresaSelecionada(){
        //  se a empresa for fiferente de null e ...
        return empresa != null && empresa.getId() != null;
                // retorna true
    }
}
