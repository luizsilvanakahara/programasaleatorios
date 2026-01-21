import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dicionario {

    public static void main(String[] args) {
        // Cria o HashMap para armazenar palavras e traduções
        Map<String, String> dicionario = new HashMap<>();

        // Scanner para ler entrada do usuário
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Adicionar palavra");
            System.out.println("2 - Consultar tradução");
            System.out.println("3 - Listar todas as palavras");
            System.out.println("0 - Sair");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Digite a palavra em inglês: ");
                    String ingles = scanner.nextLine();
                    System.out.print("Digite a tradução: ");
                    String traducao = scanner.nextLine();
                    dicionario.put(ingles, traducao);
                    System.out.println("Palavra adicionada!");
                    break;

                case "2":
                    System.out.print("Digite a palavra em inglês para consultar: ");
                    String palavra = scanner.nextLine();
                    if (dicionario.containsKey(palavra)) {
                        System.out.println("Tradução: " + dicionario.get(palavra));
                    } else {
                        System.out.println("Palavra não encontrada.");
                    }
                    break;

                case "3":
                    System.out.println("Dicionário completo:");
                    for (Map.Entry<String, String> entry : dicionario.entrySet()) {
                        System.out.println(entry.getKey() + " -> " + entry.getValue());
                    }
                    break;

                case "0":
                    System.out.println("Encerrando o programa.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
