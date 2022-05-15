import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco {
    private final List<Conta> contas = new ArrayList<>();

    enum tipoConta {
        CORRENTE,
        POUPANCA
    }

    Conta criarConta(String nome, String cpf, String senha, tipoConta tipoConta){
        Conta conta = null;
        if(tipoConta == Banco.tipoConta.CORRENTE){
            conta = new ContaCorrente(nome, cpf, senha);
            contas.add(conta);
        }else if(tipoConta == Banco.tipoConta.POUPANCA){
            conta = new ContaPoupanca(nome,cpf,senha);
            contas.add(conta);
        }
        return conta;
    }

    void excluirConta(Conta conta){
        contas.remove(conta);
    }

    void acessarConta(Conta conta, String senha) throws IOException, InterruptedException {
        Scanner entrada = new Scanner(System.in);
        if(conta.getSenha().equals(senha)){
            conta.infoConta();
            System.out.println("1) Depósito");
            System.out.println("2) Extrato");
            System.out.println("3) Transferência");
            System.out.println("4) Mudar senha da conta");
            System.out.println("5) Excluir conta");
            System.out.println("6) Sair e voltar a tela inicial");

            switch (entrada.nextInt()){
                case 1 -> {
                    double valor;
                    Main.limparTela();
                    System.out.print("Digite o valor que deseja depositar: ");
                    valor = entrada.nextDouble(); entrada.nextLine();

                    conta.depositar(valor);
                }
                case 2 -> System.out.println(conta.extrato());
                case 3 -> {
                    double valor;
                    System.out.println("1) Transferir com agência e número da conta destino");
                    System.out.println("2) Transferir com o cpf do titular da conta destino");
                    switch (entrada.nextInt()){
                        case 1 -> {
                            String agencia, numConta;
                            System.out.print("Digite a agencia: ");
                            agencia = entrada.next(); entrada.nextLine();
                            System.out.print("Digite a conta: ");
                            numConta = entrada.next(); entrada.nextLine();
                            System.out.print("Digite o valor: ");
                            valor = entrada.nextDouble();

                            conta.transferir(valor, this.getConta(agencia, numConta));

                        }
                        case 2 -> {
                            String cpf;
                            System.out.print("Digite o cpf: ");
                            cpf = entrada.next(); entrada.nextLine();
                            System.out.print("Digite o valor: ");
                            valor = Double.parseDouble(entrada.nextLine());

                            conta.transferir(valor, this.getConta(cpf));
                        }
                    }
                }
                case 4 -> {
                    String senhaAntiga;

                    System.out.print("Digite sua senha antiga: ");
                    senhaAntiga = entrada.nextLine();
                    System.out.print("Digite sua nova senha: ");
                    senha = entrada.nextLine();

                    if(conta.mudarSenha(senhaAntiga, senha)){
                        System.out.println("Senha alterada com sucesso!");
                    }else{
                        System.out.println("Senha atual incorreta!");
                    }
                }
                case 5 -> {
                    System.out.println("Digite sua senha para confirmar a exclusão da conta\n: ");
                    senha = entrada.nextLine();

                    if(conta.getSenha().equals(senha)){
                        this.excluirConta(conta);
                        System.out.println("Conta excluída com sucesso!");
                    }else{
                        System.out.println("Senha incorreta!");
                    }
                }
                case 6 -> {
                    return;
                }
            }

        }else{
            System.out.println("Senha incorreta!");
        }
    }
    Conta getConta(String agencia, String numConta){
        for (Conta conta : contas) {
            if (conta.getAgencia().equals(agencia) && conta.getNumConta().equals(numConta)) {
                return conta;
            }
        }
        System.out.printf("Conta não encontrada (AG: %s / Conta: %s)", agencia, numConta);
        return null;
    }

    Conta getConta(String cpf){
        for (Conta conta : contas) {
            if (conta.getCpf().equals(cpf)) {
                return conta;
            }
        }
        System.out.printf("Não há contas associadas ao cpf informado (%s)", cpf);
        return null;
    }

}
