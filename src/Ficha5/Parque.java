package Ficha5;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.lang.StringBuilder;
import java.util.stream.Collectors;

public class Parque {
    private String nomeParque;
    Map<String,Estacionamento> lugares;

    public Parque (){
        this.nomeParque = "";
        this.lugares = new TreeMap<String,Estacionamento>();
    }

    public Parque (String nomeParque, Map<String,Estacionamento> lugares) {
        setNomeParque(nomeParque);
        setLugares(lugares);
    }

    public  Parque (Parque umParque) {
        this(umParque.getNomeParque(),umParque.getLugares());
    }

    public String getNomeParque() {
        return this.nomeParque;
    }

    public void setNomeParque(String nomeParque) {
        this.nomeParque = nomeParque;
    }

    public Map<String, Estacionamento> getLugares() {
        return this.lugares;
    }

    public void setLugares(Map<String, Estacionamento> lugares) {
        this.lugares = lugares;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Nome do Parque: ");
        s.append(this.nomeParque);
        s.append("\nLugares do Parque: \n");
        s.append(this.lugares.toString());
        s.append("\n");
        return s.toString();
    }

    public boolean equals(Object obj){
        if (this==obj) {
            return true;
        }
        if (obj==null || obj.getClass()!=this.getClass()) {
            return false;
        }

        Parque newParque = (Parque) obj;
        return (this.nomeParque.equals(newParque.getNomeParque())  &&  this.lugares.equals(newParque.getLugares()));
    }

    public Parque clone() {
        return new Parque(this);
    }

    public List<Estacionamento> lugaresOcupados() {
        return this.lugares.values().stream().filter(a-> a.getMinutos()>0).collect(Collectors.toList());
    }

    public void adicionaLugar(Estacionamento e){
        //coloca se nao existir
        this.lugares.putIfAbsent(e.getMatricula(),e);
    }

    public void removeLugar(String matricula){
        this.lugares.remove(matricula);
    }

    public void alteraTempo (String matricula, int tempo) {
        Estacionamento e = this.lugares.get(matricula);
        e.setMinutos(tempo);
        this.lugares.put(matricula,e);
    }

    public int minAtribuidosTotal () {
        return this.lugares.values().stream().mapToInt(Estacionamento::getMinutos).sum();
    }

    public int minAtribuidosTotal2 () {
        int soma = 0;
        for(Estacionamento e : this.lugares.values()) {
            soma += e.getMinutos();
        }
        return soma;
    }


    public boolean existeLugar(String matricula){
        boolean r = false;
        if(this.lugares.containsKey(matricula)){
            r = true;
        }
        return r;
    }
    /* iterador externo
    public List<String> TempoMaiorque (int tempo) {
        List<String> matriculas = new ArrayList<String>();
        for(Estacionamento e: this.lugares.values()){
            Estacionamento temp = e.clone();
            if(temp.getMinutos() > tempo) {
                matriculas.add(temp.getMatricula());
            }
        }
        return matriculas;
    }*/
    public List<String> TempoMaiorque (int tempo) {
        return this.lugares.values().stream().filter(a -> a.getMinutos()>tempo).map(a-> a.getMatricula()).collect(Collectors.toList());
    }

    public Map<String,Estacionamento> copiaLugares() {
        Map<String,Estacionamento> copia= new TreeMap<String,Estacionamento>();
        copia.putAll(this.lugares);
        // ou numa so linhas -> Map<String, Estacionamento> copia = new TreeMap<String, Estacionamento>(this.lugares);
        return copia;
    }
    public String informacao (String matricula) {
        //para certa matricula retorna se é permanente ou nao;
        String r = "Aluguer";
        if(this.lugares.get(matricula).isPermanente()) {
            r = "Permanente";
        }
        return r;
    }

}
