public class Transacao {
    double saldoAnterior;
    double saldoAtual;
    String tipoTransacao;
    double valorTransacao;

    public Transacao(double saldoAnterior, double saldoAtual, String tipoTransacao, double valorTransacao) {
        this.saldoAnterior = saldoAnterior;
        this.saldoAtual = saldoAtual;
        this.tipoTransacao = tipoTransacao;
        this.valorTransacao = valorTransacao;
    }

    String infoTransacao(){
        return "Saldo anterior: R$"+this.saldoAnterior+"\n"+
                this.tipoTransacao+" realizado no valor de R$"+this.valorTransacao+"\n"+
                "Saldo atual: R$"+this.saldoAtual+"\n";
    }

}
