package Ficha2;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.String; // para switch com char (alinea "a"...)

public class TestePrograma {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Insira o nº do exercício: ");
        int n_exercicio = input.nextInt();

        switch (n_exercicio) {
            case 1:
                int[] array1;
                int[] array2; // iniciamos tambem o array2 que servirá na alinea "c" da pergunta 1
                System.out.println("Digite a alinea:");
                String alinea = input.next();
                switch (alinea) { // dar input a letra minuscula (letra da alinea)
                    case "a":
                        System.out.println("Tamanho do array:");
                        array1 = new int[input.nextInt()];
                        System.out.println("Digite os numeros desse array");// passar elems para este array
                        for (int i = 0; i < array1.length; i++) {
                            array1[i] = input.nextInt();
                        }
                        System.out.println("O menor valor deste array é " + Array.minArray(array1));
                        break;
                    case "b":
                        System.out.println("Tamanho do array:");
                        array1 = new int[input.nextInt()];
                        System.out.println("Digite os numeros desse array");// passar elems para este array
                        for (int i = 0; i < array1.length; i++) {
                            array1[i] = input.nextInt();
                        }
                        System.out.println("Digite o indice inicial do novo array:");
                        int ind1 = input.nextInt();
                        System.out.println("Digite o indice final do novo array:");
                        int ind2 = input.nextInt();
                        System.out.println("A partir do array " + Arrays.toString(array1) + " obtemos o novo array comecando na posição" + ind1 + " e terminando na posição " + ind2 + ", " + Arrays.toString(Array.arrayEntreIndices(array1, ind1, ind2)));
                        //demos import ao java.utils.Array para poder converter o array numa string
                        break;
                    case "c":
                        System.out.println("Tamanho do array1:");
                        array1 = new int[input.nextInt()];
                        System.out.println("Digite os numeros desse array"); // passar elems para este array
                        for (int i = 0; i < array1.length; i++) {
                            array1[i] = input.nextInt();
                        }
                        System.out.println("Tamanho do array2:");
                        array2 = new int[input.nextInt()];
                        System.out.println("Digite os numeros desse array"); // passar elems para este array
                        for (int i = 0; i < array2.length; i++) {
                            array2[i] = input.nextInt();
                        }
                        System.out.println("Os elementos comuns entre o array1 e o array2 sao:" + Arrays.toString(Array.arrayElemscomuns(array1, array2)));
                        break;
                }
                break;
            case 2:
                Turma turma = new Turma(5, 5);
                int aluno, disciplina;
                boolean ex_2 = true;
                while (ex_2) { //desta forma iremos conseguir atualizar/inserir os dados relativos as notas bem como fazer qualquer outra função com estes novos dados
                    System.out.println("Prima o numero correspondente à operação que pretende efetuar.");
                    System.out.println("1 - Para atualizar a pauta");
                    System.out.println("2 - Calcular a soma das notas a uma determinada unidade curricular");
                    System.out.println("3 - Calcular a média das notas de um aluno");
                    System.out.println("4 - Calcular a média das notas de uma disciplina");
                    System.out.println("5 - Calcular a nota mais alta de qualquer disciplina");
                    System.out.println("6 - Calcular a nota mais baixa de qualquer disciplina");
                    System.out.println("7 - Devolver o array com as notas acima de um determinado valor");
                    System.out.println("8 - Mostrar a nota dos alunos");
                    System.out.println("9 - determinar a unidade curricular com a média mais elevada");
                    System.out.println("10 - Fechar o programa\n");
                    System.out.print("Sua opção: ");
                    int opcao = input.nextInt();
                    //2.1 tentar aranjar forma de guardar os valores apenas ao correr o codigo uma vez(já arranjei com um ciclo while assim nao será perdida a informacao das notas até fechar o ciclo)
                    switch (opcao) {
                        case 1:  //2.1 atualizar notas do array anterior que irá ser usado nas alineas seguintes em 2(obrigatório atualizar pois este nao tem valores inicialmente)
                            System.out.println("Atualização das notas da turma por alunos:");
                            for (int i = 0; i < turma.getAlunos(); i++) {
                                for (int j = 0; j < turma.getUcs(); j++) {
                                    System.out.println("Aluno: " + i);
                                    System.out.println("Disciplina: " + j);
                                    System.out.println("Nota: ");
                                    int nota = input.nextInt(); //colocamos a nota para o aluno e disciplina na posicao
                                    turma.atualizaPauta(i, j, nota); // colocamos no novo array a nota que alteramos
                                }
                            }
                            System.out.println("   0\t1\t2\t3\t4 ");    //desenhar matriz das notas para ser mais facil visualizar o resto das operações
                            System.out.println("  -------------------");
                            for (int i = 0; i < 5; i++) {   // percorre as linhas da matriz
                                int j = 0;
                                System.out.print(i + "| ");
                                while (j < 5) {
                                    //System.out.print("Nota[" + i + "][" + j + "]= " + turma.notasTurma[i][j]);
                                    System.out.print(turma.notasTurma[i][j] + "\t");
                                    j += 1;
                                }
                                System.out.println();
                            }
                            break;
                        case 2: //2.2
                            System.out.println("Qual a disciplina que pretende somar(Indique o indice da disciplina na matriz):");
                            disciplina = input.nextInt();
                            int somadasNotas = turma.somaNotasTurma(disciplina);
                            System.out.println("A soma total das notas a esta disciplina é: " + somadasNotas);
                            break;
                        case 3://2.3
                            double media;
                            System.out.println("Indique o indice do aluno:");
                            aluno = input.nextInt();
                            media = turma.mediaAluno(aluno);
                            System.out.println("A média do aluno é " + media);
                            break;
                        case 4:
                            System.out.println("Indique o indice da disciplina:");
                            disciplina = input.nextInt();
                            media = turma.mediaDisciplina(disciplina);
                            System.out.println("A média desta disciplina é " + media);
                            break;
                        case 5:
                            int notamax = turma.notaMaisAlta();
                            System.out.println("A nota mais alta de todas é " + notamax);
                            break;
                        case 6:
                            int notamin = turma.notaMaisBaixa();
                            System.out.println("A nota mais alta de todas é " + notamin);
                            break;
                        case 7:
                            System.out.println("Insira o valor da nota que pretende ser a minima a ser mostrada.");
                            int notas = input.nextInt();
                            System.out.printf("O array com as notas acima de %d é %s", notas, Arrays.toString(turma.notasAcimaDe(notas)));
                            System.out.println();
                            break;                                                          //metodo para dar print a um Array
                        case 8:
                            System.out.println(turma.Pauta());
                            break;
                        case 9:
                            System.out.println("A disciplina com a média mais alta é a disciplina " + turma.mediaDisciplinaMaisAlta());
                            break;
                        case 10:
                            ex_2 = false;
                            break;
                    }
                }
                break;
            case 3:
                System.out.print("Tamanho do array das datas: ");
                int tam = input.nextInt();
                Datas data = new Datas(tam);
                System.out.print("Alinea: ");
                int opcao1 = input.nextInt();
                int d, m, a;    // d->dia m->mes a->ano

                switch (opcao1) {
                    case 1:
                        System.out.print("Digite o dia: ");
                        d = input.nextInt();
                        System.out.print("Digite o mes: ");
                        m = input.nextInt();
                        System.out.print("Digite o ano: ");
                        a = input.nextInt();
                        data.insereData(LocalDate.of(a, m, d));
                        break;
                    case 2:
                        System.out.print("Digite o dia: ");
                        d = input.nextInt();
                        System.out.print("Digite o mes: ");
                        m = input.nextInt();
                        System.out.print("Digite o ano: ");
                        a = input.nextInt();
                        LocalDate datas = LocalDate.of(a, m, d);
                        System.out.print("A data mais proxima e" + data.dataMaisProxima(datas));
                        break;
                    case 3:
                        System.out.print(data.toStringg());
                        break;
                    default:
                        System.out.println("Alinea inexistente");
                        break;
                }
                break;
            case 4:
                int[] array;
                Array f = new Array();
                System.out.println("Digite o numero da alinea que pretende ser executada");
                int opcao3 = input.nextInt();
                switch (opcao3){
                    case 1:
                        System.out.println("Digite o tamanho do array");
                        array= new int[input.nextInt()];
                        System.out.println("Digite os elementos que pretende colocar no array:");
                        for(int i=0;i<array.length;i++){
                            array[i]=input.nextInt();
                        }
                        String inicial = Arrays.toString(array);        //print ao array como passamos
                        f.sort(array);
                        System.out.printf("O array %s depois de ordenado é: %s",inicial,Arrays.toString(array));
                        break;
                    case 2:
                        System.out.print("Digite o tamanho do array: ");
                        array = new int[input.nextInt()];
                        System.out.print("Digite os elementos que pretende colocar no array:");
                        for (int i = 0;i<array.length; i++) {
                            array[i] = input.nextInt();
                        }
                        System.out.print("Digite o elemento que está a procura: ");
                        int elem = input.nextInt();
                        int indice = f.procuraBinaria(array, elem);
                        if (indice == -1) {
                            System.out.printf("O elemento %d não existe no array %s",elem,Arrays.toString(array));
                        }
                        else {
                            System.out.printf("O elemento %d está no index %d do array ordenado %s",elem,indice,Arrays.toString(array));
                        }
                        break;
                }
            case 5:
                System.out.print("Quantas strings pretende inserir: ");
                int num_strings = input.nextInt();
                String[] strings = new String[num_strings]; //cria string vazia de tamanho dado por nós
                System.out.print("Insira as strings: ");
                for (int i = 0; i < num_strings; i++) {     //ciclo para inserir as strings
                    strings[i] = input.next();
                }
                StringArray mystring = new StringArray(strings);    //para aceder ao StringArray
                System.out.print("Digite a opcao que pretende realizar: ");
                switch (input.nextInt()) {
                    case 1:
                        System.out.printf("O array %s sem strings repetidas é %s",Arrays.toString(strings), Arrays.toString(mystring.semRepeticoes(strings)));
                        break;
                    case 2:
                        System.out.printf("A maior string inserida de %s é %s",
                                Arrays.toString(strings), mystring.maiorString());
                        break;
                    case 3:
                        System.out.printf("As strings que tem repetidos do array %s são %s",
                                Arrays.toString(strings), Arrays.toString(mystring.temRepetidas(strings)));
                        break;
                    case 4:
                        System.out.print("Insira a string(indice) que pretende ver o numero de ocorrencias: ");
                        int indice = input.nextInt();
                        System.out.printf("A string [%s] ocorre %d vez(es) no array %s",
                                strings[indice], mystring.StringOcorre(strings,indice), Arrays.toString(strings));
                        break;
                }
            break;
            case 6:
                System.out.println("!! EUROMILHÕES !!");
                Euromilhoes euro = new Euromilhoes(5,2);

                System.out.println("Insira a sua chave:");
                int[] numeros_utilizador = new int[5];
                int[] estrelas_utilizador = new int[2];

                //numeros do utilizador
                System.out.print("Numeros: ");
                for(int i=0;i<5;i++){
                    int numero = input.nextInt();
                    numeros_utilizador[i]=numero;
                }

                //estrelas do utilizador
                System.out.print("Estrelas: ");
                for(int i=0;i<2;i++){
                    int estrela = input.nextInt();
                    estrelas_utilizador[i]=estrela;
                }

                //gerar a chave e mostrá-la no ecrã
                euro.geraChave();
                euro.chaveSorteada();
                euro.comparaChaves(numeros_utilizador,estrelas_utilizador);
            break;
        }
    }
}


// caso quisesse atualizar fazia algo como um while e depois um caso que terminasse com esse ciclo quando o utilizador assim quisesse

// 1 se quiser atualizar o array que temos
// 2 se nao quiser avancamos para outras funcoes o que vai ficar é mais extenso porque temos sempre que copiar as funcoes todas para os 2 casos ver depois !!!!
//por isso ignoremos isso
