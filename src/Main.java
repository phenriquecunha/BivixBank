import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Banco banco = new Banco();
        Scanner entrada = new Scanner(System.in);
        int opcao;

        while (true){

            limparTela();
            System.out.println("***** BivixBank - Seu banco digital! *****");
            System.out.println("1) Abrir conta");
            System.out.println("2) Acessar conta");
            System.out.println("3) Sair");
            System.out.print(": ");
            opcao = entrada.nextInt(); entrada.nextLine();
            switch (opcao) {
                case 1 -> abrirConta(banco);
                case 2 -> acessarConta(banco);
                case 3 -> System.exit(0);
            }
        }
    }

    static void abrirConta(Banco banco) throws IOException, InterruptedException {
        String nome, cpf, senha;
        int resposta;
        Banco.tipoConta tipoConta;

        Scanner entrada = new Scanner(System.in);

        limparTela();
        System.out.print("Digite seu nome completo: ");
        nome = entrada.nextLine();
        System.out.print("Digite seu cpf sem pontos e traços: ");
        cpf = entrada.next(); entrada.nextLine();
        System.out.print("Digite uma senha para sua conta: ");
        senha = entrada.nextLine();
        System.out.println("Selecione o tipo de conta a ser criada");
        System.out.print("1) Conta corrente\n2) Conta poupança\n: ");
        resposta = entrada.nextInt(); entrada.nextLine();

        switch (resposta) {
            case 1 -> {
                tipoConta = Banco.tipoConta.CORRENTE;
                Conta conta = banco.criarConta(nome, cpf, senha, tipoConta);
                System.out.println("Conta criada com sucesso!");
                conta.infoConta();
                System.out.println("TECLE ENTER PARA VOLTAR A TELA INICIAL");
                entrada.nextLine();
            }
            case 2 -> {
                tipoConta = Banco.tipoConta.POUPANCA;
                Conta conta = banco.criarConta(nome, cpf, senha, tipoConta);
                System.out.println("Conta criada com sucesso!");
                conta.infoConta();
                System.out.println("TECLE ENTER PARA VOLTAR A TELA INICIAL");
                entrada.nextLine();
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    static void acessarConta(Banco banco) throws IOException, InterruptedException {
        String agencia, numConta, cpf, senha;
        Scanner entrada = new Scanner(System.in);

        limparTela();
        System.out.println("1) Entrar com agência e conta");
        System.out.print("2) Entrar com cpf\n: ");

        switch (entrada.nextInt()){
            case 1 -> {
                System.out.print("Digite a agencia: ");
                agencia = entrada.nextLine();
                System.out.print("Digite a conta: ");
                numConta = entrada.nextLine();
                System.out.println("Digite a sua senha: ");
                senha = entrada.nextLine();

                banco.acessarConta(banco.getConta(agencia,numConta), senha);
            }
            case 2 -> {
                System.out.print("Digite o seu cpf(Sem pontos e traços): ");
                cpf = entrada.next(); entrada.nextLine();
                System.out.print("Digite a sua senha: ");
                senha = entrada.nextLine();
                banco.acessarConta(banco.getConta(cpf), senha);
            }
        }
    }

    static void limparTela() throws IOException, InterruptedException {
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
    }
}
