import java.util.ArrayList;
import java.util.Scanner;

class Participante {
    String nome;
    int idade;
    String celular;
    String email;
    
    Participante(String nome, int idade, String celular, String email) {
            this.nome = nome;
            this.idade = idade;
            this.celular = celular;
            this.email = email;
    }
    //Layout da busca e da lista
    void mostrarInfo() {
        System.out.println(
            "Nome: " + nome + ", Idade: " + idade + "\n" +
            "Celular: " + celular + ", Email: " + email
        );
    }
}

public class Gestão_de_Participantes {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ArrayList<Participante> listaParticipantes = new ArrayList<>();
        int opcao;
        
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Cadastrar participante");
            System.out.println("2 - Listar participantes");
            System.out.println("3 - Buscar participante");
            System.out.println("4 - Remover participante");
            System.out.println("5 - Exibir estatísticas");
            System.out.println("6 - Sair do menu");
            System.out.print("Escolha uma opção: ");
            opcao = entrada.nextInt();
            entrada.nextLine(); // limpar buffer
            
            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = entrada.nextLine();
                    //Limitar a entrada de valores na idade do participante
                    int idade;
                    do {
                        System.out.print("Idade: ");
                        idade = entrada.nextInt();
                        entrada.nextLine(); // limpar buffer
                    
                        if (idade < 0 || idade > 120) {
                            System.out.println("Idade inválida! Digite um valor entre 0 e 120.");
                        }                    
                    } while (idade < 0 || idade > 120);
                    //Adicionar um celular
                    System.out.print("Celular: ");
                    String celular = entrada.nextLine();
                    //Adicionar um Email
                    System.out.print("Email: ");
                    String email = entrada.nextLine();
                    
                    listaParticipantes.add(new Participante(nome, idade, celular, email));
                    System.out.println("Participante cadastrado com sucesso!");
                    break;
                    
                case 2:
                    System.out.println("\n--- Lista de Participantes ---");
                    if (listaParticipantes.isEmpty()) {
                        System.out.println("Nenhum participante cadastrada.");
                    } else {
                        for (Participante p : listaParticipantes) {
                            p.mostrarInfo();
                            System.out.println("-------------------");
                        }
                    }
                    break;
                    
                case 3:
                    System.out.print("\nDigite o nome, email ou celular para buscar: ");
                    String busca = entrada.nextLine();
                    boolean encontrado = false;

                    for (Participante a : listaParticipantes) {
                        if (a.nome.equalsIgnoreCase(busca) || a.email.equalsIgnoreCase(busca) || a.celular.equalsIgnoreCase(busca)) {
                            a.mostrarInfo();
                            encontrado = true;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("\nParticipante não encontrado.");
                    }
                    break;

                case 4:
                    if (listaParticipantes.isEmpty()) {
                        System.out.println("\nNenhum participante para remover.");
                        break;
                    }
                    
                    System.out.print("\nDigite o nome para remover: ");
                    String nomeRemover = entrada.nextLine();
                    boolean removido = false;
                    
                    for (int i = 0; i < listaParticipantes.size(); i++) {
                        if (listaParticipantes.get(i).nome.equalsIgnoreCase(nomeRemover)) {
                            listaParticipantes.remove(i);
                            removido = true;
                            System.out.println("Participante removido com sucesso!");
                            break;
                        }
                    }
                    
                    if (!removido) {
                        System.out.println("Participante não encontrado.");
                    }
                    break;
                case 5:
                    if (listaParticipantes.isEmpty()) {
                        System.out.println("Nenhum participante cadastrado.");
                        break;
                    }

                    int total = listaParticipantes.size();
                    int somaIdades = 0;
                    int jovens = 0, adultos = 0, idosos = 0;

                    for (Participante p : listaParticipantes) {
                        somaIdades += p.idade;

                        if (p.idade <= 17) {
                            jovens++;
                        } else if (p.idade <= 59) {
                            adultos++;
                        } else {
                            idosos++;
                        }
                    }

                    double media = (double) somaIdades / total;
                    //Lista das estatisticas
                    System.out.println("\n--- Estatísticas ---");
                    System.out.println("Total de participantes: " + total);
                    System.out.printf("Média de idade: %.2f\n", media);
                    System.out.println("Jovens (0-17): " + jovens);
                    System.out.println("Adultos (18-59): " + adultos);
                    System.out.println("Idosos (60+): " + idosos);
                    break;

                case 6:
                    System.out.println("Encerrando o sistema...");
                    break;
                    
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 6);
        
        entrada.close();
    }
}