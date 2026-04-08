package br.com.diego.lab;

import java.util.List;
import java.util.Scanner;

public class ServicosPets {
    double PrecoBanhoTosa;
    double PrecoBanho;
    double PrecoTosa;
    double PrecoTosaHigienica;

    public static void calcularPrecoPetEscolhido(Scanner sc, Usuario usuarioLogado){ 
        List<Pet> pets = usuarioLogado.getPets(); 
        if (pets.isEmpty()) { 
            System.out.println("Nenhum pet cadastrado."); 
            Main.pausar(sc); 
            return; 
        } 
        
        System.out.println("Escolha um pet:");
        
        for (int i = 0; i < pets.size(); i++) { 
            System.out.println("\n" + (i + 1) + " - " + pets.get(i).getNome()); 
        } 

        int escolha = sc.nextInt(); sc.nextLine();

        if (escolha < 1 || escolha > pets.size()) { 
            System.out.println("Opção inválida.");
            Main.pausar(sc);
            return; 
        } 
        
        Pet pet = pets.get(escolha - 1); 
        
        double taxaPeso = calcularTaxaPeso(pet.getPeso()); 
        double taxaIdade = calcularTaxaIdade(pet.getIdade()); 
        double precoBanhoTosa = 60 + taxaIdade + taxaPeso; double precoBanho = 30 + taxaIdade + taxaPeso; double precoTosa = 40 + taxaIdade + taxaPeso;
        double precoTosaHigienica = 15 + taxaIdade + taxaPeso;
        
        Main.limparTela(); 
        
        System.out.println("Deseja incluir frete de R$10? (1 - Sim | 2 - Não)"); 
        int opcaoFrete = sc.nextInt(); sc.nextLine(); 

        switch (opcaoFrete){
            case 1: 
                System.out.println("\nCom frete, os preços finais são: "); 
                System.out.println("Banho e Tosa: R$" + aplicarFrete(precoBanhoTosa)); 
                System.out.println("Banho: R$" + aplicarFrete(precoBanho)); 
                System.out.println("Tosa: R$" + aplicarFrete(precoTosa)); 
                System.out.println("Tosa Higiênica: R$" + aplicarFrete(precoTosaHigienica));
            break;
            case 2: 
                System.out.println("Sem frete, os preços são: "); 
                System.out.println("Banho e Tosa: R$" + precoBanhoTosa); 
                System.out.println("Banho: R$" + precoBanho); 
                System.out.println("Tosa: R$" + precoTosa); 
                System.out.println("Tosa Higiênica: R$" + precoTosaHigienica);
            break;
            default: 
                System.out.println("Opção inválida.");
            break;
        }
    }

    private static double calcularTaxaPeso(double peso) {
        if (peso <= (double)5.0F) {
            return (double)0.0F;
        } else if (peso <= (double)7.0F) {
            return (double)10.0F;
        } else if (peso <= (double)12.0F) {
            return (double)15.0F;
        } else {
            return peso <= (double)18.0F ? (double)25.0F : (double)0.0F;
        }
    }

    private static double calcularTaxaIdade(int idade) {
        if (idade <= 1) return 10;
        else if (idade <= 7) return 0;
        else if (idade <= 10) return 5;
        else if (idade <= 15) return 10;
        else return 15;
    }

    private static double aplicarFrete(double preco) {
    return preco + 10;
    }
}
// Banho 35 
// Tosa 50
// Banho e Tosa 75
// Tosa Higiênica 25
    

