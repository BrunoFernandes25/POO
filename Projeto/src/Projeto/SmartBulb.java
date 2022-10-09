package Projeto;

import java.util.Objects;

public class SmartBulb extends SmartDevice {

    //public static final int MAX = 100; //Tonalidade máxima deixamos de usar                   !!!!

    //public static final int WARM = 70;       //[70,100]           || public pois na classe casa terá de ser possivel alterar o estado das lampadas por exemplo
    //public static final int NEUTRAL = 50;    //[50,70[
    //public static final int COLD = 0;       //[0,50[

    private Tonalidade tone;
    private int tam; //dimensao (cm)
    private double consumod; //consumo diario

    public SmartBulb(){
        super();
        this.tone= Tonalidade.NEUTRAL; // 50 default                                            !!!!                                          !!!!
        this.tam = 0;
        this.consumod=0.0;
    }

    public SmartBulb(String id, boolean estado, Tonalidade tone, int tam, double consumo){
        super(id,estado);
        this.tone=tone;
        this.tam=tam;
        this.consumod=consumo;
    }

    public SmartBulb(SmartBulb sb){
        super(sb);
        this.tone=sb.getTone();
        this.tam=sb.getTam();
        this.consumod=sb.getConsumod();
    }

    /**
     * Getters
     * */
    public Tonalidade getTone() {
        return this.tone;
    }

    public int getTam() {
        return this.tam;
    }

    public double getConsumod() {
        return this.consumod;
    }

    /**
     * Setters
     * */
    public void setTone(Tonalidade tonalidade){
        if(tonalidade == Tonalidade.WARM){ //[70,100] deixamos de usar do 0 ao 100           !!!!
            this.tone=Tonalidade.WARM;
        }
        else if(tonalidade == Tonalidade.COLD){ //[50,70[                            !!!!
            this.tone=Tonalidade.COLD;
        }
        else{
            this.tone= tonalidade; // <50 -> COLD
        }
    }


    public void setTam(int tamanho){
        this.tam=tamanho;
    }

    public void setConsumod(double consumod) {
        this.consumod = consumod;
    }

    /**
     * clone
     * */
    public SmartBulb clone(){
        return new SmartBulb(this);
    }

    /**
     * equals
     * */
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || o.getClass() != this.getClass()){
            return false;
        }
        SmartBulb sb = (SmartBulb) o;
        return super.equals(o) && this.tam == sb.tam && this.tone == sb.tone && Double.compare(sb.consumod,this.consumod) == 0;
    }


    public int hashCode() {
        /*int hash =  super.hashCode();
        hash = 37*hash +*/
        return Objects.hash(super.hashCode(), tone, tam, consumod);
    }
    //return Objects.hash(super.hashCode(), tone, tam, consumod);

    /**
     * toString
     *
     */
    public String toString(){
        return super.toString() + "\nTonalidade: " + this.tone + "\nTamanho: " + this.tam + "\nConsumo Diário: " + this.consumod +"\n";
    }

    public double custoInstalacao(){
        double custo = 3.5; // por lampada
        return custo;
    }

    /**
     *
     * @return Consumo de uma SmartBulb
     */
    public double consumoEnergia(){
        double custo;
        if(this.getTone() == Tonalidade.COLD){
            custo = (0.15)*getConsumod() + 0.50; //valor fixo 0.50
        }
        else if(this.getTone() == Tonalidade.WARM){
            custo = (0.50)*getConsumod() + 0.50;
        }
        else {
            custo = (0.30)*getConsumod() + 0.50;
        }
        return custo;
    }

    public static SmartBulb parse(String input){
        String[] campos = input.split(",");                                                                                     //30 exemplo mudar depois
        return new SmartBulb(campos[0],Boolean.parseBoolean(campos[1]),Tonalidade.valueOf(campos[2]),Integer.parseInt(campos[3]),Double.parseDouble(campos[4]));
    }
}
