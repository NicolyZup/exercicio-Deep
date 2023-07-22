package org.example;

import view.MenuView;

import static connection.Conexao.getConnection;

public class Main {
    public static void main(String[] args) {
        MenuView menuView = new MenuView();
        System.out.println("==========================================");
        System.out.println("     BEM VINDES AO DEEP ESTACIONAMENTO    ");
        System.out.println("==========================================");
        menuView.menuIniciar();
    }
}