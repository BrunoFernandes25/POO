package Ficha1;

import java.util.Scanner;
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
                int e= Integer.parseInt(args[0]); // Integer.valueOf(arg[0]),ver novamente mais tarde !! nao esquecer colocar em cima no martelo valor que queremos passar para calular no Program arguments
                System.out.printf("o fatorial de %s é %.0f", args[0], f.fatorial(e));
                break;
            case 7:
                System.out.println("O tempo gasto de calculo do fatorial 5000 foi de:"+ ((double)f.tempoGasto())/1000000 + " milisegundos"); //pegamos no valor dado em nanosegundos/1000000= valor em milisegundos, e usamos o double pois caso contrario daria sempre 0 visto as grandezas dos valores associados
                break;
            default:
                System.out.println("Essa pergunta não existe tente outro numero !!");
        }
    }
}