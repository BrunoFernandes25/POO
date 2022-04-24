package Ficha4;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class FBPost {
    private int identificador;
    private String nome;
    private LocalDateTime instantePost;
    private String conteudo;
    private int likes;
    private ArrayList<String> comentarios;

    public FBPost (){
        this.identificador = 0;
        this.nome = "";
        this.instantePost = LocalDateTime.now();
        this.conteudo = "";
        this.likes = 0;
        this.comentarios = new ArrayList<String>();
    }

    public FBPost(int identificador, String nome, LocalDateTime instantePost, String conteudo, int likes, ArrayList<String> comentarios) {
        this.identificador = identificador;
        this.nome = nome;
        this.instantePost = instantePost;
        this.conteudo = conteudo;
        this.likes = likes;
        this.comentarios = new ArrayList<String>(comentarios);
    }

    public FBPost(FBPost post){
            this.identificador = post.getIdentificador();
            this.nome = post.getNome();
            this.instantePost = post.getInstantePost();
            this.conteudo = post.getConteudo();
            this.likes = post.getLikes();
            this.comentarios = post.getComentarios();
    }


    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getInstantePost() {
        return instantePost;
    }

    public void setInstantePost(LocalDateTime instantePost) {
        this.instantePost = instantePost;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public ArrayList<String> getComentarios() {
        return new ArrayList<String>(comentarios);
    }

    public void setComentarios(ArrayList<String> comentarios) {
        this.comentarios = new ArrayList<String>(comentarios);
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FBPost fbPost = (FBPost) o;
        return identificador == fbPost.identificador && likes == fbPost.likes && Objects.equals(nome, fbPost.nome) && Objects.equals(instantePost, fbPost.instantePost) && Objects.equals(conteudo, fbPost.conteudo) && Objects.equals(comentarios, fbPost.comentarios);
    }

    public String toString() {
        return "FBPost\n{" +
                " identificador = " + identificador +
                ", nome = '" + nome + '\'' +
                ", instantePost = " + instantePost +
                ", conteudo = '" + conteudo + '\'' +
                ", likes = " + likes +
                ", comentarios = " + comentarios +
                '}';
    }

    public FBPost clone() {
        return new FBPost(this);
    }


    public void adicionaComentario(String comentario){
        this.comentarios.add(comentario);
    }


}

