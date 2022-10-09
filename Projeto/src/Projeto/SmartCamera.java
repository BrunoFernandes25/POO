package Projeto;

import java.util.Objects;

public class SmartCamera extends SmartDevice {

    private String resolucao;
    private int tamficheiro;
    private double consumod;

    /**
     * Construtores
     * */

    public SmartCamera(){
        super();
        this.resolucao="";
        this.tamficheiro = 0;
        this.consumod = 0.0;
    }

    public SmartCamera(String id, boolean estado, String res, int tamanho, double consumo){
        super(id,estado);
        this.resolucao=res;
        this.tamficheiro=tamanho;
        this.consumod=consumo;
    }

    public SmartCamera(SmartCamera sc){
        super(sc);
        this.resolucao=sc.getResolucao();
        this.tamficheiro=sc.getTamficheiro();
        this.consumod=sc.getConsumod();
    }

    /**
     * Getters
     * */

    public String getResolucao() {
        return this.resolucao;
    }

    public int getTamficheiro() {
        return this.tamficheiro;
    }

    public double getConsumod() {
        return this.consumod;
    }

    /**
     * Setters
     * */

    public void setResolucao(String res){
        this.resolucao = res;
    }

    public void setTamficheiro(int tamfich){
        this.tamficheiro= tamfich;
    }

    public void setConsumod(double consumo){
        this.consumod= consumo;
    }

    /**
     * clone
     * */

    public SmartCamera clone(){
        return new SmartCamera(this);
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
        SmartCamera sc = (SmartCamera) o;
        return super.equals(o) && this.resolucao.equals(sc.resolucao) && this.tamficheiro == sc.tamficheiro && Double.compare(sc.consumod,this.consumod) == 0;
    }

    /**
     * hashcode
     * */

    public int hashCode() {
        int hash = super.hashCode(); //considerando estar correto o da superclasse e está
        hash = 37 * hash + (this.resolucao != null ? this.resolucao.hashCode() : 0);
        hash = 37 * hash + this.tamficheiro; // nao precisa de (int) visto volume ja ser int
        long aux = Double.doubleToLongBits(consumod);
        hash = 37 * hash + (int) (aux ^ (aux >>> 32));

        return hash;
    }
    //return Objects.hash(super.hashCode(), resolucao, tamficheiro, consumod);

    /**
     * toString
     * */

    public String toString(){
        return super.toString() + "\nResolução: " + this.resolucao + "\nTamanho do ficheiro: " + this.tamficheiro + "\nConsumo Diário: " + this.consumod + "\n";
    }

    public double custoInstalacao(){
        double custo = 50.0;
        return custo;
    }

    /**
     *
     * @return Consumo de uma SmartCamera
     */
    public double consumoEnergia(){
        double custo;
        String res = this.resolucao;
        switch (res){
            case ("(3840x2160)"):
                custo = this.tamficheiro* 0.7;
                break;
            case ("(2160x1440)"):
                custo = this.tamficheiro* 0.5;
                break;
            case ("(1920x1080)"):
                custo = this.tamficheiro* 0.4;
                break;
            case ("(1366x768)"):
                custo = this.tamficheiro* 0.3;
                break;
            case ("(1024x768)"):
                custo = this.tamficheiro* 0.2;
                break;
            default:
                custo = this.tamficheiro*0.1;
        }
        return  custo;
    }

    public static SmartCamera parse(String input){
        String[] campos = input.split(",");
        return new SmartCamera(campos[0],Boolean.parseBoolean(campos[1]),campos[2],Integer.parseInt(campos[3]),Double.parseDouble(campos[4]));
    }
}
