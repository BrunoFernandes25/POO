package Ficha3;

import java.util.Objects;

public class LinhaEncomenda {

    private String referencia;
    private String descricao;
    private double precoinicial;
    private int quantidade;
    private double imposto;    //podendo ser 6,13,23,etc
    private double desconto;


    public LinhaEncomenda() {
        this.setReferencia("");
        this.setDescricao("");
        this.setPrecoinicial(0);
        this.setQuantidade(0);
        this.setImposto(23);
        this.setDesconto(0);
    }

    public LinhaEncomenda(String referencia, String descricao, double precoinicial, int quantidade, double imposto, double desconto) {
        this.setReferencia(referencia);
        this.setDescricao(descricao);
        this.setPrecoinicial(precoinicial);
        this.setQuantidade(quantidade);
        this.setImposto(imposto);
        this.setDesconto(desconto);
    }

    public LinhaEncomenda(LinhaEncomenda umaEncomenda) {
        this.referencia = umaEncomenda.getReferencia();
        this.descricao = umaEncomenda.getDescricao();
        this.precoinicial = umaEncomenda.getPrecoinicial();
        this.quantidade = umaEncomenda.getQuantidade();
        this.imposto = umaEncomenda.getImposto();
        this.desconto = umaEncomenda.getDesconto();
    }

    public boolean equals(Object linhaEncomenda) {
        if (this == linhaEncomenda) {
            return true;
        }
        if (linhaEncomenda == null || getClass() != linhaEncomenda.getClass()) {
            return false;
        }
        LinhaEncomenda linhaencomenda = (LinhaEncomenda) linhaEncomenda;
        return Double.compare(linhaencomenda.precoinicial, precoinicial) == 0 && quantidade == linhaencomenda.quantidade
                && Double.compare(linhaencomenda.imposto, imposto) == 0 && Double.compare(linhaencomenda.desconto, desconto) == 0
                && Objects.equals(referencia, linhaencomenda.referencia) && Objects.equals(descricao, linhaencomenda.descricao);
    }

    public String toString() {
        return "LinhaEncomenda{" +
                "\n referencia= '" + referencia + '\'' +
                "\n descricao= '" + descricao + '\'' +
                "\n precoinicial= " + precoinicial +
                "\n quantidade= " + quantidade +
                "\n imposto= " + imposto + " %" +
                "\n desconto= " + desconto + " %"+
                "\n}\n";
    }

    public LinhaEncomenda clone() {
        return new LinhaEncomenda(this);
    }


    public String getReferencia() {
        return referencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPrecoinicial() {
        return precoinicial;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getImposto() {
        return imposto;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPrecoinicial(double precoinicial) {
        this.precoinicial = precoinicial;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }


    //b)
    public double calculaValorLinhaEnc() {
        double preco = this.getPrecoinicial() + (this.getPrecoinicial()*(this.getImposto()/100));
        double desconto = this.getPrecoinicial()*(this.getDesconto()/100);

        return this.getQuantidade()*(preco-desconto);
    }

    //c)
    public double calculaValorDesconto() {
        return this.getPrecoinicial()*(this.getDesconto()/100);
    }
}
