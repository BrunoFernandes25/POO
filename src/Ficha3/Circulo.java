package Ficha3;

public class Circulo {

    // variáveis de instâncias
    private double x;
    private double y;
    private double raio;

    // Construtor de Circulo (por omissão)
    public Circulo() {
        this.setX(0);
        this.setY(0);
        this.setRaio(0);
    }

    public Circulo(double x, double y, double raio) {
        this.setX(x);
        this.setY(y);
        this.setRaio(raio);
    }

    public Circulo (Circulo umCirculo){     //usado no clone
        this.x= umCirculo.getX();
        this.y= umCirculo.getY();
        this.raio= umCirculo.getRaio();
    }

    // Getters
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public  double getRaio() {
        return this.raio;
    }

    //Setters
    public void setX(double novoX) {
        this.x = novoX;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    public  void alteraCentro(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double calculaArea() {       //A=pi*r^2
        return Math.PI * Math.pow(raio,2);
    }

    public double calculaPerimetro() {  //P=pi*d, onde d=2*r
        return Math.PI * (2*raio);
    }

    public boolean equals(Object umCirculo) {
        if (this == umCirculo) {
            return true;
        }
        if (umCirculo == null || getClass() != umCirculo.getClass()) {
            return false;
        }

        Circulo circulo = (Circulo) umCirculo;
        return  this.x == circulo.getX() && this.y == circulo.getY() && this.raio == circulo.getRaio();
    }

    public String toString() {
        return "Circulo{" +
                "x=" + x +
                ", y=" + y +
                ", raio=" + raio +
                '}';
    }

    public Circulo clone(){
        return new Circulo(this);
    }
}
