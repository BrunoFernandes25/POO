package Ficha2;

import java.util.Arrays;

public class StringArray {
    String[] strings;

    public StringArray(String[] strings) {
        this.strings = strings;
    }

    //5.1

    public String[] semRepeticoes (String[] strings) {
        int tam = strings.length;
        String[] final_array = Arrays.copyOf(strings,tam); //copia array recebido para o final

        for (int i= 0;i<tam;i++){
            int pos=i+1; //guardar esta posicao para depois inserir os elems
            int removidos=0; //servira para criar o novo array com o tamanho estritamente necessario
            for (int j=i+1;j<tam;j++){
                if(strings[i].equals(strings[j])){  //comparacao de uma string e a string seguinte
                    removidos++;
                }
                else {
                    final_array[pos++]=final_array[j];      //caso nao sejam iguai colocamos no novo array a string que se encontra na posicao comparada(em j)
                }
            }
            tam-=removidos;     //tamanho do array final a ser usado
            final_array= Arrays.copyOf(final_array,tam);
        }
    return final_array;
    }
    /*   ou de outra forma..

    public String[] semRepeticoes () {
        int tam = strings.length;
        String[] temp = new String[tam];
        int pos = 0;
        Arrays.sort(strings); //ordenemos as strings para assim ser mais facil comparar olhando apenas para a string seguinte
        for (int i=0;i<tam;i++){  //ciclo para percorrer o array das strings
            while(i<tam-1 && strings[i].equals(strings[i+1])){ // caso um caracter na string seja igual ao seguinte seguimos em frente pois queremos sem repeticoes
                i++;
            }
            temp[pos++]=strings[i];
        }
        String[] final_array = new String[pos];
        System.arraycopy(temp,0,final_array,0,pos);
        return final_array;
    }
    */

    /*
    public String[] stringDistinct() {
        return (String[]) Arrays.stream(this.stringArray).distinct().toArray();
    }   forma alternativa usando já stream
    */

    //5.2
    public String maiorString() {
        int max = strings[0].length();      //tamanho maximo será igual ao tamanho da primeira string
        String max_string = strings[0];
        for (int i = 1; i <strings.length; i++) {
            if (strings[i].length() > max) {
                max = strings[i].length();
                max_string = strings[i];
            }
        }
        return max_string;
    }

    // 5.3

    public String[] temRepetidas (String[] string) {
        int tam = strings.length;
        String[] temp = new String[strings.length];
        int pos = 0;

        for(int i=0;i<tam;i++){
            for(int j=i+1;j<tam;j++){
                if(strings[i].equals(strings[j])){
                    temp[pos++]= strings[i];
                }
            }
        }
        String[] final_array= new String[pos]; //pos é o numero de posicoes usadas no array temporario logo será o tamanho necessario para este array final
        System.arraycopy(temp,0,final_array,0,pos);
        return final_array;
    }
    /*  ou...

    public String[] temRepetidas() {
        String[] temp = new String[strings.length];
        int tam=strings.length;
        int pos = 0;
        Arrays.sort(strings);
        for (int i = 0; i <tam; i++) {
            boolean temRepetidos = false;
            while (i <tam - 1 && strings[i].equals(strings[i+1])) {
                i++;
                temRepetidos = true;
            }
            if (temRepetidos) temp[pos++] = strings[i];
        }
        String[] new_string = new String[pos];
        System.arraycopy(temp, 0, new_string, 0, pos);
        return new_string;
    }
    */

    // 5.4
    public int StringOcorre(String[] strings,int indice){
        int tam = strings.length;
        int ocorrencias=0;
        for (int i=0;i<tam;i++) {   //pegando em cada string do array(elem)
            if(strings[indice].equals(strings[i])){   //vemos se existe mais alguma ocorrencia dela no array
                ocorrencias+=1;         //existindo vamos incrementando as ocorrencias até obter o resultado final
            }
        }
        return ocorrencias;
    }
}
