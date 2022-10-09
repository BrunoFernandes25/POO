package Projeto;

import java.time.LocalDate;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;


public class Controller implements Serializable{
                    //ID,Casa
    private Map<Integer,SmartHome> casas; //criar excecao para as casas pois nao opde existir 2 pessoas com o mesmo NIF
    private Map<String,Comercializador> comercializadores; // nao tao com a lista de clientes atualizada ver amanha
    private Map<String,SmartDevice> devices; // Ex: <"1111",lampada....>
    private Simulacao simulacao;

    /**
     * Construtor
     * @param casas
     * @param comercializadores
     * @param devices
     */
    public Controller(Map<Integer,SmartHome> casas,Map<String,Comercializador> comercializadores,Map<String,SmartDevice> devices){
        this.casas = casas;
        this.comercializadores = comercializadores;
        this.devices= devices;
        this.simulacao=null;
    }
    //usado em simular para obter as casas criadas e assim escolher quais delas serao clientes de certos comercializadores
    public Map<Integer, SmartHome> getCasas() { //mas acho que nao virao com devices e divisoes ja..., nao sei, ou virao pois o parser irá ja criar as casas com tudo
        return this.casas;
    }

    public void setComercializadores(Map<String, Comercializador> comercializadores) {
        this.comercializadores = comercializadores;
    }

    public List<String> divisoes(SmartHome s){
        return this.casas.get(s.getNIF()).getDivisoes();
    }
    public SmartHome getCasa(int NIF){
        return this.casas.get(NIF);
    }


    public void lerCasas(String ficheiro) throws LinhaIncorretaException, SmartDeviceJaExisteException, DivisaoNaoExisteException, DivisaoJaExisteException{
        Map<Integer,SmartHome> novasCasas = new HashMap<>();
        Map<String,Comercializador> novosComercializadores = new HashMap<>();
        Map<String,SmartDevice> novosDevices = new HashMap<>();
        Parser.parse(novasCasas,novosComercializadores,novosDevices,ficheiro);

        for(Integer nif : novasCasas.keySet()){
            this.casas.put(nif,novasCasas.get(nif));
        }

       for(String id: novosDevices.keySet()){
           this.devices.put(id,novosDevices.get(id));
       }

        for(String nome: novosComercializadores.keySet()){
            this.comercializadores.put(nome,novosComercializadores.get(nome));
            for(SmartHome sh: this.casas.values()){
                if(sh.getComercializador().getNome().equals(nome)){
                    this.comercializadores.get(nome).addCasasClientes(sh);
                }
            }
        }
    }

    public void gravarEstado(String ficheiro) throws IOException {
        SaveSimulacao.gravar(casas, comercializadores,ficheiro);
    }

    public void lerCasasObjetos(String ficheiro) throws IOException,ClassNotFoundException{ //corrigir nao le devices por divisao
        FileInputStream fis = new FileInputStream(ficheiro);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Controller controladorlido = (Controller) ois.readObject();

        controladorlido.comercializadores.forEach((k,v)->this.comercializadores.put(k,v));
        controladorlido.casas.forEach((k,v)->this.casas.put(k,v));
        controladorlido.devices.forEach((k,v)->this.devices.put(k,v));


        ois.close();
        fis.close();
    }

