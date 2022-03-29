package Ficha3;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class TestePrograma {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Pergunta número: ");
        int pergunta = input.nextInt();
        switch (pergunta){
            case 1:
                Circulo c1 = new Circulo();
                System.out.println("Valores do circulo default: x = 0 || y = 0 || raio = 0 ");
                System.out.println(c1.toString());

                double x = 1;
                double y = 2;
                double raio = 3;

                Circulo c2 = new Circulo(x,y,raio);
                System.out.print("Valores do circulo passado: ");
                System.out.println(c2.toString());

                Circulo c3 = new Circulo(c2).clone();
                System.out.print("Valores do circulo clonado: ");
                System.out.println(c3.toString());

                c3.setX(2);
                c3.setY(4);
                System.out.println("Atualiza a coordenada x e y para 2 e 4, respetivamente.");
                System.out.println(c3.toString());
                c2.setRaio(4);
                System.out.println("Atualiza o raio de c2 para 4.");
                System.out.println(c2.toString());
                c1.alteraCentro(5,5);
                System.out.println("Altera o centro de c1 para (5,5).");
                System.out.println(c1.toString());

                System.out.printf(" A área de c2 é: %f\n", c2.calculaArea());
                System.out.printf("E o perimetro é: %f\n", c2.calculaPerimetro());
                break;
            case 2:
                String alinea = input.next();
                //t1
                System.out.println("Caracteristicas default de um telemóvel:");
                Telemovel t1 = new Telemovel();
                System.out.println(t1.toString());   //telemovel por omissao

                //t2
                String[] sms = {"Ola mene!","Tas pronto pah?", "F*d@sse despacha-te!!!"};
                String[] apps = {"Imsta,Fassebuk,Tuiter,Yutube,Gmaile"};
                Telemovel t2 = new Telemovel("Samchunga","Galaxia530",20,50,3,500,sms,5000,10000,15000,10000,500,5,apps); // cada App equivale a 1000Mb
                System.out.println(t2.toString());

                // t2:  armtotal(Fotos e Apps) = 15000 || armOcupado = 10000 || sobra apenas 5000 para instalações

                t2.instalaApp("SnépChat",1000);
                t2.instalaApp("Tuitxh",2000);
                t2.instalaApp("Rédite", 1000);
                t2.instalaApp("Pakamone", 800);
                t2.instalaApp("Clash roiale",200);
                t2.instalaApp("Candi cruch", 100);      //nao irá ser instalado

                t2.recebeMsg("Bora pah tou cá embaixo. Tens 5 minutos oupa !!");    //supostamente esta é maior mas visto já nao existir espaço nem esta nem a seguinte irao ser recebidas no telemovel
                t2.recebeMsg("Sigaaaaaa !!");

                //t3
                Telemovel t3 = new Telemovel(t2).clone();
                System.out.println(t3.toString());
                t3.setArmazenamentoApps(12500);
                t3.setArmazenamentoFotosApps(17500);
                t3.setDisplayY(100);
                System.out.println(t3.toString());

                switch (alinea){
                    case "a":
                        boolean b = t2.existeEspaco(5000);
                        System.out.println("Existe espaço para instalar uma aplicacao com tamanho 5000Mb [True/False]?  "+ b);
                        b = t2.existeEspaco(6000);
                        System.out.println("E com  um tamanho de 6000Mb [True/False]?  "+ b);
                        break;
                    case "b":
                        /*              coloquei fora do switch case para assim fazer a média das aplicacoes do telemovel ja com estas instaladas
                        t2.instalaApp("SnépChat",1000);
                        t2.instalaApp("Tuitxh",2000);
                        t2.instalaApp("Rédite", 1000);
                        t2.instalaApp("Pakamone", 800);
                        t2.instalaApp("Clash roiale",200);
                        t2.instalaApp("Candi cruch", 100);      //nao irá seer instalado
                        */

                        //System.out.println("Num. apps instaladas "+ t2.getNumAppsInstaladas()); estao no total instaladas 10 aplicacoes visto a 11 nao entrar
                        String[] app = t2.getNomeAppsInstaladas(); //array string com nomes das apps ja instaladas + as que foram instaladas com sucesso, pois a funcao instalaapp ja adiciona o nome como incrementa o tamanho do array
                        //System.out.println("tam " + app.length); tamanho da string app = 5
                        System.out.println("-------------------Apps instaladas no seu telemóvel atualmente -------------------\n");
                        for (int i = 0; i<app.length;i++){
                            if(i<app.length-1) {
                                System.out.print(app[i]+",");
                            }
                            else {
                                System.out.println(app[i]+";");
                            }
                        }
                        break;
                    case "c":
                        String[] msg = t2.getMensagens();
                        System.out.println("----------- Mensagens ---------------\n");

                        for (String s : msg) {
                            System.out.println(s);
                        }
                        break;
                    case "d":
                       double tamanhoMedio = t2.tamMedioApps();
                       System.out.println("O tamanho médio das aplicações do seu telemóvel é: " + tamanhoMedio);
                       break;
                    case "e":
                        String maior = t2.maiorMsg();
                        System.out.println("A mensagem com maior tamanho (nº de caracteres) é: " + maior);
                        break;
                    case "f":   //corrigir depois erro de print no ciclo for/ rever funcao removeApp
                        t2.removeApp("SnépChat",1000);
                        t2.removeApp("Rédite",1000);

                        String[] aps = t2.getNomeAppsInstaladas();
                        System.out.println("-------------- Aplicacoes Instaladas ---------------\n");
                        for(int i = 0; i<aps.length;i++) {
                            System.out.println(aps[i]);
                        }
                        break;
                }
            case 3:
                String alineaa = input.next();
                char[] conteudo = new char[20];
                String[] comentarios = {"Maior youtuber","És um lixo","Haters vão haitar"};
                Youtube v1 = new Youtube();
                System.out.println("Video(1)\n" + v1.toString());
                Youtube v2 = new Youtube("Mano ganda video!",conteudo, LocalDateTime.now(),1024,50,30,comentarios,5,1000);
                Youtube v3 = new Youtube(v2).clone();
                System.out.println("Video(2/3)\n" + v3.toString());

                switch (alineaa) {
                    case "b":
                        v2.insereComentario("Concordo contigo!!");
                        v2.insereComentario("Nao percebes nada disto pah.");

                        System.out.println("---------- Comentários ---------------");
                        String[] coments = v2.getComentarios();
                        for (int i  = 0; i< coments.length;i++) {
                            System.out.println(coments[i]);
                        }
                        System.out.println("--------------------------------------");
                        break;
                    case "c" :
                        System.out.println("Video foi publicado há " + v2.qtsDiasDepois() + " dias.");
                        break;
                    case "d" :
                        v2.thumbsUp();
                        int likes = v2.getLikes();
                        System.out.println("Likes:" + likes);
                        break;
                    case "e":
                        System.out.println(v2.processa());
                        break;
                }
                break;
            case 4:
                Lampada l = new Lampada(); // desligada
                LocalDateTime inicioOn = LocalDateTime.now();
                l.lampON();
                System.out.println("Estado: " + l.getModo());
                LocalDateTime fimOn = LocalDateTime.now();
                long timeOn = ChronoUnit.MILLIS.between(inicioOn, fimOn);
                LocalDateTime inicioEco = LocalDateTime.now();
                l.lampECO();
                System.out.println("Estado: " + l.getModo());
                LocalDateTime fimEco = LocalDateTime.now();
                long timeEco = ChronoUnit.MILLIS.between(inicioEco, fimEco);
                l.lampOFF();
                System.out.println("Estado: " + l.getModo());
                long consumo = timeOn * 10 + timeEco * 5;
                System.out.println(l.totalConsumo() + " == " + consumo);
                System.out.println(l.periodoConsumo());
                break;
            case 5:
                Futebol f1 = new Futebol();
                Futebol f2 = new Futebol(f1).clone();
                Futebol f3 = new Futebol(Futebol.Estado.Iniciar,0,0,"VSC","SCB");
                System.out.println("Estado do jogo: " + f3.getEstado());
                f3.startGame();
                f3.goloVisitante();
                f3.goloVisitado();
                f3.goloVisitado();
                f3.goloVisitado();
                f3.goloVisitante();
                System.out.println("Vitória: " + f3.getGolosCasa());
                System.out.println("Braga: " + f3.getGolosFora());
                f3.endGame();
                System.out.println("Estado do jogo: " +  f3.getEstado());
                System.out.println("Resultado Final: " + f3.resultadoActual());
                break;
            case 7:
                LinhaEncomenda l1 = new LinhaEncomenda();
                LinhaEncomenda l2 = new LinhaEncomenda(l1).clone();
                LinhaEncomenda l3 = new LinhaEncomenda("1000","Chipss",0.90,2,6,10);

                System.out.println(l1.toString());
                System.out.println(l2.toString());
                System.out.println(l3.toString());

                System.out.println("O valor da linha de Encomenda l3 é: " + l3.calculaValorLinhaEnc());
                System.out.println("Preço já com um desconto de: " + l3.calculaValorDesconto());
                break;
            case 8:
                Encomenda enc1 = new Encomenda();
                Encomenda enc2 = new Encomenda(enc1).clone();
                Encomenda enc3 = new Encomenda("Jose",1234,"Rua padre V",127,LocalDateTime.now());
                LinhaEncomenda l5 = new LinhaEncomenda("1111", "Oreu", 0.99, 200, 6, 10);
                LinhaEncomenda l6 = new LinhaEncomenda("2222", "Chips", 0.80, 100, 6, 5);
                LinhaEncomenda l7 = new LinhaEncomenda("3333", "Iogurte Mimosa", 1.10, 50, 13, 15);
                LinhaEncomenda l8 = new LinhaEncomenda("4444", "Pao", 0.30, 1000, 23, 0);
                enc3.adicionaLinha(l5);
                enc3.adicionaLinha(l6);
                enc3.adicionaLinha(l7);
                enc3.adicionaLinha(l8);

                System.out.println("Valor total da Encomenda: " +enc3.calculaValorTotal());
                System.out.println("Valor desconto da Encomenda: " + enc3.calculaValorDesconto());
                System.out.println("Numero total de produtos da Encomenda: " + enc3.numeroTotalProdutos());
                System.out.println("Existe encomenda com o código 1000?: " + enc3.existeProdutoEncomenda("1000"));
                System.out.println("Existe encomenda com o código 1111?: " + enc3.existeProdutoEncomenda("1111"));

                System.out.println("---------------Produtos---------------------");
                for (LinhaEncomenda e : enc3.getLinhasEncomenda()){
                    System.out.println("Produto: " + e.getDescricao());
                }
                System.out.println("--------------------------------------------");
                break;
            default:
                System.out.println("Não existe essa pergunta, tente outro número !!");
        }
    }
}
