package com.mavenproject.erp.controller;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.mavenproject.erp.model.RamoAtividade;

/**
 *
 * @author walber
 */
public class RamoAtividadeConverter implements Converter {

    private List<RamoAtividade> listaRamoAtividades;
    
    public RamoAtividadeConverter(List<RamoAtividade> listaRamoAtividades) {
        this.listaRamoAtividades = listaRamoAtividades;
    }

    // vai ser chamado quando houver uma submissão. o auto complete vai enviar a stirng do que foi escolhido
    // informa o (no caso, ramoAtividade) referente a string
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        Long id = Long.valueOf(value);
        for (RamoAtividade ramoAtividade : listaRamoAtividades) {
            if (id.equals(ramoAtividade.getId())) {
                return ramoAtividade;
            }
        }
        return null;
    }

    // na renderização do autocomplete (vinculado a algum vaor) mostra qual o valor
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        RamoAtividade ramoAtividade = (RamoAtividade)value;
        //avisa ao elemento autocomplete como uma entidade (ramoAtividade) sera representada
        return ramoAtividade.getId().toString();
    }
    
}
