package Ficha5;

import java.lang.StringBuilder;

public class Estacionamento {
    private String matricula;
    private String nome;
    private int minutos;
    private boolean permanente;

    public Estacionamento(){
        this.matricula = "";
        this.nome = "";
        this.minutos = 0;
        this.permanente = false;
    }

    public Estacionamento (String matricula, String nome, int minutos, boolean permanente){
        this.setMatricula(matricula);
        this.setNome(nome);
        this.setMinutos(minutos);
        this.setPermanente(permanente);
    }

    public  Estacionamento (Estacionamento umEstacionamento) {
        this.matricula = umEstacionamento.getMatricula();
        this.nome = umEstacionamento.getNome();
        this.minutos = umEstacionamento.getMinutos();
        this.permanente = umEstacionamento.isPermanente();
    }

    public String getMatricula() {
        return this.matricula;
    }

    public String getNome() {
        return this.nome;
    }

    public int getMinutos() {
        return this.minutos;
    }

    public boolean isPermanente() {
        return this.permanente;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public void setPermanente(boolean permanente) {
        this.permanente = permanente;
    }


    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Estacionamento novoLugar = (Estacionamento) obj;
        return (this.matricula.equals(novoLugar.getMatricula()) && this.nome.equals(novoLugar.getNome()) &&  this.minutos == (novoLugar.getMinutos()) && this.permanente == (novoLugar.isPermanente()) );
    }

    /*
    public String toString() {
        return "Estacionamento{" +
                "matricula='" + matricula + '\'' +
                ", nome='" + nome + '\'' +
                ", minutos=" + minutos +
                ", permanente=" + permanente +
                '}';
    }
    || OU ||
    */
    public String toString() {
        StringBuilder s= new StringBuilder();
        s.append("\nMatricula: ");
        s.append(this.matricula);
        s.append("\nProprietário: ");
        s.append(this.nome);
        s.append("\nTempo no estacionamento: ");
        s.append(this.minutos);
        s.append("\nPermanente: ");
        s.append(this.isPermanente());
        s.append("\n");
        return s.toString();
    }

    public Estacionamento clone(){
        return new Estacionamento(this);
    }

}
