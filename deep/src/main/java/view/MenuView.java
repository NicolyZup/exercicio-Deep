package view;



import service.CarroService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuView {
    private Scanner scanner;
    private CarroService carroService;

    public MenuView() {
        scanner = new Scanner(System.in);
        carroService = new CarroService();
    }

    public void menuIniciar() {
        int opcao;
        do {
            menuExibir();
            opcao = opcaoEscolhida();

            switch (opcao) {
                case 1:
                    carroService.consultarCarros();
                    break;
                case 2:
                    System.out.println("Informe o carro do cliente:");
                    String nomeCarro = scanner.nextLine();

                    System.out.println("Informe o nome do cliente:");
                    String nomeCliente = scanner.nextLine();

                    System.out.println("Informe a placa do carro:");
                    String placa = scanner.nextLine();

                    System.out.println("Informe o cpf do cliente:");
                    String cpfCliente = scanner.nextLine();

                    carroService.entradaCarro(nomeCarro,placa,nomeCliente,cpfCliente);
                    break;
                case 3:
                    System.out.println("Informe a placa do carro que está saindo:");
                    String placaSaida = scanner.next();

                    carroService.registrarSaidaCarro(placaSaida);
                    break;
                case 4:

                    break;
                case 5:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }
        } while (opcao != 5);
    }

    public void menuExibir() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("-------------------------------------------");
        System.out.println("| Qual operação deseja realizar?          |");
        System.out.println("| (1) Consultar carros no estacionamento  |");
        System.out.println("| (2) Entrada de carro                    |");
        System.out.println("| (3) Saída de carro                      |");
        System.out.println("| (4) Verificar histórico de permanências |");
        System.out.println("| (5) Sair do sistema                     |");
        System.out.println("-------------------------------------------");
    }

    public int opcaoEscolhida() {
        try {
            int opcao = scanner.nextInt();
            scanner.nextLine();
            return opcao;
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        return 1;
    }
}
