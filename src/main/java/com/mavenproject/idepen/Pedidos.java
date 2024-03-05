package com.mavenproject.idepen;

import java.math.BigDecimal;

public class Pedidos {
    /* e para essa propriedade a criação de EntityManegr exige 
    toda uma preparação. Ele vai usar o "createEntityManager anotado com @Produces"
    @Inject
    private EntityManager manager;
    */
    public BigDecimal totalPedidosMesAtual(){
        // simulação
        return new BigDecimal("100");
    }
}
