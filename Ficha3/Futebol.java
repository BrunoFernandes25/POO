package Ficha3;


import java.util.Objects;

public class Futebol {

    public enum Estado {
        Iniciar,
        Decorrer,
        Terminado
    }

    private Estado estado;
    private String equipaCasa;
    private String equipaFora;
    private  int golosCasa;
    private  int golosFora;

    public Futebol() {
        this.setEstado(Estado.Iniciar);
        this.setEquipaCasa("Equipa da Casa");
        this.setEquipaFora("Equipa Visitante");
        this.setGolosCasa(0);
        this.setGolosFora(0);
    }

    public Futebol(Estado estado, int golosCasa, int golosFora, String equipaCasa, String equipaFora) {
        this.setEstado(estado);
        this.setEquipaCasa(equipaCasa);
        this.setEquipaFora(equipaFora);
        this.setGolosCasa(golosCasa);
        this.setGolosFora(golosFora);

    }

    public Futebol(Futebol umFutebol) {
        this.estado = umFutebol.getEstado();
        this.equipaCasa = umFutebol.getEquipaCasa();
        this.equipaFora = umFutebol.getEquipaFora();
        this.golosCasa = umFutebol.getGolosCasa();
        this.golosFora = umFutebol.getGolosFora();
    }


    public boolean equals(Object umFutebol) {
        if (this == umFutebol){
            return true;
        }
        if (umFutebol == null || getClass() != umFutebol.getClass()) {
            return false;
        }
        Futebol futebol = (Futebol) umFutebol;
        return golosCasa == futebol.golosCasa && golosFora == futebol.golosFora
                && estado == futebol.estado && Objects.equals(equipaCasa, futebol.equipaCasa)
                && Objects.equals(equipaFora, futebol.equipaFora);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("Resultado: ")
                .append(this.getGolosCasa())
                .append("x")
                .append(this.getGolosFora());

        return s.toString();
    }

    public Futebol clone(){
        return new Futebol(this);
    }

    public Estado getEstado() {
        return estado;
    }

    public String getEquipaCasa() {
        return equipaCasa;
    }

    public String getEquipaFora() {
        return equipaFora;
    }

    public int getGolosCasa() {
        return golosCasa;
    }

    public int getGolosFora() {
        return golosFora;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setEquipaCasa(String equipaCasa) {
        this.equipaCasa = equipaCasa;
    }

    public void setEquipaFora(String equipaFora) {
        this.equipaFora = equipaFora;
    }

    public void setGolosCasa(int golosCasa) {
        this.golosCasa = golosCasa;
    }

    public void setGolosFora(int golosFora) {
        this.golosFora = golosFora;
    }

    //a)
    public void startGame() {
        this.estado = Estado.Decorrer;
        this.golosCasa = 0;
        this.golosFora = 0;
    }

    //b)
    public void endGame() {
        this.estado = Estado.Terminado;
    }

    //c)
    public void goloVisitado() {
        if(this.estado == Estado.Decorrer) {
            setGolosCasa(this.getGolosCasa() + 1);
        }
    }

    //d)
    public void goloVisitante() {
        if(this.estado == Estado.Decorrer) {
            setGolosFora(this.getGolosFora() + 1);
        }
    }

    //e)
    public String resultadoActual(){
        if(this.estado == Estado.Iniciar){
            System.out.println("Jogo ainda nem começou !!");
        }
        StringBuilder r = new StringBuilder();
        r.append(this.getEquipaCasa());
        r.append(" ");
        r.append(this.getGolosCasa());
        r.append(" X ");
        r.append(this.getGolosFora());
        r.append(" ");
        r.append(this.getEquipaFora());
        return r.toString();
    }






}
