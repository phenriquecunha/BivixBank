public class ContaCorrente extends Conta{

    public ContaCorrente(String nome, String cpf, String senha) {
        super(nome, cpf, senha);
    }

    @Override
    void infoConta() {
        System.out.printf("Titular: %s\nCPF: %s\nAg: %s\nConta Corrente: %s\nSaldo: %.2f\n",this.getNome(),this.getCpf(),this.getAgencia(),this.getNumConta(),this.getSaldo());
    }
}
