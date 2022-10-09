package Projeto;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;


public abstract class SmartDevice implements Serializable {
    /**
     * Instancias
     * */
    private String id; // codigo fabricante // depois fazer super(id) nas subclasses nos metodos definidos
    private boolean on;

    /**
     * Construtores
     * */
    public SmartDevice(){
        this.id="";
        this.on=false;  //default está desligado.

    }

    public SmartDevice(String id, boolean estado){
        this.id=id;
        this.on = estado;
    }

    public SmartDevice (SmartDevice sd){
        this.id=sd.getId();
        this.on=sd.isOn();
    }

    /**
    * Metodos abstratos
    * */
    //public abstract String processa(); //toString nao sei se faria sentido deixar abstrato, tipo um device tera sempre um codigo e um estado independentemente do que for depois pode ser especializado
    public abstract double custoInstalacao(); //rever isto e pensar se deixo assim ou se uso como metodo aqui
    public abstract SmartDevice clone();
    public abstract double consumoEnergia();
    /**
    * Getters
    * */
    public String getId() {
        return this.id;
    }

    public boolean isOn() {
        return this.on;
    }

    /**
     * Setters
     */

    public void setId(String id) {
        this.id = id;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    /**
     * Equals
     * */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SmartDevice sd = (SmartDevice) o;
        return this.on == sd.on && this.id.equals(sd.id);
    }

    public int hashCode() {
        int hash = 7;           //evita NullPOinterException
        hash = 37*hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 37*hash + (this.on ? 0:1);
        return hash;
    }

    /**
     * toString
     * */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nReferencia: ");
        sb.append(this.id);
        sb.append("\nEstado(On): ");
        sb.append(this.on);

        return sb.toString();
    }

    /**
     * Mudar estado dos SmartDevices
     * */
    public void turnOn() {
        this.on = true;
    }

    public void turnOff() {
        this.on = false;
    }


    public void escreverdevs(FileWriter writer) throws  IOException{
        String subClasse = this.getClass().getSimpleName();
        if(this instanceof SmartBulb){
            writer.write("SmartBulb:");
        }
        else{
            writer.write(this.getClass().getSimpleName() + ":");
        }


        // lampada -> String id, boolean estado, Tonalidade tone, int tam, double consumo
        //coluna -> String id,boolean estado,int vol, String rad, String marca, double consumo
        //camera -> String id, boolean estado, String res, int tamanho, double consumo

                                                        //Tonalidade nao deve dar print ver depois testando
        if( this instanceof SmartBulb) writer.write( this.getId() + "," + this.isOn() + "," + ((SmartBulb) this).getTone() + "," + ((SmartBulb) this).getTam() + "," + ((SmartBulb) this).getConsumod());
        if( this instanceof SmartCamera) writer.write(this.getId() + "," + this.isOn() + "," + ((SmartCamera) this).getResolucao() + "," + ((SmartCamera) this).getTamficheiro() + "," + ((SmartCamera) this).getConsumod());
        if(this instanceof SmartSpeaker) writer.write(this.getId() + "," + this.isOn() + "," + ((SmartSpeaker) this).getVolume() + "," + ((SmartSpeaker) this).getRadio() + "," +((SmartSpeaker) this).getMarca() + "," + ((SmartSpeaker)this).getConsumod());

        writer.write("\n");
        writer.flush();
    }
}
