package br.com.diego.lab;

public class Administrador extends Usuario {

    public Administrador(String login, String senha) {
        super(login, senha);
    }

    public void acessarRelatorio() {
        System.out.println("Acessando relatório administrativo...");
    }
}
