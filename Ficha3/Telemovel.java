package Ficha3;

import java.util.*;
import java.util.Objects;

public class Telemovel {

    /**
     * Variáveis de instância
     */
    private String marca;
    private String modelo;
    private int displayX;
    private int displayY;

    private int numMensagens;
    private int armazenamentoMensagens;
    private  String[] mensagens;
    private int armazenamentoFotos;
    private int armazenamentoApps;
    private int armazenamentoFotosApps;
    private int armazenamentoOcupado;
    private int numFotosArmazenadas;
    private int numAppsInstaladas;
    private String[] nomeAppsInstaladas;

    /**
    * Construtor de telemovel (por omissão)
    */
    public Telemovel() {
        this.marca = "";
        this.modelo = "";
        this.displayX= 0;
        this.displayY= 0;
        this.numMensagens= 0;
        this.armazenamentoMensagens = 0;
        this.mensagens = new String[]{}; // queremos representar um array de strings por isso
        this.armazenamentoFotos = 0;
        this.armazenamentoApps = 0;
        this.armazenamentoFotosApps = 0;
        this.armazenamentoOcupado=0;
        this.numFotosArmazenadas = 0;
        this.numAppsInstaladas = 5;
        this.nomeAppsInstaladas = new String[]{"Imsta,Fassebuk,Tuiter,Yutube,Gmaile"};
    }

    /**
    * Construtor de telemovel(parameterizado)
    */
    public Telemovel (String marca, String modelo, int dX, int dY, int numSms, int armSms, String[] mensagens, int armFotos, int armApps, int armFotoseApps, int armOcupado, int numFotos, int numApps, String[] nomeAppsInstaldas){
        this.setMarca(marca);
        this.setModelo(modelo);
        this.setDisplayX(dX);
        this.setDisplayY(dY);
        this.setNumMensagens(numSms);
        this.setArmazenamentoMensagens(armSms);
        this.setMensagens(mensagens);
        this.setArmazenamentoFotos(armFotos);
        this.setArmazenamentoApps(armApps);
        this.setArmazenamentoFotosApps(armFotoseApps);
        this.setArmazenamentoOcupado(armOcupado);
        this.setNumFotosArmazenadas(numFotos);
        this.setNumAppsInstaladas(numApps);
        this.setNomeAppsInstaldas(nomeAppsInstaldas);

    }
    /**
    * Construtor de telemovel (como cópia)
    */
    public Telemovel(Telemovel umTelemovel) {
        this.marca = umTelemovel.getMarca();
        this.modelo = umTelemovel.getModelo();
        this.displayX = umTelemovel.getDisplayX();
        this.displayY = umTelemovel.getDisplayY();
        this.numMensagens = umTelemovel.getNumMensagens();
        this.armazenamentoMensagens = umTelemovel.getArmazenamentoMensagens();
        this.mensagens= umTelemovel.getMensagens();
        this.armazenamentoFotos = umTelemovel.getArmazenamentoFotos();
        this.armazenamentoApps = umTelemovel.getArmazenamentoApps();
        this.armazenamentoFotosApps = umTelemovel.getArmazenamentoFotosApps();
        this.armazenamentoOcupado = umTelemovel.getArmazenamentoOcupado();
        this.numFotosArmazenadas = umTelemovel.getNumFotosArmazenadas();
        this.numAppsInstaladas = umTelemovel.getNumAppsInstaladas();
        this.nomeAppsInstaladas = umTelemovel.getNomeAppsInstaladas();
    }

    /**
     * Getters e setters
     */
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getDisplayX() {
        return displayX;
    }

    public void setDisplayX(int displayX) {
        this.displayX = displayX;
    }

    public int getDisplayY() {
        return displayY;
    }

    public void setDisplayY(int displayY) {
        this.displayY = displayY;
    }

    public int getNumMensagens() {
        return numMensagens;
    }

