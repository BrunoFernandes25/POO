package Ficha1;

import java.time.LocalDateTime;

public class Ficha1 {
    public double celsiusParaFarenheit(double graus) {
        double tempfarenheit;   // double por causa da formula
        tempfarenheit= graus*(1.8)+32.00;
        return tempfarenheit;
    }

    public int maximoNumeros (int a,int b) { // ou usava a classe Math e fazia Math.max
        int maximo;
        if (a<b) {
            maximo=b;
        }
        else maximo=a;
        return maximo;
    }

    public int minimoNumeros (int a,int b) { // ou usava a classe Math e fazia Math.min
        int minimo;
        if (a<b) {
            minimo=a;
        }
        else minimo=b;
        return minimo;
    }

    public double media (int a,int b) {
        return ((double)a+b)/2; //daria erros double(a+b)/2
    }

    public String criaDescricaoConta (String nome, double saldo) {
        return "Nome:" + nome +", saldo: " + saldo;
    }
    public double eurosParaLibras (double valor, double taxaConversao) {
        return valor* taxaConversao; // valor(€) * custo de converter uma moeda dará o nosso novo valor em libras
    }

    public String decrescenteEmedia (int a, int b) {
        return "Maior número:"+maximoNumeros(a,b) + ", menor número:" + minimoNumeros(a,b) + ", média:" + media(a,b); //usei a funcao maximo ja feita noutra questao e criei a minimos que é identica
    }

    public double fatorial (int num) {
        double fact = 1; // nunca é menor que um o fatorial de qualquer numero
        for (int i=1;i<=num;i++) fact *= i;
        return fact;
    }

    public long tempoGasto () {
        long temp = System.nanoTime();
        fatorial(5000);
        return System.nanoTime() - temp; //tempo que demorou, final-inicial
    }
}
