package Ficha2;

import java.util.Random;

public class Euromilhoes {
    private int[] numeros;
    private int[] estrelas;

    public Euromilhoes(int nums, int stars){    //sem isto iria dar erro porque os vetores numeros e estrelas estariam a null
        numeros = new int[nums];
        estrelas = new int[stars];
    }


    public boolean existeNum (int[] v, int num) {
        for (int i = 0;i<v.length;i++) {
            if(v[i]==num) {
                return true;
            }
        }
        return false;
    }

    public void geraChave () {
        Random r = new Random();
        int num_random;
        int star_random;

        for (int i=0;i<5;i++){      //Números
            num_random = r.nextInt(50)+1;  //limite dos numeros é 50, e o que nos queremos é entre 1-50 logo somamos 1
            while(existeNum(numeros,num_random)){
                num_random = r.nextInt(50)+1;
            }
            this.numeros[i]=num_random; //guarda se no vetor numeros os numeros à medida que se vao obtendo
        }
        for (int i=0;i<2;i++){      //Estrelas
            star_random = r.nextInt(9)+1;  //limite das estrelas é 9, e o que nos queremos é entre 1-9 logo somamos 1
            while(existeNum(estrelas,star_random)){
                star_random = r.nextInt(9)+1;
            }
            this.estrelas[i]= star_random;      //guarda se no vetor estreals as estrelas à medida que se vao obtendo
        }
    }

    public  void chaveSorteada() {
        System.out.print("Numeros: ");
        for (int i=0;i<numeros.length;i++){
            System.out.print(numeros[i] + " ");
        }
        System.out.print("\n");
        System.out.print("Estrelas: ");
        for (int i = 0;i<estrelas.length;i++){
            System.out.print(estrelas[i] + " ");
        }
        System.out.println("\n");
    }

    public void comparaChaves (int[] num_utilizador, int[] estrelas_utilizador) {
        int num_certos = 0;
        int estrelas_certas=0;

        for (int i = 0;i<numeros.length;i++){        // ciclo para ver quantos numeros estao corretos
            if(this.numeros[i] == num_utilizador[i]) {
                num_certos+=1;
            }
        }

        for (int i = 0;i<estrelas.length;i++){      // ciclo para ver quantas estrelas estao corretas
            if(this.estrelas[i] == estrelas_utilizador[i]) {
                estrelas_certas+=1;
            }
        }

        System.out.println("Na sua chave acertou em " + num_certos + "numeros e em "+ estrelas_certas + "estrelas.");
    }

}