    public void setNumMensagens(int numMensagens) {
        this.numMensagens = numMensagens;
    }
    public int getArmazenamentoMensagens() {
        return numMensagens;
    }

    public void setArmazenamentoMensagens(int numMensagens) {
        this.numMensagens = numMensagens;
    }

    public String[] getMensagens() {
        return mensagens;
    }

    public void setMensagens(String[] mensagens) {
        this.mensagens = mensagens;
    }

    public int getArmazenamentoFotos() {
        return armazenamentoFotos;
    }

    public void setArmazenamentoFotos(int armazenamentoFotos) {
        this.armazenamentoFotos = armazenamentoFotos;
    }

    public int getArmazenamentoApps() {
        return armazenamentoApps;
    }

    public void setArmazenamentoApps(int armazenamentoApps) {
        this.armazenamentoApps = armazenamentoApps;
    }

    public int getArmazenamentoFotosApps() {
        return armazenamentoFotosApps;
    }

    public void setArmazenamentoFotosApps(int armazenamentoFotosApps) {
        this.armazenamentoFotosApps = armazenamentoFotosApps;
    }

    public int getArmazenamentoOcupado() {
        return armazenamentoOcupado;
    }

    public void setArmazenamentoOcupado(int armazenamentoOcupado) {
        this.armazenamentoOcupado = armazenamentoOcupado;
    }

    public int getNumFotosArmazenadas() {
        return numFotosArmazenadas;
    }

    public void setNumFotosArmazenadas(int numFotosArmazenadas) {
        this.numFotosArmazenadas = numFotosArmazenadas;
    }

    public int getNumAppsInstaladas() {
        return numAppsInstaladas;
    }

    public void setNumAppsInstaladas(int numAppsInstaladas) {
        this.numAppsInstaladas = numAppsInstaladas;
    }

    public String[] getNomeAppsInstaladas() {
        return nomeAppsInstaladas;
    }

    public void setNomeAppsInstaldas(String[] nomeAppsInstaladas) {
        this.nomeAppsInstaladas = nomeAppsInstaladas;
    }

    /**
    * @return String com as características do telemovel
    */
    public String toString() {
        return "Telemovel{" +
                "\n marca = '" + marca + '\'' +
                "\n modelo = '" + modelo + '\'' +
                "\n displayX = " + displayX +
                "\n displayY = " + displayY +
                "\n numMensagens = " + numMensagens +
                "\n mensagens = " + Arrays.toString(mensagens) +
                "\n armazenamentoFotos = " + armazenamentoFotos +
                "\n armazenamentoApps = " + armazenamentoApps +
                "\n armazenamentoFotosApps = " + armazenamentoFotosApps +
                "\n numFotosArmazenadas = " + numFotosArmazenadas +
                "\n numAppsInstaladas = " + numAppsInstaladas +
                "\n nomeAppsInstaldas = " + Arrays.toString(nomeAppsInstaladas) +
                "\n}\n";
    }

    public boolean equals(Object telem) {
        if (this == telem){
            return true;
        }

        if (telem == null || getClass() != telem.getClass()){
            return false;
        }
        Telemovel telemovel = (Telemovel) telem;
        return (displayX == telemovel.displayX && displayY == telemovel.displayY
                && numMensagens == telemovel.numMensagens && armazenamentoFotos == telemovel.armazenamentoFotos
                && armazenamentoApps == telemovel.armazenamentoApps && armazenamentoFotosApps == telemovel.armazenamentoFotosApps
                && numFotosArmazenadas == telemovel.numFotosArmazenadas
                && numAppsInstaladas == telemovel.numAppsInstaladas && Objects.equals(marca, telemovel.marca)
                && Objects.equals(modelo, telemovel.modelo) && Arrays.equals(mensagens, telemovel.mensagens)
                && Arrays.equals(nomeAppsInstaladas, telemovel.nomeAppsInstaladas));
    }

    public Telemovel clone() {
        return new Telemovel(this);
    }

