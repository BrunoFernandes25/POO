package Ficha2;

public class Turma {        //usaremos aqui o que já foi dado em aulas de POO
    int[][] notasTurma;
    private final int aluno;
    private final int disciplina;

    public Turma(int aluno, int disciplina) {
        notasTurma = new int[aluno][disciplina];
        this.aluno = aluno;
        this.disciplina = disciplina;
    }

    public void setNota(int aluno, int disciplina, int nota) {
        this.notasTurma[aluno][disciplina] = nota;
    }

    public int getNota(int aluno, int disciplina) {   //obter a nota de um determinado aluno
        return this.notasTurma[aluno][disciplina];
    }

    public int getAlunos() {
        return aluno;
    }

    public int getUcs() {
        return disciplina;
    }

    //2.1
    public void atualizaPauta(int aluno, int disciplina, int nota) {
        setNota(aluno, disciplina, nota);
    }

    //2.2
    public int somaNotasTurma(int disciplina) {
        int total = 0;
        for (int i = 0; i < aluno; i++) {
            total += notasTurma[i][disciplina]; //vamos a matriz e a cada aluno(i) somamos a nota da disciplina pretendida
        }
        return total;
    }

    //2.3
    public double mediaAluno(int aluno) {
        double soma = 0;
        for (int j = 0; j < getUcs(); j++) { // nao é preciso o getucs bastava por disciplina que corria sem problemas
            soma += notasTurma[aluno][j];
        }
        return soma / getUcs();
    }

    //2.4
    public double mediaDisciplina(int disciplina) {
        double soma = 0;
        for (int i = 0; i < getAlunos(); i++) {
            soma += notasTurma[i][disciplina];
        }
        return soma / getAlunos();
    }

    //2.5
    public int notaMaisAlta() {
        int max = Integer.MIN_VALUE; //o primeiro maximo começará no menor valor possivel
        for (int i = 0; i < getAlunos(); i++) {
            for (int j = 0; j < getUcs(); j++) {
                if (notasTurma[i][j] > max) {
                    max = notasTurma[i][j];
                }
            }
        }
        return max;
    }

    //2.6
    public int notaMaisBaixa() {
        int min = Integer.MAX_VALUE; //o primeiro maximo começará no menor valor possivel
        for (int i = 0; i < getAlunos(); i++) {
            for (int j = 0; j < getUcs(); j++) {
                if (notasTurma[i][j] < min) {
                    min = notasTurma[i][j];
                }
            }
        }
        return min;
    }
    //2.7
    /*
    public void notasAcimaDe(int nota){
        for (int i = 0; i < getAlunos(); i++) {
            for (int j = 0; j < getUcs(); j++) {
                if (notasTurma[i][j]<nota){
                    atualizaPauta(i,j,0);// a minha ideia inicial era retornar nao o array so com as notas mas sim a mtariz completa e colocar "0" onde as notas nao fossem maiores
                }
            }
        }
        System.out.println("   0   1   2   3   4 ");    //desenhar matriz das notas para ser mais facil visualizar o resto das operações
        System.out.println("  -------------------");
        for (int i = 0; i < 5; i++) {   // percorre as linhas da matriz
            int j=0;
            System.out.print(i + "| ");
            while(j<5){
                //System.out.print("Nota[" + i + "][" + j + "]= " + turma.notasTurma[i][j]);
                System.out.print(notasTurma[i][j]+"  ");
                j+=1;
            }
            System.out.println();
        }
    }
     */
    //2.7
    public int[] notasAcimaDe(int nota) {
        int[] temp= new int[aluno*disciplina]; // ou 25 que seria o tamanho maximo possivel
        int tam = 0;
        for (int i = 0; i < getAlunos(); i++) {
            for (int j = 0; j < getUcs(); j++) {
                if(notasTurma[i][j]> nota) {
                    temp[tam]=notasTurma[i][j];
                    tam+=1;
                }
            }
        }
        int[] arrayfinal = new int[tam]; // sabendo agora o tamanho que foi preenchudo no array temporario, cria se um array final e passamos os valores do temporario
        System.arraycopy(temp,0,arrayfinal,0,tam);
        return arrayfinal;
    }
    //2.8
    public String Pauta() {
        String notas = "";
        for (int i = 0; i < getAlunos(); i++) {
            notas += "As notas do aluno" + i + " são [";
            for (int j = 0; j < getUcs()-1; j++) {
                notas += getNota(i, j) + ",";
            }
            notas += getNota(i,getUcs()-1) + "]\n";
        }
        return notas;
    }

    public int mediaDisciplinaMaisAlta () {
        double max= Integer.MIN_VALUE;
        int ind=0;
        for(int i= 0;i<getUcs();i++){
            if(mediaDisciplina(i)>max) {
                max= mediaDisciplina(i); // mas nos nao queremos o valor mas sim o indice da nota
                ind=i;
            }
        }
        return ind;
    }
}