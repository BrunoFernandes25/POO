package Ficha1;

import java.util.Scanner;
import java.util.Arrays;

public class TestePrograma { //main e tudo o que precisamos para correr as funcoes de Ficha1
    public static void main(String[] args) {
        Ficha1 f = new Ficha1(); //f funcao
        Scanner input = new Scanner(System.in); //dar import ao java.util.Scanner
        System.out.print("Escolha o exercicio:");
        int n_exercicio = input.nextInt(); //para ler numero do scanner input

        switch (n_exercicio){
            case 1:
                System.out.println("Insira a temperatura em graus Celsius:");
                double tempcelsius= input.nextDouble();
                double tempfarenheit= f.celsiusParaFarenheit(tempcelsius);
                System.out.println("A temperatura agora em graus Farenheit é: "+ tempfarenheit);
                break;
            case 2:
                System.out.print("Insira o primeiro número:");
                int a =input.nextInt();  //vai ao scanner input que nos serve para dar inputs ao que queremos
                System.out.print("Insira o segundo número:");
                int b = input.nextInt();
                int maximo = f.maximoNumeros(a,b);
                System.out.println("O máximo é: " + maximo);
                break;
            case 3:
                System.out.println("Nome da conta: ");
                /*input.nextLine();
                String nome = input.nextLine(); input.nextLine so funciona se antes  colocar o que esta em cima*/
                String nome = input.next();
                System.out.println("Insira o saldo da conta: ");
                double saldo = input.nextDouble(); //(12,2 e nao 12.2 cuidado)
                String conta = f.criaDescricaoConta(nome,saldo);
                System.out.println(conta); //dai ter criado uma String conta para agora dar print a essa String
                break;
            case 4:
                System.out.println("Valor em euros:");
                double euros = input.nextDouble();
                System.out.println("Qual o valor da taxa de conversao em vigor atualmente?:");
                double taxa = input.nextDouble();
                System.out.printf("O valor %.2f em euros corresponde a %.2f libras",euros,f.eurosParaLibras(euros,taxa)); //%.2f duas casas decimais (ex:50,25€)
                break;
            case 5:
                System.out.println("Digite dois valores:");
                int c = input.nextInt(); // nao se pode repetir o nome das variaveis, nao podia por int a nem b pois ja havia definido em cima
                int d = input.nextInt();
                String decmedia = f.decrescenteEmedia(c,d);
                System.out.println(decmedia);
                break;
            case 6:
                System.out.println("Digite um número e veja o seu fatorial:");
                int h= Integer.parseInt(args[0]); // Integer.valueOf(arg[0]),ver novamente mais tarde !! nao esquecer colocar em cima no martelo valor que queremos passar para calular no Program arguments
                System.out.printf("o fatorial de %s é %.0f", args[0], f.fatorial(h));
                break;
            case 7:
                System.out.println("O tempo gasto de calculo do fatorial 5000 foi de:"+ ((double)f.tempoGasto())/1000000 + " milisegundos"); //pegamos no valor dado em nanosegundos/1000000= valor em milisegundos, e usamos o double pois caso contrario daria sempre 0 visto as grandezas dos valores associados
                break;
            case 8:
                System.out.println("Calcular o dia da semana. Escreva o dia...");
                int dia = input.nextInt();
                System.out.println("...o mês...");
                int mes = input.nextInt();
                System.out.println("...e o ano.");
                int ano = input.nextInt();
                System.out.println("O dia da semana foi: " + Ficha1_1.diaSemana(ano,mes,dia));  // em vez de criar Ficha1_1 f = new Ficha1_1(); que iria dar warning preferi fazer assim
                break;
            case 9: // falta agora conseguir transformar os dias em arrays e...
                int[] dia1 = new int[4];    //inicializemos os arrays do dia1
                int[] dia2 = new int[4];    // e do dia2
                String[] splitdia1;         //pensando em passar os dados como dd/hh/mm/ss
                String[] splitdia2;
                String data;
                int i;
                //stringdia1
                System.out.println("Digite a primeira data(Formato pretendido -> dd/hh/mm/ss): ");
                data = input.next(); // ler string da data passada
                splitdia1 = data.split("/"); // pensemos em algo que ao ler a string introduzida "parta" a cada vez que veja "/", criando assim um array que contem elementos entre cada "/"
                //stringdia2
                System.out.println("Digite a segunda data(Formato pretendido -> dd/hh/mm/ss): ");
                data = input.next(); // ler string da data passada
                splitdia2 = data.split("/");

                for (i=0;i<4;i++){
                    dia1[i] = Integer.parseInt(splitdia1[i]); // passamos os elementos de string para integer (ex: se passasemos a string "25/3/40/22" iria ser partido em "25","3","40","22", e agora iriamos passar todos os elems. de "num" para num)
                    dia2[i] = Integer.parseInt(splitdia2[i]); // e o mesmo para a string2 que passarmos
                }
                System.out.println(Ficha1_1.somaDatas(dia1,dia2)); // por fim chamamos a função que irá calcular os valores agora no array como integers.
                break;
            case 10:
                System.out.println("Quantas classificações pretende passar? Escreva-as separadas por barras.");        //vamos primeiro saber quantas classificacoes foram passadas, para criar assim um array desse tamanho
                String pautas;  // Iniciamos uma string para depois lhe passarmos as notaas pretendidas
                pautas = input.next(); // lemos a linha completa das pautas(classificacoes)
                String[] splitpauta;    // Criamos um array para colocar as respetivas notas ex: ["5","13","20","4"]
                splitpauta = pautas.split("/");        // usamos split para contar nº de classificacoes para criar, tentei com espacos mas contava mal nao percebi porque mas ok..
                int[] avaliacoes = new int[splitpauta.length]; //criamos entao o nosso array das classificacoes com o tamanho pretendido
                for(i = 0; i< splitpauta.length;i++) {
                        avaliacoes[i] = Integer.parseInt(splitpauta[i]);  // convertemos o numero "x" em x nao esquecer
                }
                avaliacoes = Ficha1_1.classificacoes(avaliacoes);//chamamos a funcao
                for (i = 0;i< avaliacoes.length-1;i++) {    //vamos dar print ao resultado da funçao
                    System.out.printf("%d ", avaliacoes[i]);
                }
                System.out.printf("%d\n", avaliacoes[i]); // para dar print ao ultimo valor acho
                break; //corre mas nao deixa ler nada algo nao esta correto e so correu recorrendo ao try catch
            case 11:
                System.out.println("Quantas temperaturas pretende passar? Escreva as separadas por barras (minimo 2 temperaturas).");
                String graus;
                graus = input.next();
                String[] splitgraus;
                splitgraus = graus.split("/");
                int[] temp = new int[splitgraus.length];
                for (i=0;i< splitgraus.length;i++) {
                    temp[i]=Integer.parseInt(splitgraus[i]);
                }
                // temperaturas tem de ser no minimas duas por isso
                System.out.println(Ficha1_1.temperaturas(temp));
                break;
            case 12:
                System.out.println("Calcular horas de vida. Escreva o ano seguido do mês e do dia de nascimento(aa/mm/dd)");
                String nascimento;
                nascimento=input.next();
                String[] dataa;
                dataa = nascimento.split("/");
                int [] dias= new int[dataa.length];
                for(i=0;i< dataa.length;i++){
                    dias[i]=Integer.parseInt(dataa[i]);
                }
                ano= dias[0];
                mes= dias[1];
                dia= dias[2];
                System.out.println(Ficha1_1.calculaidade(ano,mes,dia));
                break;
                default:
                System.out.println("Essa pergunta não existe tente outro numero !!");
        }
    }
}