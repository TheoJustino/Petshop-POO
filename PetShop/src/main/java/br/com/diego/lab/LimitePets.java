package br.com.diego.lab;

import java.util.List;
import java.util.Scanner;

public class LimitePets {
    public static int LIMITE_PETS = 5;

    public static boolean podeCadastrar(List<Pet> listaPets) {
        return listaPets.size() < LIMITE_PETS;
    }

    public static void alterarLimite(Scanner sc) {
        System.out.println("Digite o novo limite de pets:");
        LIMITE_PETS = sc.nextInt();
        sc.nextLine();
        System.out.println("Novo limite definido: " + LIMITE_PETS);
    }

    public static void mostrarCapacidade(List<Usuario> usuarios) {
        int totalPets = 0;
        for (Usuario u : usuarios) {
            totalPets += u.getPets().size();
        }
        System.out.println("Pets cadastrados: " + totalPets);
        System.out.println("Limite: " + LIMITE_PETS);
    }
}
