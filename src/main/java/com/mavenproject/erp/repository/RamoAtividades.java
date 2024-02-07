package com.mavenproject.erp.repository;
import com.mavenproject.erp.model.RamoAtividade;
import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author walber
 */
public class RamoAtividades implements Serializable{
    private static final Long serialVersionUID = 1L;
    
    private EntityManager manager;

    public RamoAtividades(){
    }

    public RamoAtividades(EntityManager manager){
        this.manager = manager;
    }
    
    // implementar utilizando criteria
    public List<RamoAtividade> pesquisar(String descricao){
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

        // aqui é mais complexo do que usar jpql. Pode-se fazer das duas maneiras
        CriteriaQuery<RamoAtividade> criteriaQuery = criteriaBuilder.createQuery(RamoAtividade.class);
        // epécie de alias
        /*  por exemplo
         *      select ra.* from ramo_atividade ra;
         */
        Root<RamoAtividade> root = criteriaQuery.from(RamoAtividade.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.like(root.get("descricao"), descricao +"%"));

        TypedQuery<RamoAtividade> query = manager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}