package app;

import model.*;
import service.HelpDesk;
import java.util.Scanner;

public class Main {

    public static PrioridadeChamado escolherPrioridade(Scanner entrada){
        System.out.println("1 - BAIXA\t2 - MEDIA\t3- ALTA");
        int opcao;
        do {
            opcao = entrada.nextInt();
            entrada.nextLine();
            if (opcao == 1) return PrioridadeChamado.BAIXA;
            if (opcao == 2) return PrioridadeChamado.MEDIA;
            if (opcao == 3) return PrioridadeChamado.ALTA;
        } while (opcao < 0 || opcao > 3);
        return null;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        HelpDesk hd = new HelpDesk();

        int opcao;
        do {
            System.out.println("---SISTEMA HELPDESK---");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Abrir Chamada");
            System.out.println("3 - Alterar Status");
            System.out.println("4 - Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Nome");
                    String nome = scanner.nextLine();
                    System.out.println("Email");
                    String mail = scanner.nextLine();
                    hd.cadastrarCliente(nome, mail);
                    System.out.println("Cliente cadastrado!");
                    break;
                case 2:
                    System.out.println("Digite o numero o número do chamado");
                    Integer numero = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Digite o titulo");
                    String titulo = scanner.nextLine();


                    System.out.println("Digite a descrição");
                    String descricao = scanner.nextLine();

                    System.out.println("Email do cliente");
                    String email = scanner.nextLine();

                    Cliente cli = hd.buscarCliente(email);
                    if (cli == null){
                        System.out.println("Cliente não foi encontrado");
                        break;
                    }
                    scanner.nextLine();

                    PrioridadeChamado prio = escolherPrioridade(scanner);

                    hd.abrirChamada(numero, titulo, descricao, prio, cli);

                    System.out.println("Chamado foi aberto!");
                    break;

                case 3: {
                    int op;
                    Integer numChamado;
                    StatusChamado novoStatus;
                    do {
                        System.out.println("Digite o número do chamado:");
                        numChamado = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("1 - ABERTO\t2- EM ANDAMENTO\t3 - RESOLVIDO\t4 - CANCELADO");
                        op = scanner.nextInt();
                        scanner.nextLine();

                        novoStatus = null;

                        if (op == 1) novoStatus = StatusChamado.ABERTO;
                        if (op == 2) novoStatus = StatusChamado.EM_ANDAMENTO;
                        if (op == 3) novoStatus = StatusChamado.RESOLVIDO;
                        if (op == 4) novoStatus = StatusChamado.CANCELADO;
                    } while(op <=0 || op >=5);
                    hd.alterarStatus(numChamado, novoStatus);
                    break;
                }
            }

        }while (opcao != 4);
    }

}
