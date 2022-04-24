package Ficha4;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class EncEficiente {
    private String nome;
    private long NIF;
    private String morada;
    private int numero;
    private LocalDateTime data;
    private ArrayList<LinhaEncomenda> encomendas;


    public EncEficiente() {
        setNome("");
        setNIF(0);
        setMorada("");
        setNumero(-1);
        setData(LocalDateTime.now());
        setEncomendas(new ArrayList<LinhaEncomenda>());
    }

    public EncEficiente(String nome, long nif, String morada, int numero, LocalDateTime data, ArrayList<LinhaEncomenda> encomendas) {
        this.nome = nome;
        this.NIF = nif;
        this.morada = morada;
        this.numero = numero;
        this.data = data;
        this.setEncomendas(encomendas);
    }

    public EncEficiente(EncEficiente encomenda) {
        this.nome = encomenda.getNome();
        this.NIF = encomenda.getNIF();
        this.morada = encomenda.getMorada();
        this.numero = encomenda.getNumero();
        this.data= encomenda.getData();
        this.encomendas = encomenda.getEncomendas();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getNIF() {
        return NIF;
    }

    public void setNIF(long NIF) {
        this.NIF = NIF;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public ArrayList<LinhaEncomenda> getEncomendas() {
        return encomendas;
    }

    public void setEncomendas(ArrayList<LinhaEncomenda> encomendas) {
        this.encomendas = encomendas;
    }



    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EncEficiente that = (EncEficiente) o;
        return NIF == that.NIF && numero == that.numero && Objects.equals(nome, that.nome) && Objects.equals(morada, that.morada) && Objects.equals(data, that.data) && Objects.equals(encomendas, that.encomendas);
    }



    public String toString() {
        return "\nEncEficiente {" +
                "Nome = '" + nome + '\'' +
                ", NIF = " + NIF + '\'' +
                ", Morada = '" + morada + '\'' +
                ", Numero da encomenda = " + numero +
                ", Data = " + data +
                ", Encomendas =" + encomendas +
                "}\n";
    }

    public EncEficiente clone(){
        return new EncEficiente(this);
    }

    public double calculaValorTotal(){
        return this.encomendas.stream()
                              .mapToDouble(LinhaEncomenda::calculaValorLinhaEnc)
                              .sum();
    }

    public double calculaValorDesconto() {
        return this.encomendas.stream()
                              .mapToDouble(LinhaEncomenda::calculaValorDesconto)
                              .sum();
    }

    public int numeroTotalProdutos(){
        return this.encomendas.stream()
                .mapToInt(LinhaEncomenda::getQuantidade)
                .sum();
    }
    //anyMatch -> verifica se algum elemento faz match
    public boolean existeProdutoEncomenda(String referencia) {
        return this.encomendas.stream().anyMatch(prod -> prod.getReferencia().equals(referencia));
    }

    public void adicionaLinha (LinhaEncomenda l) {
        this.encomendas.add(l.clone()); // ou add(l.clone());
    }

    public void removeProduto (String codProd) {
        this.encomendas.removeIf(prod -> prod.getReferencia().equals(codProd));
    }



}
