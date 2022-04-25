package Ficha5;

import java.util.*;
import java.util.stream.Collectors;

public class TurmaAlunos {
    private Map<String,Aluno> aluno;
    private String nomeTurma;
    private String uc;

    public TurmaAlunos(Map<String,Aluno> aluno, String nome, String codigoUC){
        this.aluno = aluno.values().stream().collect(Collectors.toMap(Aluno :: getNumero,Aluno :: clone));
        this.nomeTurma = nome;
        this.uc = codigoUC;
    }

    public TurmaAlunos(TurmaAlunos t) {
        this(t.aluno, t.nomeTurma, t.uc);
    }

    public Map<String,Aluno> getAluno() {
        return this.aluno.values().stream().collect(Collectors.toMap(Aluno::getNumero, Aluno::clone));
    }                                                         //Map<   String        ,     Aluno>
    public void setAluno(Map<String,Aluno> al){
        this.aluno = al.values().stream().collect(Collectors.toMap(Aluno::getNumero,Aluno::clone));
    }
    public String getNomeTurma(){
        return this.nomeTurma;
    }
    public void setNomeTurma(String tN){
        this.nomeTurma = tN;
    }
    public String getUc(){
        return this.uc;
    }
    public void setUc(String codUc){
        this.uc = codUc;
    }

    public TurmaAlunos clone(){
        return new TurmaAlunos(this);
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || this.getClass() != o.getClass()){
            return  false;
        }
        TurmaAlunos turma = (TurmaAlunos) o;
        return this.aluno.equals(turma.aluno) && this.nomeTurma.equals(turma.nomeTurma) && this.uc.equals(turma.uc);
    }

    public int compareTo(Object o){
        if(o.getClass() != this.getClass()) return 1;
        TurmaAlunos turma = (TurmaAlunos) o;
        if(!this.aluno.equals(turma.aluno)){
            return this.aluno.size() - turma.aluno.size();
        }
        if(!this.nomeTurma.equals(turma.nomeTurma)){
            return this.nomeTurma.compareTo(turma.nomeTurma);
        }
        if(!this.uc.equals(turma.uc)){
            return this.uc.compareTo(turma.uc);
        }
        return 0;
    }

    public void insereAluno (Aluno a) {
        this.aluno.put(a.getNumero(),a.clone());
    }

    public Aluno getAluno (String codAluno) {   //pensar que pode nao existir logo usemos getOrDefault que caso exista retorna nos o valor existente senao retorna o que nos indicarmos, neste caso indicamos null
        return this.aluno.getOrDefault(codAluno,null).clone();
    }

    public void removeAluno (String codAluno) {
        this.aluno.remove(codAluno);
    }

    public Set<String> todosOsCodigos() {
        return this.aluno.keySet(); //retorna o conjunto de chaves
    }

    public int qtsAlunos() {
        return this.aluno.size();
    }

    public Collection<Aluno> alunosOrdemAlfabetica() {
        return this.aluno.values().stream().sorted().collect(Collectors.toList());
    }

    public Set<Aluno> alunosOrdemDecrescenteNumero() {  //copiei
        SortedSet<Aluno> s = new TreeSet<Aluno>((a,b)-> b.getNumero().compareTo(a.getNumero())); // decrescente dai comparar b com a
        this.aluno.values().forEach(a -> s.add(a.clone()));
        return s;
    }

}
