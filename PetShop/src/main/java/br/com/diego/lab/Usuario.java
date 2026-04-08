package br.com.diego.lab;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    protected String login;
    protected String senha;
    protected  List<Pet> pets;

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
        this.pets = new ArrayList<>();
    }

    public String getLogin() {
        return login;
    }

    public boolean autenticar(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }

    public List<Pet> getPets() {
        return pets;
    }
}