    public void gravarEstadoObjetos(String ficheiro) throws IOException{
        FileOutputStream fos = new FileOutputStream(ficheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(this);

        oos.close();
        fos.close();
    }
                        //NIF do dono, o nome e comercializador

    public void criarCasa(String nome, int NIF,String comercializador) throws SmartHomeJaExisteException, ComercializadorNaoExisteException, NumeroFormatoImpossivelException, IOException, InterruptedException {
        //int NIF = Integer.parseInt("nif");
        if((!casas.containsKey(NIF))){      //caso nao exista já essa casa
            if(comercializadores.containsKey(comercializador)){ //caso exista comercializador entao podemos criar uma casa
                SmartHome sh = new SmartHome(nome,NIF);
                sh.setComercializador(this.comercializadores.get(comercializador));
                this.comercializadores.get(comercializador).addCasasClientes(sh);
                //vamos à casa e colocamos nela o respetivo Comercializador
                casas.put(NIF,sh);
            }else throw new ComercializadorNaoExisteException();  //se nao existir retorna a excecao
        } else throw new SmartHomeJaExisteException();
    }

    public List<Integer> donosCasas(){
        return new ArrayList<>(this.casas.keySet());
    }

    public void adicionarDivisao(String div, int nif) throws DivisaoJaExisteException{
        //SmartHome sh;
        //obter acasa com o nif x
        //e adicionar a divisao
        this.casas.get(nif).adicionaDivisao(div); //obtemos a casa com o NIF x e adicionamos divisao nessa casa
    }

    public void criarDeviceSMartBulb(String id, String casa, String divisao,Boolean estado, Tonalidade tone,int tamanho, double consumo) throws SmartDeviceJaExisteException, DivisaoNaoExisteException {
        //converter String  casa em Integer
        int h = Integer.parseInt(casa);
         //this.casas.get(h); //obtemos a SmartHome
        //falta obter os devices da casa e caso nao exista este device adiciona lo
        // ou basta apenas adicionar pois o metodo adiciona tera a throw caso o device ja exista na casa
        SmartDevice sb;
        sb = new SmartBulb(id,estado,tone,tamanho,consumo);
        //caso as casas todas nao tenham o device com id x entao adicionamo o device na casa pretendida

        //mas nos queremos que adicione se nao existir em nenhuma casa mais
        if(!(devices.containsKey(id))){
            if(this.casas.get(h).getDivisoes().contains(divisao)){
                devices.put(id,sb);
                this.casas.get(h).addicionaDevice(sb);
                this.casas.get(h).addicionaDevicenumaDivisao(sb,divisao);
            }
            else throw  new DivisaoNaoExisteException();
        } else throw new SmartDeviceJaExisteException();
        //this.casas.get(h).addicionaDevice(sb); //adiciona caso nao existe ja um com o mesmo id nessa casa (ler em cima continuacao)
    }

    public void criarDeviceSMartSpeaker(String id, String casa,String divisao, Boolean estado,int volume,String radio,String marca, double consumo) throws SmartDeviceJaExisteException,DivisaoNaoExisteException{
        //converter String em Integer
        int hh = Integer.parseInt(casa); // para poder usar o Map<Integer,SmartHome> e obter a SmartHome atraves da key Integer
        SmartDevice ss;
        ss = new SmartSpeaker(id,estado,volume,radio,marca,consumo);
        if(!(devices.containsKey(id))){
            if(this.casas.get(hh).getDivisoes().contains(divisao)) {
                devices.put(id, ss);
                this.casas.get(hh).addicionaDevice(ss);
                this.casas.get(hh).addicionaDevicenumaDivisao(ss,divisao);
            }
            else throw new DivisaoNaoExisteException();
        } else throw new SmartDeviceJaExisteException();

    }

    public void criarDeviceSMartCamera(String id, String casa,String divisao,Boolean estado,String resolucao,int tamanho, double consumo) throws SmartDeviceJaExisteException, DivisaoNaoExisteException {
        //converter String em Integer
        int hhh = Integer.parseInt(casa); // para poder usar o Map<Integer,SmartHome> e obter a SmartHome atraves da key Integer
        SmartDevice sc;
        sc = new SmartCamera(id,estado,resolucao,tamanho,consumo);
        if(!(devices.containsKey(id))){
            if(this.casas.get(hhh).getDivisoes().contains(divisao)) {
                devices.put(id, sc);
                this.casas.get(hhh).addicionaDevice(sc);
                this.casas.get(hhh).addicionaDevicenumaDivisao(sc,divisao);
            }
            else throw new DivisaoNaoExisteException();
        } else throw new SmartDeviceJaExisteException();
    }

    public void criarComercializador(String comercializador) throws ComercializadorjaExisteException{
        if(!(comercializadores.containsKey(comercializador))){
            comercializadores.put(comercializador,new Comercializador(comercializador)); //cria se Comercializador pelo nome, nao se sabe nada dos clientes
        } else throw new ComercializadorjaExisteException();
    }

    //public
    public void simular(Map<Integer,SmartHome> clientes,LocalDate d1,LocalDate d2) throws SimulacaoImpossivelException, SmartHomeJaExisteException, SmartHomeNaoExisteException, DataImpossivelException {
        //vamos a cada casacliente e associamo-la ao respetivo cliente.
        //Exemplo do Map<(Galp,clientesGalp),(Endesa,clientesEndesa)>
        double custoMax = 0.0;
        int casaMaxFaturacao = 0;
        for (SmartHome sh : clientes.values()) {
            System.out.println(sh.getComercializador().emiteFatura(sh, d1, d2));
            //arranjar forma de emitir fatura por comercializador
            if (sh.getComercializador().custoENergeticoTotal(sh, d1, d2) > custoMax) {
                custoMax = sh.getComercializador().custoENergeticoTotal(sh, d1, d2);
                casaMaxFaturacao = sh.getNIF();
            }
        }
        double maxFaturacao = 0.0;
        String com = "";
        for (Comercializador c : this.comercializadores.values()) {
            if (c.TotalFaturacao() > maxFaturacao) {
                maxFaturacao = c.TotalFaturacao();
                com = c.getNome();
            }
        }
        System.out.printf("-> A casa que mais consumiu neste período de tempo foi a casa [%d].\n", casaMaxFaturacao);
        //System.out.println(this.comercializadores.toString()); //da [] pois nao ta a passar os clientes das casas
        System.out.printf("-> O Comercializador que mais faturou neste período de tempo foi o Comercializador [%s].\n", com);
        //System.out.printf("\nO Consumidor que mais faturou neste período de tempo foi o Consumidor [%s]\n",);
    }
    public void informar(Map<Integer,SmartHome> clientes) throws  SmartHomeNaoExisteException{
        System.out.println("Informação relativa a todas as casas existentes:");
        for(SmartHome sh : clientes.values()) {
            System.out.println(sh.toString());
            System.out.printf("Custo diário da casa [%d] é de %.2f €.\n\n", sh.getNIF(), sh.getComercializador().custoEnergetico(sh));
        }
        int i;
        for(i=0;i<comercializadoresSImulacao().size();i++){
            System.out.println(comercializadoresSImulacao().get(i));
        }

    }

    /**
     * Altera estado de todos os devices de uma casa
     */

    public void alteraCasaOff(Map<Integer,SmartHome> clientes, int casa) {

        SmartHome s = clientes.get(casa);
        Map<String, SmartDevice> devices = s.getDevices();
        s.setAllOff(devices);
    }

    public void alteraCasaOn(Map<Integer,SmartHome> clientes, int casa) {

        SmartHome s = clientes.get(casa);
        Map<String, SmartDevice> devices = s.getDevices();
        s.setAllOn(devices);
    }

    /**
     * Altera estados em todos os devices de todas as casas
     * */
    public void alteraTudoparaOff(Map<Integer,SmartHome> clientes){
        for(SmartHome sh : clientes.values()){
            //tentar coletar para uma lista e escolher qual o device pelo id que queremos alterar o estado
                sh.setAllOff(sh.getDevices()); //depois mudar para ja fica assim
        }
    }

    public void alteraTudoparaOn(Map<Integer,SmartHome> clientes){
        for(SmartHome sh : clientes.values()){
            //tentar coletar para uma lista e escolher qual o device pelo id que queremos alterar o estado
            sh.setAllOn(sh.getDevices()); //depois mudar para ja fica assim
        }
    }

    /**
     * Altera estados por divisao em todas as casas
     * */
    public void alteraDivisaoOff(Map<Integer,SmartHome> clientes,String divisao){
        for(SmartHome sh: clientes.values()){
            //List<String> divs = sh.getDivisoes();
            if(sh.getDivisoes().contains(divisao)){ //divs.contains(divisao)
                sh.setAllOffDiv(divisao);
            }
        }
    }
    public void alteraDivisaoOn(Map<Integer,SmartHome> clientes,String divisao){
        for(SmartHome sh: clientes.values()){
            //List<String> divs = sh.getDivisoes();
            if(sh.getDivisoes().contains(divisao)){ //divs.contains(divisao)
                sh.setAllOnDiv(divisao);
            }
        }
    }

    /**
     * Altera estados de uma divisao de uma casa
     */
    public void alteradivCasaOff(Map<Integer,SmartHome> clientes, String divisao, int home) throws DivisaoNaoExisteException {
        SmartHome sh = clientes.get(home);
        if(!sh.getDivisoes().contains(divisao)) throw new DivisaoNaoExisteException();
        sh.setAllOffDiv(divisao);
    }

    public void alteradivCasaOn(Map<Integer,SmartHome> clientes, String divisao, int home) throws DivisaoNaoExisteException {
        SmartHome sh = clientes.get(home);
        if(!sh.getDivisoes().contains(divisao)) throw new DivisaoNaoExisteException();
        sh.setAllOnDiv(divisao);
    }

    /**
     * Altera estado de um device
     */

    //nao dao erro se adicionar um dispositivo que nao exista
    public void alteraDeviceOff(Map<Integer,SmartHome> clientes, String id) throws  SmartDeviceNaoExisteException{
        int encontrou = 0;
        for(SmartHome sh : clientes.values()){
            Map<String,SmartDevice> devices = sh.getDevices();
            if(devices.containsKey(id)){
                SmartDevice sd = devices.get(id);
                sh.setDeviceOff(sd);
                encontrou = 1;
            }
        }
        if(encontrou == 0) throw new SmartDeviceNaoExisteException("\nSmartDevice nao existe.\n");
    }

    public void alteraDeviceOn(Map<Integer,SmartHome> clientes, String id) throws SmartDeviceNaoExisteException{
        int encontrou = 0;
        for(SmartHome sh : clientes.values()){
            Map<String,SmartDevice> devices = sh.getDevices();
            if(devices.containsKey(id)){
                SmartDevice sd = devices.get(id);
                sh.setDeviceOn(sd);
                encontrou = 1;
            }
        }
        if(encontrou == 0) throw new SmartDeviceNaoExisteException("\nSmartDevice nao existe.\n");
    }

    public void printDevices(){
        List<String> devs = new ArrayList<String>();
        for(SmartDevice sd: this.devices.values()){
            devs.add(sd.getId());
        }
        List<String> devsordenados = devs.stream().sorted().collect(Collectors.toList());
        devsordenados.forEach(System.out::println);
    }

    public List<Comercializador> comercializadoresSImulacao (){
        List<Comercializador> coms = new ArrayList<Comercializador>();
        for(Comercializador c : this.comercializadores.values()){
            coms.add(c);
        }
        return coms;
    }

    public void printaDivisoes(int NIF){
        List<String> divisoes = this.casas.get(NIF).getDivisoes();
        for(String div: divisoes){
            System.out.println(div);
        }
    }

    public void emitirFaturasComercializador(String comercializador, LocalDate d1, LocalDate d2) throws SmartHomeNaoExisteException, DataImpossivelException, ComercializadorNaoExisteException {
        if(!this.comercializadores.containsKey(comercializador)) throw  new ComercializadorNaoExisteException();
        List<SmartHome> casas = this.comercializadores.get(comercializador).getCasasClientes();
        for(SmartHome sh : casas){
            System.out.println(this.comercializadores.get(comercializador).emiteFatura(sh,d1,d2));
        }
    }
}

