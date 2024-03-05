package com.mavenproject.idepen;

public class Main {
    public static void main(String[] args) {
        Pedidos pedidos = new Pedidos();

                        // aqui está injetando uma instância de Pedido
        RelatorioService relatorioService = new RelatorioService(pedidos);
        
        // ou injetando pelo método setter
        RelatorioService relatorioService1 = new RelatorioService();
        relatorioService.setPedidos(pedidos);
        // o melhor é delegar para o CDI
        
    }
}
