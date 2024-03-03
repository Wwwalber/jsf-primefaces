package com.mavenproject.erp.util;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor  // diz pro CDI que esta classe é um interceptador
@Transacional // dizer pro CDI que todos os métodos dessa classe devem ser interceptados
@Priority(Interceptor.Priority.APPLICATION) // ativa o interceptador com prioridade de aplicação
public class TransacionalInterceptor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    @AroundInvoke // MÉTODO INTERCEPTADOR quando do chamamento de métodos anotados com @Transacional
    public Object invoke(InvocationContext context) throws Exception{
        EntityTransaction trx = manager.getTransaction();
        boolean criador = false;

        try { // vsi verificar se a transação não foi aberta automaticamente, ou seja por meio do @Transacional
            if (!trx.isActive()) {
                // truque para fazer rollback no que já passou
                // (senão, um futuro commit confirmaria até mesmo operações sem transação)
				trx.begin();
				trx.rollback();
                // agora sim inicia a transação
				trx.begin(); // agora, marca que foi o interceptador que iniciou a transação

				criador = true;
            }
            return context.proceed();// processa o nosso método

        } catch (Exception e) {
            if (trx != null && criador) {
				trx.rollback();
			}
			throw e;
        } finally {
            if (trx != null && trx.isActive() && criador) {
				trx.commit(); // será executado, pela lógica, somente se não houve excessão. Já que teria sido dado rllback acima
			}
        }
    }
}
