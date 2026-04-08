package br.com.diego.lab;

public class UsuarioComum extends Usuario {

    public UsuarioComum(String login, String senha) {
        super(login, senha);
    }

    public void visualizarPets() {
        System.out.println("Visualizando pets...");
    }
}