    //a)
    public boolean existeEspaco (int numeroBytes) {
        return this.armazenamentoOcupado + numeroBytes <= this.armazenamentoFotosApps + this.armazenamentoMensagens;  // ou seja menor que o armazenamento total
    }

    //b)
    public  void instalaApp(String nome, int tamanho) {
        //primeiro verificamos se existe espaco, se houver instala se a aplicacao em questao
        if (existeEspaco(tamanho)) {
            this.setArmazenamentoApps(getArmazenamentoApps() + tamanho);

            String[] nomesApps = this.getNomeAppsInstaladas();
            String[] nomesAppsatualizados = new String[nomesApps.length + 1];                             // mais a app em questao que ira aumentar o tamanho do array em 1
            System.arraycopy(nomesApps,0,nomesAppsatualizados,0,nomesApps.length);  //copiamos o array com o nome das apps ja existente e...
            nomesAppsatualizados[nomesAppsatualizados.length -1] = nome;                                                        // colocamos na ultima posicao do array String a nova App
            this.setNomeAppsInstaldas(nomesAppsatualizados);

            this.setNumAppsInstaladas(getNumAppsInstaladas() + 1);
            this.setArmazenamentoOcupado(getArmazenamentoOcupado() + tamanho);
        } else {
            System.out.printf("Falha na instalação de '' %s ''. Espaço de memória insuficiente.\n\n",nome);
        }
    }

    //c)
    public void recebeMsg (String msg) {
        if(existeEspaco(msg.length())) {
            this.setNumMensagens(getNumMensagens() + 1);

            String[] sms = this.getMensagens();
            String[] smsatualizados = new String[sms.length + 1];
            System.arraycopy(sms,0,smsatualizados,0,sms.length);
            smsatualizados[smsatualizados.length - 1] = msg;
            this.setMensagens(smsatualizados);

            this.setArmazenamentoMensagens(getArmazenamentoMensagens() + msg.length() );
            this.setArmazenamentoOcupado(getArmazenamentoOcupado() + msg.length());
        }
    }

    //d)
    public double tamMedioApps () {
        double appsInstaladas = this.getArmazenamentoApps();
        double numApps = this.getNumAppsInstaladas();

        return appsInstaladas/numApps;
    }

    //e)
    /*
    public String maiorMsg () {
        int max_indice = 0;
        int tam_maiorMsg = 0;
        String[] sms = this.getMensagens();

        for(int i = 0; i<this.getNumMensagens();i++) {
            if (tam_maiorMsg < sms[i].length()) {
                max_indice = i;
                tam_maiorMsg = sms[i].length();
            }
        }

        return (sms[max_indice]);
    }
    */

    public String maiorMsg() {
        int biggestSize = Integer.MIN_VALUE;
        String answer = "";
        for(String sms : this.getMensagens()) {
            if(sms.length() > biggestSize) {
                biggestSize = sms.length();
                answer = sms;
            }
        }
        return answer;
    }

    //f)

    public void removeApp (String nome, int tamanho) {

        String[] nomesApps = this.getNomeAppsInstaladas();
        String[] nomesAppsatualizados = new String[getNumAppsInstaladas() - 1];                             // mais a app em questao que ira aumentar o tamanho do array em 1

        for (int i=0,j=0;i< getNumAppsInstaladas();i++) {   //j sera usado par o novo array
            if(nomesApps[i] != null && !nomesApps[i].equals(nome)) {   // ou seja enquanto nao terminar o array ou o nome for diferente da app comparada copiamos essa app para o novo array
                nomesAppsatualizados[j] = nomesApps[i];
                j += 1;
            }
        }

        this.setArmazenamentoApps(getArmazenamentoApps() - tamanho);
        this.setArmazenamentoApps(getArmazenamentoApps() - tamanho);
        this.setNumAppsInstaladas(getNumAppsInstaladas() - 1);

        System.arraycopy(nomesAppsatualizados,0,nomesApps,0,nomesAppsatualizados.length);

    }

}
