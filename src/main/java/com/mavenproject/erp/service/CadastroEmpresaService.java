// regras de negócio
package com.mavenproject.erp.service;

import javax.inject.Inject;

import com.mavenproject.erp.model.Empresa;
import com.mavenproject.erp.repository.Empresas;
import com.mavenproject.erp.util.Transacional;

public class CadastroEmpresaService {
    private static final long serialVersionUID = 1L;

    @Inject // 
    private Empresas empresas;

    @Transacional // esse método precisa de transação, logo
    public void salvar(Empresa empresa){  // saindo daqui, vai lá no TransacionalInterceptor executando 
        //até "return context.proceed(), quando cai aqui novamente
        empresas.guardar(empresa);
        // daqui volta no TransacionalInterceptor para executar apartir de "catch (Exception e)
    } 

    @Transacional  // // esse método precisa de transação
    public void excluir(Empresa empresa){ 
        empresas.remover(empresa);
    }

}
