package Projeto;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class SmartHome implements Serializable {
    private String name; //dono da casa
    private int NIF; //NIF dono da casa (nome e NIF utilizar nas faturas) logo nao deve poder ser privado
    private List<String> divisoes;      //Divisoes da casa
    private Map<String,SmartDevice> devices; //  Devices todos de uma casa
    private Map<String, List<String>> devicesPorDivisao;   //colecao de dispositivos por divisao de uma casa
    private Comercializador comercializador; // ter em conta a Composicao entre as classes
    //ex:  Sala = [devices]  devices queremos que sejam as referencias
         //key    values

    public SmartHome(){
        this.name = "";
        this.NIF = 0;
        this.divisoes= new ArrayList<>();
        this.devices= new HashMap<>();
        this.devicesPorDivisao= new HashMap<>();
        this.comercializador = new Comercializador();
    }

    public SmartHome(String n, int num, List<String> divisoes, Map<String,SmartDevice> d,Map< String, List<String> > dpd, Comercializador c){
        this.name=n;
        this.NIF=num;
        this.divisoes= divisoes;
        this.devices=d.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, a->a.getValue().clone()));
        this.devicesPorDivisao = dpd.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
        this.comercializador = c;
    }                                                       //aqui nao pus clone porque dizia ser protegido nao sei se nao fara problemas

    public SmartHome (String nome, int num,Comercializador c){
        this.name=nome;
        this.NIF=num;
        this.divisoes= new ArrayList<>();
        this.devices= new HashMap<>();
        this.devicesPorDivisao= new HashMap<>();
        this.comercializador = c;
        c.addCasasClientes(this);
    }
    //utilizado no controller para criar uma casa para um certo dono(NIF)
    public SmartHome (String nome,int num){
        this.name=nome;
        this.NIF=num;
        this.divisoes= new ArrayList<>();
        this.devices= new HashMap<>();
        this.devicesPorDivisao= new HashMap<>();
        this.comercializador = new Comercializador();
    }

    public SmartHome(SmartHome sh){
        this.name = sh.getName();
        this.NIF=sh.getNIF();
        this.divisoes =sh.getDivisoes();
        this.devices=sh.getDevices();
        this.devicesPorDivisao = sh.getDevicesPorDivisao();
        this.comercializador = sh.getComercializador();
    }

    /**
     * Getters
     * */

    public String getName() {
        return this.name;
    }

    public int getNIF() {
        return this.NIF;
    }

    public List<String> getDivisoes() {
        return this.divisoes;
    }

    public Map<String, SmartDevice> getDevices() {
        return this.devices;
    }

    public Map<String, List<String>> getDevicesPorDivisao() {
        return this.devicesPorDivisao;
    }

    public Comercializador getComercializador() {
        return this.comercializador;
    }

    /**
     * Setters
     * */
    public void setName(String name) {
        this.name = name;
    }

    public void setNIF(int NIF) {
        this.NIF = NIF;
    }

    public void setDivisoes(List<String> divisoes) {
        this.divisoes = divisoes;
    }

    public void setDevices(Map<String, SmartDevice> devices) {
        this.devices = devices;
    }

    public void setDevicesPorDivisao(Map<String, List<String>> devicesPorDivisao) {
        this.devicesPorDivisao = devicesPorDivisao;
    }

    public  void setComercializador(Comercializador comercializador){
        this.comercializador = comercializador;
    }

    /**
     * clone
     * */
    public SmartHome clone(){
        return new SmartHome(this);
    }

    /**
     * equals
     * */

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        SmartHome sh = (SmartHome) o;
        return this.name.equals(sh.name) && this.NIF == sh.NIF && this.divisoes.equals(sh.divisoes) && this.devices.equals(sh.devices) && this.devicesPorDivisao.equals(sh.devicesPorDivisao) && this.comercializador.equals(sh.comercializador);
    }

    /**
     * hashcode este acho que terei que fazer a mao obrigatoriamente e apenas este devido as suas instancias
     * */
    public int hashCode() { // FALTA VER ESTE HASHCODE TAMBEM E O DA LAMPADA
        int hash = 7;
        hash = 37*hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 37*hash + this.NIF;
        hash = 37*hash + (this.divisoes != null ? this.divisoes.hashCode() : 0);
        hash = 37*hash + (this.devices != null ? this.devices.hashCode() : 0);
        hash = 37*hash + (this.devicesPorDivisao != null ? this.devicesPorDivisao.hashCode() : 0);
        hash = 37*hash + (this.comercializador != null ? this.comercializador.hashCode() : 0);
        return hash;
        //return Objects.hash(name, NIF, divisoes, devices, devicesPorDivisao);
    }

    /**
     * toString
     * */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nCasa[");
        sb.append("\nNome: ");
        sb.append(this.name);
        sb.append("\nNIF: ");
        sb.append(this.NIF);
        sb.append("\nDivisoes: ");
        sb.append(this.divisoes);
        sb.append("\nDevices:\n");
        sb.append(this.devices);
        sb.append("\nDevices por divisão: ");
        sb.append(this.devicesPorDivisao);
        sb.append("\nComercializador de energia: ");
        sb.append(this.comercializador.getNome());
        sb.append("\n]");

        return sb.toString();
    }

    public boolean existeDiv(String d){
        List<String> divisoess = getDivisoes();
        //comparar string d com cada string da lista de divisoes
        boolean r = false;
        if(divisoess.contains(d)){
            r = true;
        }
        return r;
    }


    public boolean existeDev(SmartDevice s) {    //na casa toda
        boolean r = false;
        Iterator<SmartDevice> it = getDevices().values().iterator();
        SmartDevice sd;
        while (it.hasNext() && !r) {
            sd = it.next();
            r = sd.getId().equals(s.getId());
        }
        return r;
    }
    /**
     * @param sdevice,divisao
     * @return True caso exista Device na divisao passada como parametro
     */
    public boolean existeDevicenumaDivisao (String sdevice,String divisao){
        boolean r;
        //vamos a divisao, e percorremos (iteradores) ate encontrar, se encontrar retorna true semao retorna false
        List<String> devicess = this.devicesPorDivisao.get(divisao);
        if(existeDiv(divisao) && devicess.contains(sdevice)){
            r= true;
        }
        else{
            r = false;
        }
        return r;
    }

    /**
     * @param s,divis
     * @throws SmartDeviceJaExisteException
     * @throws DivisaoNaoExisteException
     */
    public void addicionaDevicenumaDivisao(SmartDevice s,String divis) throws SmartDeviceJaExisteException,DivisaoNaoExisteException{
        //ao adicionar numa divisao, adiciona automaticamente numa casa
        //caso nao exista a divisao
        if(!(this.devicesPorDivisao.containsKey(divis))) throw new DivisaoNaoExisteException("Não existe essa Divisao na casa" + divis);
        // caso essa divisao exista, analizemos se ja existe esse device na casa

        //if(this.devices.containsKey(s.getId())) throw new SmartDeviceJaExisteException("Já existe este SmartDevicenaCasa" + s.getId());

        //caso nao exista, atualizamos os devices na casa, os devices por divisao
        //caso exista divisao e nao exista ainda o device na casa, adicionamo lo nessa divisao

        //this.devices.put(s.getId(),s); // adicionamos os devices na casa, visto nao existir

        //acho que nao precisamos do SetDevices mas testar
        this.devicesPorDivisao.get(divis).add(String.valueOf(s.getId()));//adicionamos o value a key correspondente
        //e atualizamos os devices nas divisoes da casa caos queiramos ver como estao

    }   //try catch ver slides e merdas do genero amanha

    /**
     *
     * @param s
     * @throws SmartDeviceJaExisteException
     */
    public void addicionaDevice(SmartDevice s) throws SmartDeviceJaExisteException{
        if(this.devices.containsKey(s.getId())) throw new SmartDeviceJaExisteException("Já existe este SmartDevice " + s.getId() +" na Casa ");
        //atualizamos os devices na casa
        this.devices.put(s.getId(),s);
        setDevices(this.getDevices());
    }

    /**
     *
     * @param div
     * @throws DivisaoJaExisteException
     */
    public void adicionaDivisao (String div) throws DivisaoJaExisteException{
        if(this.divisoes.contains(div)) throw new DivisaoJaExisteException("Já existe esta Divisao na casa " + div);
        divisoes.add(div);
        setDivisoes(divisoes);
        List<String> devices = new ArrayList<String>(); //devices na divisao adicionada nao tera nada la dentro até adicionarmos la algum device
        Map<String,List<String>> dPd = getDevicesPorDivisao(); // devicesPorDivisao até ao momento
        dPd.put(div,devices);
        setDevicesPorDivisao(dPd);
    }

    /**
     * Muda o estado do device para ligado
     * @param dvc
     */
    public void setDeviceOn(SmartDevice dvc) {
        boolean on = true;
        if(!dvc.isOn()){
            dvc.setOn(true);
        }
        //poderia depois com um try catch ou algo do genero nao sei dizer ao utilizador que ja se encontra ligado, e o mesmo a contece no caso do metodo a seguir
    }

    /**
     * Muda o estado do device para desligado
     * @param dvcc
     */
    public void setDeviceOff(SmartDevice dvcc) {
        boolean onn = false;
        if(dvcc.isOn()){
            dvcc.setOn(false);
        }
    }

    public void setAllOnDiv(String divis) {
        //temos de fazer algo para obter os devices todos de uma divisao
        List<String> devicesss = this.devicesPorDivisao.get(divis);
        //depois mudar o seu estado para On, e atualizar os devices nessa divisao e na casa
        //temos as strings apenas
        Iterator<SmartDevice> it = getDevices().values().iterator();
        SmartDevice sdsd;
        while(it.hasNext()){
            sdsd = it.next();
            if(existeDevicenumaDivisao(sdsd.getId(),divis)){
                setDeviceOn(sdsd);
            }
        }
    }

    public void setAllOn(Map<String,SmartDevice> devices){
        for(SmartDevice sd : devices.values()){
            setDeviceOn(sd);
        }
    }

    public void setAllOff(Map<String,SmartDevice> devices){
        for(SmartDevice sd : devices.values()){
            setDeviceOff(sd);
        }
    }


    public void setAllOffDiv(String diviss) {
        //temos de fazer algo para obter os devices todos de uma divisao
        List<String> devicessss = this.devicesPorDivisao.get(diviss);
        //depois mudar o seu estado para On, e atualizar os devices nessa divisao e na casa
        //temos as strings apenas
        Iterator<SmartDevice> it = getDevices().values().iterator();
        SmartDevice sddd;
        while(it.hasNext()){
            sddd = it.next();
            if(existeDevicenumaDivisao(sddd.getId(),diviss)){
                setDeviceOff(sddd);
            }
        }
    }

    /**
     *
     * @param devicess
     * @return custo dos devices que estão ligados numa casa
     */
    public double custoDevices(Map<String,SmartDevice> devicess){
        List<SmartDevice> d = devicess.values().stream().collect(Collectors.toList());
        //double custodvcs = this.casasClientes.stream().mapToDouble(SmartHome::)
        return d.stream().mapToDouble(SmartDevice::consumoEnergia).sum();
    }


    public static SmartHome parse(String input){
        String[] campos = input.split(",");

        return new SmartHome(campos[0],Integer.parseInt(campos[1]),Comercializador.parse(campos[2]));
    }

    public void escrever(FileWriter writer) throws IOException{
        writer.write("Casa:");
        writer.write(this.name);
        writer.write(",");
        String num = String.valueOf(this.NIF);
        writer.write(num);
        writer.write(",");
        String com = this.comercializador.getNome();
        writer.write(com);
        for(String divisao: this.divisoes){
            List<String> devs = this.devicesPorDivisao.get(divisao);
            this.devicesPorDivisao.put(divisao,devs);
        }
        writer.write("\n");

        //escrever NIF, Comercializador se tivermos em conta o exmeplo do stor, mas podemos adicionar os devices e as divisoes
        writer.flush();
        //colocar como o escrever do comercializador depois
    }

    //metodo para escrever divisoes de uma casa
    public void escreverdivs(FileWriter writer) throws IOException{

        for(int i = 0; i<this.divisoes.size();i++){
            writer.write("Divisao:");
            writer.write(this.divisoes.get(i));
            writer.write("\n");
        }

        writer.flush();
    }
}
