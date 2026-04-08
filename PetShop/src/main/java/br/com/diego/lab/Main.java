package br.com.diego.lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean controleprograma = true;

        usuarios.add(new Administrador("admin", "123"));
        usuarios.add(new UsuarioComum("user", "456"));

        do {
            int opcao; 
            System.out.println("1 - Fazer Login");
            System.out.println("2 - Encerrar o programa");
            opcao = sc.nextInt();
            sc.nextLine();
            limparTela();
            
            switch (opcao) {
                case 1: 
                    Usuario usuarioLogado = SistemaLogin.fazerLogin(sc, usuarios);

                    if (usuarioLogado != null) {
                        System.out.println("Login realizado!");
                        if (usuarioLogado instanceof Administrador) {
                            menuAdmin(sc, usuarioLogado, usuarios);
                        } else {
                            menuUsuario(sc, usuarioLogado);
                        }
                    } else {
                        System.out.println("Login inválido");
                    }
                break;
                case 2:
                    controleprograma = false;
                break;
                default:
                    System.out.println("Esse número não é válido. \nTente Novamente.");
                    pausar(sc);
                    limparTela();
                break;
            }
        } while (controleprograma);
    }

    public static void menuAdmin(Scanner sc, Usuario usuarioLogado, List<Usuario> usuarios) {

        int opcao;

        System.out.println("Você é ADMIN");
        ((Administrador) usuarioLogado).acessarRelatorio();
    
        do {
            System.out.println("\n=== MENU ADMIN ===");
            System.out.println("1 - Cadastrar pets");
            System.out.println("2 - Remover pet");
            System.out.println("3 - Ver capacidade");
            System.out.println("4 - Alterar limite");
            System.out.println("5 - Mostrar pets cadastrados");
            System.out.println("0 - Voltar para tela de login");
    
            opcao = sc.nextInt();
            limparTela();
    
            switch (opcao) {
                case 0:
                opcao = 0;
                limparTela();
                break;
                case 1:
                    cadastrarPet(sc, usuarioLogado);
                    pausar(sc);
                    limparTela();
                break;
                case 2:
                    removerPetAdmin(sc, usuarios);
                    pausar(sc);
                    limparTela();
                break;
                case 3:
                    LimitePets.mostrarCapacidade(usuarios);
                    sc.nextLine();
                    pausar(sc);
                    limparTela();
                break;
                case 4:
                    LimitePets.alterarLimite(sc);
                    pausar(sc);
                    limparTela();
                break;
                case 5:
                    listarTodosPets(usuarios);
                    sc.nextLine();
                    pausar(sc);
                    limparTela();
                break;
                default:
                    System.out.println("Esse número não é válido. \nTente Novamente.");
                    pausar(sc);
                    limparTela();
                break;
            }
        } while (opcao != 0);
    }

    public static void menuUsuario(Scanner sc, Usuario usuarioLogado) {

        System.out.println("Você é usuário comum");
        boolean controle = true;
        System.out.println("=============================");
        System.out.println("Seja bem vindo ao pet shop!");
        System.out.println("=============================");

        do {
            System.out.println("Escolha um dos números:");
            System.out.println("1 - Cadastrar Pet");
            System.out.println("2 - Mostrar Pets Cadastrados");
            System.out.println("3 - Serviços disponíveis");
            System.out.println("4 - Excluir Pets");
            System.out.println("5 - Voltar para tela de login");
            System.out.println("=============================");
            int escolha = sc.nextInt();
            limparTela();
            switch (escolha) {
                case 1:
                    cadastrarPet(sc, usuarioLogado);
                    pausar(sc);
                    limparTela();
                break;
                case 2:
                    mostrarPets(usuarioLogado.getPets());
                    sc.nextLine();
                    pausar(sc);
                    limparTela();
                break;
                case 3:
                    ServicosPets.calcularPrecoPetEscolhido(sc, usuarioLogado);
                    pausar(sc);
                    limparTela();
                break;
                case 4:
                    removerPet(sc, usuarioLogado);
                    pausar(sc);
                    limparTela();
                break;
                case 5:
                controle = false;
                limparTela();
                break;
                default:
                    System.out.println("Esse número não é válido. \nTente Novamente.");
                    pausar(sc);
                    limparTela();
                break;
            }
        } while(controle);
    }

    public static void cadastrarPet(Scanner sc, Usuario usuarioLogado) {
        if (!LimitePets.podeCadastrar(usuarioLogado.getPets())) {
        System.out.println("Capacidade máxima atingida!");
        return;
        }

        sc.nextLine();

        System.out.println("Nome do seu pet:");
        String nome = sc.nextLine();

        System.out.println("Idade:");
        int idade = sc.nextInt();
        sc.nextLine();

        System.out.println("Peso:");
        double peso = sc.nextDouble();
        sc.nextLine();

        Pet pet = new Pet(nome, idade, peso);
        usuarioLogado.getPets().add(pet);

        System.out.println("Pet cadastrado com sucesso!");
    }

    public static void mostrarPets(List<Pet> listaPets) {
        if (listaPets.isEmpty()) {
            System.out.println("Nenhum pet cadastrado.");
        } else {
            for(Pet p : listaPets) {
                System.out.println("Nome: " + p.getNome());
                System.out.println("Idade: " + p.getIdade());
                System.out.println("Peso: " + p.getPeso() + "\n");
            }
        }
    }

    public static void listarTodosPets(List<Usuario> usuarios) {
        for (Usuario u : usuarios) {
            for (Pet p : u.getPets()) {
                System.out.println("Dono: " + u.getLogin() +
                                " | Pet: " + p.getNome());
            }
        }
    }

    public static void limparTela() {
      System.out.print("\u001b[H\u001b[2J");
      System.out.flush();
   }

    public static void pausar(Scanner sc) {
      System.out.println("\nPressione ENTER para continuar...");
      sc.nextLine();
   }

    public static void removerPet(Scanner sc, Usuario usuarioLogado) {
    
    sc.nextLine();
    System.out.println("Digite o nome do pet para remover:");
    String nome = sc.nextLine();

    boolean removido = false;

    List<Pet> pets = usuarioLogado.getPets();

    for (int i = 0; i < pets.size(); i++) {
        if (pets.get(i).getNome().equalsIgnoreCase(nome)) {
            pets.remove(i);
            removido = true;
            System.out.println("Pet removido com sucesso!");
            break;
        }
    }

    if (!removido) {
        System.out.println("Pet não encontrado.");
    }
}

    public static void removerPetAdmin(Scanner sc, List<Usuario> usuarios) {

        sc.nextLine();
        System.out.println("Digite o nome do pet:");
        String nome = sc.nextLine();

        for (Usuario u : usuarios) {
            for (int i = 0; i < u.getPets().size(); i++) {
                if (u.getPets().get(i).getNome().equalsIgnoreCase(nome)) {
                    u.getPets().remove(i);
                    System.out.println("Pet removido com sucesso!");
                    return;
                }
            }
        }

        System.out.println("Pet não encontrado.");
    }
}