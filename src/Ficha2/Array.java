package Ficha2;

import java.util.Arrays;
import java.lang.System;
/*
* Não é possível alterar a dimensão de um array depois de este ter sido criado.
* A única forma de fazer um array crescer é alocar um novo array e copiar para lá
* os elementos do array original.
* */

public class Array {
    //1.1
    public static int minArray (int[] array) {
        int min = Integer.MAX_VALUE; //usamos o MAX.VALUE para assim conseguir descobrir valores menores, caso contrario se fosse MIn.Value iria este ser semrpe o minimo e nao iria dar o minimo do array
        for(int elem :array) {
            if(elem < min){
                min = elem;
            }
        }
        return min;
    }
    //1.2
    public static int[] arrayEntreIndices (int[] array, int ind1, int ind2) {
        /*
        int[] noMeio = new int[indice2-indice1];  //guardamos o este valor que irá servir para o tamanho deste novo array

        for (i = 0; i < indice2-indice1; i += 1) {      entre o indice1 e indice2 vamos guardando num array resultado esses valores
            noMeio[i] = array[i + indice1];         //somamos entao ao indice o i de forma a obter os valores corretos deste novo array a partir do anterior
            out.print(noMeio[i]);
        }   ou entao uma maneira mais facil visto termos dado import a
        */
        int[] arraynovo = new int[ind2-ind1+1];        //criar um array para o qual iremos copiar os valores entre os indices
        System.arraycopy(array,ind1,arraynovo,0,ind2-ind1+1);
        return arraynovo;  //começa a analisar aqui(ind1) a partir do array original(array),copiará para o novo array(arraynovo) de tamanho (length: ind2-ind1+1) elems até atingir esse tamanho comecando por prencher a posicao zero (destPos:0)
    }

    //1.3
    public static int[] arrayElemscomuns (int[] array, int[] arrayy) {
        int[] comunstemp = new int[Math.max(array.length,arrayy.length)]; // tamanho do array tem no maximo o mesmo nº elems do array original maior
        int y=0, yy=0, tam=0;                       // y posicao no array e yy posicao no arrayy | tam servirá mais adiante para ir aumentando o tamanho do array final
        Arrays.sort(array);                        //convem ordenar para ser mais facil saber como avancar em ambos os arrays
        Arrays.sort(arrayy);                      // dar import ao java.util.Arrays
        while (y<array.length && yy<arrayy.length) {
            if(array[y]<arrayy[yy]) {           //caso em que o elem do array é menor que o do arrayy entao avancamos posicao y
                y++;
            }
            else if (arrayy[yy]<array[y]) {   //caso em que o elem do arrayy é menor que o do array entao avancamos posicao yy
                yy++;
            }
            else {
                if(tam == 0 || array[y] != array[tam-1] ) {       // no primeiro caso de serem iguais temos de ter a condicao(tam==0), nos restantes basta analizar se o elemento a comparar é diferente de outro já existente no array final
                    comunstemp[tam]=array[y];                   //para colocar um novo elemento
                    tam+=1;                                    // aumentamos o tamanho
                }
                y++;                                         //avancamos em ambos os arrays para comparar novos elementos
                yy++;
            }
        }
        int[] arrayfinal = new int[tam];
        System.arraycopy(comunstemp,0,arrayfinal,0,(tam));        //copiamos entao para o novoarray os elementos guardados no array temporario
        return arrayfinal;      //caso sejam ambas diferentes o que resultará do arraycopy será uma lista vazia [], ou seja, nao tem elems comuns as 2 strings
    }

    //4.1
    public void sort(int[] array) {
        Arrays.sort(array);
    }

    //4.2

    public int procuraBinaria(int[] array, int elem) {
        Arrays.sort(array);
        return Arrays.binarySearch(array, elem);
    }
}
