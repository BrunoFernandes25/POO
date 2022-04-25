package Ficha5;

import java.util.Map;
import java.util.TreeMap;

public class TestePrograma {
    public static void main (String[] args){
        Parque p1 = new Parque();
        Map<String,Estacionamento> lugar= new TreeMap<String,Estacionamento>();
        Parque p2 = new Parque("Parque Vitoria",lugar);

        lugar.put("69AA96",new Estacionamento("69AA96","Nelo",35,true));
        lugar.put("88JJ88",new Estacionamento("88JJ88","Boçe",88,false));
        p2.adicionaLugar(new Estacionamento("55XX55","Nikito",25,true));
        p2.setLugares(lugar);
        System.out.println(p1);
        System.out.println(p2);

        System.out.println(p2.minAtribuidosTotal());
        System.out.println(p2.minAtribuidosTotal2());

        System.out.println("\nExiste algum lugar com a matricula 69AA96? " + p2.existeLugar("69AA96"));
        System.out.println("Existe algum lugar com a matricula 69AA90? " + p2.existeLugar("69AA90"));

        p2.alteraTempo("55XX55",100);

        //System.out.println(p2.minAtribuidosTotal2());

        System.out.println("\nInformação do carro com matricula '88JJ88': " + p2.informacao("88JJ88"));
        System.out.println("\n" + p2.copiaLugares());

        p2.removeLugar("69AA96");

        System.out.println("\nLugares ocupados atualmente: \n" + p2.lugaresOcupados());

        System.out.println("Lugares ocupados com tempo superior a 75: \n" + p2.TempoMaiorque(75));

    //------------------------------------------------------------------------------------------------------------------
        System.out.println("-----------------------------------------------------------");

        Map<String,Aluno> aluno= new TreeMap<String,Aluno>();
        TurmaAlunos t = new TurmaAlunos(aluno,"LCC","POO");

        Aluno a = new Aluno("1111",18,"Ze","LCC");
        t.insereAluno(a);
        Aluno b = new Aluno("2222",10,"Manel","LCC");
        t.insereAluno(b);
        Aluno c = new Aluno("3333",15,"Lurdes","LCC");
        t.insereAluno(c);

        t.removeAluno("2222");

        System.out.println("Nome turma: " + t.getNomeTurma());
        System.out.println("Uc: " + t.getUc());
        System.out.println("Total alunos na turma de LCC em POO: " + t.qtsAlunos());
        System.out.println("\nTurma ordenada: " + t.alunosOrdemAlfabetica());
        System.out.println("\nTurma ordenada por numero decrescente de aluno: \n" + t.alunosOrdemDecrescenteNumero());

        System.out.println("\nCodigos alunos: " + t.todosOsCodigos());
    //------------------------------------------------------------------------------------------------------------------


    }
}
