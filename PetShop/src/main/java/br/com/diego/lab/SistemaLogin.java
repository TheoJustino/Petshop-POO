package br.com.diego.lab;

import java.util.*;

public class SistemaLogin {

    public static Usuario fazerLogin(Scanner sc, List<Usuario> usuarios) {

        System.out.println("Login:");
        String login = sc.nextLine();

        System.out.println("Senha:");
        String senha = sc.nextLine();

        for (Usuario u : usuarios) {
            if (u.autenticar(login, senha)) {
                return u;
            }
        }
        return null;
    }
}
