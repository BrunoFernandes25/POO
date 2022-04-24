package Ficha4;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class TestePrograma {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Ex: ");
        int ex = input.nextInt();

        switch(ex){
            case 1:
                ArrayList<String> stack= new ArrayList<String>();
                Stack s = new Stack(stack);
                /*
                 ou apenas -> Stack s = new Stack(); e nao davamos import ao ArrayList
                */
                s.push("Ola");
                s.push("tudo");
                s.pop();
                s.push("tá tudo");
                s.push("bem?");
                System.out.println("Palavra no topo da stack: " + s.top());
                System.out.println("A stack está vazia? : " + s.empty());
                System.out.println(("Tamanho da stack: " + s.length()));

                System.out.println(s.toString());
                break;
            case 2:
                EncEficiente encomenda = new EncEficiente("Manel",123123123,"Rua do Manel",50, LocalDateTime.now(), new ArrayList<LinhaEncomenda>());
                System.out.println("Encomenda:" + encomenda.toString());

                LinhaEncomenda l1 = new LinhaEncomenda();
                //System.out.println(l1.toString());
                LinhaEncomenda l2 = new LinhaEncomenda("11111111","Batata palha",2,3,6,15);
                //System.out.println(l2.toString());
                LinhaEncomenda l3 = new LinhaEncomenda("21212121","Coca-cola",1.5,5,13,30);
                LinhaEncomenda l4 = new LinhaEncomenda("10101001","Pao de forma",0.99,3,6,0);
                encomenda.adicionaLinha(l2);
                //System.out.println(l2.toString());
                encomenda.adicionaLinha(l3);
                encomenda.adicionaLinha(l4);
                encomenda.removeProduto("11111111");
                System.out.println(encomenda.toString());

                System.out.printf("Valor total da encomenda: %.2f\n",encomenda.calculaValorTotal());
                System.out.printf("Valor do desconto total da encomenda: %.2f\n",encomenda.calculaValorDesconto());
                System.out.println("Numero total de produtos na encomenda: " + encomenda.numeroTotalProdutos());
                System.out.println("Existe o produto com a referencia 21212121 ?: "+ encomenda.existeProdutoEncomenda("21212121"));
                System.out.println("Existe o produto com a referencia 10101010 ?: "+ encomenda.existeProdutoEncomenda("10101010"));
                break;
            case 4:
                FBPost p1 = new FBPost(10,"Zeze",LocalDateTime.now(),"Forecse",600,new ArrayList<String>());
                FBPost p2 = new FBPost(10,"Zeze",LocalDateTime.now(),"#makemoney",524,new ArrayList<String>());
                FBPost p3 = new FBPost(25,"Jony",LocalDateTime.now(),"Babydelas",35,new ArrayList<String>());
                FBPost p4 = new FBPost(30,"Tete",LocalDateTime.now(),"NFtês",1000,new ArrayList<String>());

                ArrayList<FBPost> posts = new ArrayList<>();
                posts.add(p1);
                posts.add(p2);
                posts.add(p3);
                posts.add(p4);

                FBFeed feed = new FBFeed(posts);

                System.out.println(feed.nrPosts("Zeze"));

                feed.comment(10,"Ensina ai Zeze tou farto de estudar.");
                feed.comment(10,"Zeze quanto é o custo para entrar? Quero ser como tu, Another day, another house");
                feed.comment(25,"Papy delas!");
                feed.comment(30,"Bro 5 da matina já sabes.");


                System.out.println(feed.postsOf("Zeze"));
                System.out.println(feed.postsOf("Jony"));
                feed.like(25);
                System.out.println("Likes do Jony: " + p3.getLikes());
                System.out.println(feed.postsOf("Tete"));

                int likkes = p1.getLikes() + p2.getLikes();
                for(int i = 0; i<200;i++){
                    feed.like(10);  // coloca like no primeiro post por default
                    likkes++;
                }
                System.out.println("Likes do primeiro post do Zeze: " + p1.getLikes());
                System.out.println("Likes dos dois posts do Zeze: " + likkes);
                break;
        }
    }

}
