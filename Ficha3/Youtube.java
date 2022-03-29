package Ficha3;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.lang.StringBuilder;


public class Youtube {

    private String nome_video;
    private char[] conteudo;
    private LocalDateTime data;
    private  int resolucao;
    private int minutos;
    private  int segundos;
    private String[] comentarios;
    private  int likes;
    private  int dislikes;

    public Youtube() {
        this.setNome_video("");
        this.conteudo = new char[0];
        this.setData(LocalDateTime.now());
        this.setResolucao(1080);
        this.setMinutos(0);
        this.setSegundos(0);
        this.comentarios = new String[0];
        this.setLikes(0);
        this.setDislikes(0);
    }

    public Youtube(String nome_video, char[] conteudo, LocalDateTime data, int resolucao, int minutos, int segundos, String[] comentarios, int likes, int dislikes) {
        this.setNome_video(nome_video);
        this.setConteudo(conteudo);
        this.setData(data);
        this.setResolucao(resolucao);
        this.setMinutos(minutos);
        this.setSegundos(segundos);
        this.setComentarios(comentarios);
        this.setLikes(likes);
        this.setDislikes(dislikes);
    }

    public Youtube(Youtube youtube) {
        this.nome_video = youtube.getNome_video();
        this.conteudo = youtube.getConteudo();
        this.data = youtube.getData();
        this.resolucao = youtube.getResolucao();
        this.minutos = youtube.getMinutos();
        this.segundos = youtube.getSegundos();
        this.comentarios = youtube.getComentarios();
        this.likes = youtube.getLikes();
        this.dislikes = youtube.getDislikes();
    }


    /**
     * Getters
     */
    public String getNome_video() {
        return nome_video;
    }

    public char[] getConteudo() {
        return conteudo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public int getResolucao() {
        return resolucao;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public String[] getComentarios() {
        return comentarios;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    /**
     * Setters
     */
    public void setNome_video(String nome_video) {
        this.nome_video = nome_video;
    }

    public void setConteudo(char[] conteudo) {
        this.conteudo = conteudo;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setResolucao(int resolucao) {
        this.resolucao = resolucao;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public void setComentarios(String[] comentarios) {
        this.comentarios = comentarios;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public boolean equals(Object umVideo) {
        if (this == umVideo) {
            return true;
        }
        if (umVideo == null || getClass() != umVideo.getClass()) {
            return false;
        }
        Youtube youtube = (Youtube) umVideo;
        return (this.getNome_video().equals(youtube.getNome_video()) && Arrays.equals(this.getConteudo(), youtube.getConteudo()) &&
                this.getData() == youtube.getData() && this.getResolucao() == youtube.getResolucao() && this.getMinutos() == youtube.getMinutos() &&
                this.getSegundos() == youtube.getSegundos() && Arrays.equals(this.getComentarios(), youtube.getComentarios()) &&
                this.getLikes() == youtube.getLikes() && this.getDislikes() == youtube.getDislikes());
    }

    public String toString() {
        return "Youtube{" +
                "\n nome_video='" + nome_video + '\'' +
                "\n conteudo=" + Arrays.toString(conteudo) +
                "\n data=" + data +
                "\n resolucao=" + resolucao +
                "\n minutos=" + minutos +
                "\n segundos=" + segundos +
                "\n comentarios=" + Arrays.toString(comentarios) +
                "\n likes=" + likes +
                "\n dislikes=" + dislikes +
                "\n}\n";
    }

    public Youtube clone(){
        return new Youtube(this);
    }

    //b)
    public  void insereComentario (String comentario) {
        String[] comentarios = this.getComentarios();
        String[] comentariosatualizados = new String[comentarios.length +1];
        System.arraycopy(comentarios,0,comentariosatualizados,0,comentarios.length);
        comentariosatualizados[comentariosatualizados.length -1] = comentario;
        this.setComentarios(comentariosatualizados);
    }

    //c)
    public long qtsDiasDepois() {
        LocalDateTime data = this.getData();
        LocalDateTime datahoje = LocalDateTime.now();

        return ChronoUnit.DAYS.between(data,datahoje);
    }

    public void thumbsUp() {
        int likes = this.getLikes();
        this.setLikes(likes + 1);
    }

    public String processa() {
        StringBuilder r = new StringBuilder();
        char[] content = this.getConteudo();
        int i;
        for(i = 0; i<content.length ; i+=1){
            r.append(content[i]);
        }

        return r.toString();
    }
}
