package Ficha4;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;
import java.util.stream.Collectors;

public class FBFeed {
    private ArrayList<FBPost> posts;

    public FBFeed(){
        this.posts = new ArrayList<FBPost>();
    }

    public FBFeed(ArrayList<FBPost> posts){
        this.posts = new ArrayList<FBPost>();
        for(FBPost post : posts){ // Ou em vez de for escreviamos apenas ->  this.posts.addAll(posts);
            this.posts.add(post.clone());
        }
    }

    public  FBFeed (FBFeed feed) {
        this(feed.posts);
    }

    public ArrayList<FBPost> getPosts() {
        return this.posts;
    }

    public void setPosts(ArrayList<FBPost> posts) {
        this.posts = posts;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FBFeed fbFeed = (FBFeed) o;
        return Objects.equals(posts, fbFeed.posts);
    }

    public FBFeed clone(){
        return new FBFeed(this);
    }

    //i
    public int nrPosts(String user){
        return (int)this.posts.stream().filter(posts -> posts.getNome().equals(user)).count();
    }
    //maptoInt nao dava pois FBPost nao tem funcao nenhuma que permitisse usar tal funçao

    //ii
    //import java.util.List;
    public List<FBPost>postsOf(String user){
        List<FBPost> userPosts = new ArrayList<FBPost>();
        for(FBPost post : this.posts) {
            if(post.getNome().equals(user)) {
                userPosts.add(post);
            }
        }
        return userPosts;
    }
    /* ou
    public List<FBPost> postsOf(String user) {
        return this.posts.stream()
                .filter(post -> (post.getUserName().equals(user)))
                .collect(Collectors.toList());
    }
    */
    //iii
    public List<FBPost>postsOf(String user, LocalDateTime inicio, LocalDateTime fim) {
        return this.posts.stream().filter(post -> (post.getNome().equals(user)) && (post.getInstantePost()).isAfter(inicio) && (post.getInstantePost()).isBefore(fim)).collect(Collectors.toList());
    }           //(List<FBPost>) ou -> ver fim linha de cima

    //iv
    public FBPost getPost(int id) {
        return (FBPost) this.posts.stream().filter(post -> post.getIdentificador() == id); //ou alteravamos o equals ou entao nao da para usar assim
    }

    /*
    public FBPost getPost(int id){
        FBPost post = null;
        boolean existe = false;
        for(int i=0; i<this.posts.size() && !existe; i++){
            if(this.posts.get(i).getIdentificador() == id) {
                post = this.posts.get(i).clone();
                existe = true;
            }
        }
        return post;*/

    //v
    public void comment(FBPost post, String comentario) {
        //temos que ir ao post que pretendemos e depois adicionar o comentario aos comentarios ja existentes
        post.getComentarios().add(comentario);
    }
    //vi
    public  void comment(int postid,String comentario) {
        boolean existe = false;

        for (int i = 0; i < this.posts.size() && !existe; i++) {
            //FBPost post = this.posts.get(i); e substituia se em baixo por post.getIdentificador() apenas //a cada passagem analisamos cada post
            if (this.posts.get(i).getIdentificador() == postid) {    //caso seja igual ao id que passamos na funçao
                existe = true;  //entao encontramos o post atraves do id, falta adicionar o comentario
                this.posts.get(i).adicionaComentario(comentario);
                this.posts.set(i,this.posts.get(i)); //e colocamos na posicao encontrada o post com os comentarios todos
            }               //criamos no FBPost esta funcao para auxiliar na resolucao
        }
    }

    //vii
    public void like(FBPost post){
        post.setLikes(post.getLikes()+1);
    }

    //viii
    public void like(int postid) {
        boolean existe = false;

        for(int i=0; i<this.posts.size() && !existe; i++){
            FBPost post = this.posts.get(i);
            if(post.getIdentificador() == postid){
                existe = true;
                post.setLikes(post.getLikes()+1);
                this.posts.set(i,post);
            }
        }
    }

    //ix vi noutras resoluções apenas
}
