package Ficha4;


import java.time.LocalDateTime;
import java.util.Objects;

public class PedidoSuporte {

    private String nome;
    private LocalDateTime instantePedido;
    private String assunto;
    private String descricao;
    private String informatico;
    private LocalDateTime instanteConclusao;
    private String informacaoDoProblema;
    private boolean resolvido;

    public PedidoSuporte(){
        this.nome = "";
        this.instantePedido = LocalDateTime.now();
        this.assunto = "";
        this.descricao = "";
        this.informatico = "";
        this.instanteConclusao = LocalDateTime.now();
        this.informacaoDoProblema = "";
        this.resolvido = false;
    }

    public PedidoSuporte(String nome, LocalDateTime instantePedido, String assunto, String descricao, String informatico, LocalDateTime instanteConclusao, String informacaoDoProblema, boolean resolvido) {
        this.nome = nome;
        this.instantePedido = instantePedido;
        this.assunto = assunto;
        this.descricao = descricao;
        this.informatico = informatico;
        this.instanteConclusao = instanteConclusao;
        this.informacaoDoProblema = informacaoDoProblema;
        this.resolvido = resolvido;
    }

    public PedidoSuporte(PedidoSuporte pedido) {
        this.nome=pedido.getNome();
        this.instantePedido=pedido.getInstantePedido();
        this.assunto=pedido.getAssunto();
        this.descricao = pedido.getDescricao();
        this.informatico = pedido.getInformatico();
        this.instanteConclusao = pedido.getInstanteConclusao();
        this.informacaoDoProblema = pedido.getInformacaoDoProblema();
        this.resolvido = pedido.isResolvido();
    }


    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getInstantePedido() {
        return this.instantePedido;
    }

    public void setInstantePedido(LocalDateTime instantePedido) {
        this.instantePedido = instantePedido;
    }

    public String getAssunto() {
        return this.assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getInformatico() {
        return this.informatico;
    }

    public void setInformatico(String informatico) {
        this.informatico = informatico;
    }

    public LocalDateTime getInstanteConclusao() {
        return this.instanteConclusao;
    }

    public void setInstanteConclusao(LocalDateTime instanteConclusao) {
        this.instanteConclusao = instanteConclusao;
    }

    public String getInformacaoDoProblema() {
        return this.informacaoDoProblema;
    }

    public void setInformacaoDoProblema(String informacaoDoProblema) {
        this.informacaoDoProblema = informacaoDoProblema;
    }

    public boolean isResolvido() {
        return this.resolvido;
    }

    public void setResolvido(boolean resolvido) {
        this.resolvido = resolvido;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoSuporte that = (PedidoSuporte) o;
        return resolvido == that.resolvido && Objects.equals(nome, that.nome) && Objects.equals(instantePedido, that.instantePedido) && Objects.equals(assunto, that.assunto) && Objects.equals(descricao, that.descricao) && Objects.equals(informatico, that.informatico) && Objects.equals(instanteConclusao, that.instanteConclusao) && Objects.equals(informacaoDoProblema, that.informacaoDoProblema);
    }

    public String toString() {
        return "PedidoSuporte {" +
                "nome = '" + nome + '\'' +
                ", instantePedido = " + instantePedido +
                ", assunto = '" + assunto + '\'' +
                ", descricao = '" + descricao + '\'' +
                ", informatico = '" + informatico + '\'' +
                ", instanteConclusao = " + instanteConclusao +
                ", informacaoDoProblema = '" + informacaoDoProblema + '\'' +
                ", resolvido = " + resolvido +
                '}';
    }

    public PedidoSuporte clone(){
        return new PedidoSuporte(this);
    }
}
