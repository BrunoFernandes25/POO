package Projeto;

public class SmartSpeaker extends SmartDevice {

    public static final int MAX = 100; //volume máximo

    private int volume;
    private String radio;
    private String marca;
    private double consumod;

    public SmartSpeaker(){
        super();
        this.volume=0;
        this.radio = "";
        this.marca = "";
        this.consumod = 0.0;
    }

    public SmartSpeaker(String id,boolean estado,int vol, String rad, String marca, double consumo){
        super(id,estado);
        this.volume=vol;
        this.radio=rad;
        this.marca = marca;
        this.consumod= consumo;
    }

    public  SmartSpeaker(SmartSpeaker ss){
        super(ss);
        this.volume=ss.getVolume();
        this.radio=ss.getRadio();
        this.marca=ss.getMarca();
        this.consumod=ss.getConsumod();
    }

    /**
     * Getters
     * */

    public int getVolume() {
        return this.volume;
    }

    public String getRadio() {
        return this.radio;
    }

    public String getMarca() {
        return this.marca;
    }

    public double getConsumod() {
        return this.consumod;
    }

    /**
     * Setters
     * */

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setConsumod(double consumod) {
        this.consumod = consumod;
    }

    /**
     * clone
     * */

    public SmartSpeaker clone(){
        return new SmartSpeaker(this);
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
        SmartSpeaker ss = (SmartSpeaker) o;
        return super.equals(o) && this.volume == ss.volume &&  this.radio.equals(ss.radio) && this.marca.equals(ss.marca) && Double.compare(ss.consumod,this.consumod) == 0;
    }

    /**
     *  hashcode
     * */
    public int hashCode() {
        int hash = super.hashCode(); //considerando estar correto o da superclasse e está
        hash = 37 * hash + this.volume; // nao precisa de (int) visto volume ja ser int
        hash = 37 * hash + (this.radio != null ? this.radio.hashCode() : 0);
        hash = 37 * hash + (this.marca != null ? this.marca.hashCode() : 0);
        long aux = Double.doubleToLongBits(consumod);
        hash = 37 * hash + (int) (aux ^ (aux >>> 32));

        return hash;
    }
                                //int    string  string  double
        //return Objects.hash(super.hashCode(), volume, radio, marca, consumod);

    /**
     * toString
     * */
    public String toString(){
        return super.toString() + "\nVolume: " + this.volume + "\nRadio:" + this.radio + "\nMarca: " + this.marca + "\nConsumo Diário: " + this.consumod + "\n";
    }

    public double custoInstalacao(){
        double custo = 150.0; //instalacao completa
        return custo;
    }

    /**
     *
     * @return Consumo de uma SmartSpeaker
     */
    public double consumoEnergia(){
        double custo = this.consumod + (0.10 *this.volume); //acho que pode ser assim inventado pelo que entendi do enunciado.
        return custo;
    }


    public void volumeUp() {
        if (this.volume<MAX) this.volume++;
    }

    public void volumeDown() {
        if (this.volume>0) this.volume--;
    }

    public static SmartSpeaker parse(String input){
        String[] campos = input.split(",");
        return new SmartSpeaker(campos[0],Boolean.parseBoolean(campos[1]),Integer.parseInt(campos[2]),campos[3],campos[4],Double.parseDouble(campos[5]));
    }

}