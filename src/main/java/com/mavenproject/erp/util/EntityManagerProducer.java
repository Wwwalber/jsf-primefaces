package com.mavenproject.erp.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped // proporciona que a instância gerada sobreviva durante todo o ciclo de vida da aplicação
public class EntityManagerProducer {
    private EntityManagerFactory factory;

    public EntityManagerProducer() {
        this.factory = Persistence.createEntityManagerFactory("jsf_prime_PU");
    }

    //informa pro CDI que o método creatEntityManager é um método produtor de EntitiManager's
    @Produces
    @RequestScoped  // a cada requisição, uma instância nova
    public EntityManager createEntityManager(){
        return  this.factory.createEntityManager();
    }

    // providencias após o encerramento de um EntityManager
    public void closeEntityManager(@Disposes EntityManager manager){ // o CDI saberá que deve chamar este método devido a anotação @Dispose
        manager.close();
    }
    
}
