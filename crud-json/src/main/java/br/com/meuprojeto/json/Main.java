package br.com.meuprojeto.json;

public class Main {
    public static void main(String[] args) {
        ClienteCRUDJSON crud = new ClienteCRUDJSON();
        crud.salvar(new Cliente(1, "Luis"));
        System.out.println("Cliente salvo no JSON!");
    }
}
