import java.util.ArrayList;
import java.util.List;

public abstract class Conta {
    private final String numConta;
    private final String agencia;
    private final String nome;
    private double saldo = 0;
    private final String cpf;
    private String senha;
    private final List<Transacao> historico = new ArrayList<>();
    List<Conta> contas = new ArrayList<>();


    public Conta(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.agencia = Integer.toString((int) (Math.random() * (999 - 100)));
        this.numConta = Integer.toString((int) (Math.random() * (999999 - 100000)));
    }


    void depositar(double valor){
        double saldoAnterior = this.saldo;
        this.saldo += valor;
        this.historico.add(new Transacao(saldoAnterior,this.saldo,"Depósito",valor));
    }

    void sacar(double valor){
        double saldoAnterior = this.saldo;
        this.saldo -= valor;
        this.historico.add(new Transacao(saldoAnterior,this.saldo,"Saque",valor));
    }
    void transferir(double valor, Conta contaDestino){
        this.sacar(valor);
        contaDestino.depositar(valor);
    }
    StringBuilder extrato(){
        StringBuilder saida = new StringBuilder("=====Extrato bancário completo=====\n");
        for (Transacao transacao : historico) {
            saida.append(transacao.infoTransacao());
        }
        return saida;
    }

    boolean mudarSenha(String senhaAtual, String senhaNova){
        if(this.senha.equals(senhaAtual)){
            this.senha = senhaNova;
            return true;
        }
        return false;
    }
    abstract void infoConta();

    String getNumConta(){
        return this.numConta;
    }

    String getAgencia(){
        return this.agencia;
    }

    String getNome(){
        return this.nome;
    }

    String getCpf(){
        return this.cpf;
    }

    double getSaldo(){
        return this.saldo;
    }

    String getSenha(){ return this.senha; }
}
