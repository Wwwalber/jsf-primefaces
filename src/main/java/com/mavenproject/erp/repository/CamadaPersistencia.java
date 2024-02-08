package com.mavenproject.erp.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mavenproject.erp.model.Empresa;
import com.mavenproject.erp.model.RamoAtividade;
import com.mavenproject.erp.model.TipoEmpresa;

/**
 *
 * @author walber
 */
public class CamadaPersistencia {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jsf_prime_PU");

        EntityManager em = emf.createEntityManager();
    
        em.getTransaction().begin();// se não iniciar a transção pode fazer somente consultas

        // declarando os repositórios
        RamoAtividades ramoAtividades = new RamoAtividades(em);
        Empresas empresas = new Empresas(em);

        // buscando as informações no banco
        List<RamoAtividade> listaDeRamoAtividades = ramoAtividades.pesquisar("");// traz todos os registros
        List<Empresa> listaDeEmpresas = empresas.pesquisar(""); // traz todos os registros
        System.out.println(listaDeEmpresas);
 
        // criando uma empresa
        
        Empresa empresa = new Empresa();
        empresa.setNomeFantasia("João da Silva");
        empresa.setCnpj("41.952.519/0001-57");
        empresa.setRazaoSocial("João da Silva 41952519000157");
        empresa.setTipo(TipoEmpresa.MEI);
        empresa.setDataFundacao(new Date());
        empresa.setRamoAtividade(listaDeRamoAtividades.get(0));// pegando o primeiro registro

        // salvando a empresa
        empresas.guardar(empresa);

        em.getTransaction().commit(); //

        //verificar se a transação funcionou
        List<Empresa> listaDeEmpresas2 = empresas.pesquisar("");
        System.out.println(listaDeEmpresas2);

        em.close();
        emf.close();
    }
}

