package com.mavenproject.idepen;

import java.math.BigDecimal;

import javax.inject.Inject;

public class RelatorioService {

    @Inject // informa a injeção por meio do CDI
    private Pedidos pedidos;
    // é uma dependência. Como suprir
    // forma 1/3 -> = new Pedidos()

    // forma 2/3 -> Pelo construtor
    public RelatorioService(Pedidos pedidos) { // aqui será injetado pela Main
        this.pedidos = pedidos;
    }

    // form 3/3 -> pelo setter
    public RelatorioService() {

    }


    public BigDecimal totalPedidosMesAtual(){
        return pedidos.totalPedidosMesAtual();
    }

    // 3/3 -> injecao pelo setter
    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

}
