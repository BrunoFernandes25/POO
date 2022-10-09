package Projeto;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class View {

    private Controller controller;
    private Scanner input;
    private LocalDate dataInicial;
    private LocalDate dataFinal;

    public View(Controller controller, Scanner input) {
        this.controller = controller;
        this.input = input;
    }

    public void run(){
        System.out.println("\nProjeto de POO Grupo 15 !!");

        int opcao;
        do{
            opcao = receberComando();
            switch(opcao){
                case 0:
                    System.out.println("\nSimulação Terminada.");
                    break;
                case 1:
                    lerFicheiro();
                    break;
                case 2:
                    gravarEmFicheiro();
                    break;
                case 3:
                    lerFicheiroObjetos();
                    break;
                case 4:
                    gravarEmFicheiroObjetos();
                    break;
                case 5:
                    adicionarCasa();
                    break;
                case 6:
                    adicionarDivisao();
                    break;
                case 7:
                    adicionarDevice();
                    break;
                case 8:
                    adicionarComercializador();
                    break;
                case 9:
                    simulacao();
                    break;
                case 10 :
                    informacao();
                    break;
                case 11:
                    faturasporComercializador();
                    break;
                //informacao das casas, devices, e comercializadores existentes por exemplo caso queiramos colocar mais merdas
                default:
                    System.out.println("\nOpção Inválida. Escolha um número entre 0 e 11.\n");
            }
        }while(opcao != 0);
    }

    public int receberComando() {
        try{
            System.out.println("\n\n\n\n\n\n\n\n\n[Menu Inicial]" +
                    "\n  _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ " +
                    "\n/                                         \\" +
                    "\n|Introduza a opcao que pretende efetuar:   |\n" +
                    "|0.Sair                                    |\n" +
                    "|1. Carregar ficheiro                      |\n" +
                    "|2. Gravar estado em ficheiro              |\n" +
                    "|3. Carregar ficheiro de objetos           |\n" +
                    "|4. Gravar estado em ficheiro de objetos   |\n" +
                    "|5.Criar Casa                              |\n" +
                    "|6.Adicionar Divisoes                      |\n" +
                    "|7.Adicionar SmartDevice                   |\n" +
                    "|8.Adicionar Comercializador               |\n" +
                    "|9.Simulacao                               |\n" +
                    "|10.Informação casas ficheiro              |\n" +
                    "|11.Obter faturas por comercializador      |\n" +
                    "\\ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ /"); //entramos com as datas, avancar no tempo e emitir faturas, bem como alterar estado dos devices de certas casas
            System.out.println("Opção: ");
            return Integer.parseInt(this.input.next());
        } catch(Exception e){
            return 99; // num. aleatorio
        }
    }

    public void lerFicheiro() {
        try {
            System.out.print("Introduza o nome do ficheiro que pretende ler: ");
                                        //nome do ficheiro
            this.controller.lerCasas(this.input.next());
            System.out.println("\nInformação do ficheiro carregada.\n");
        } catch (LinhaIncorretaException | SmartDeviceJaExisteException | DivisaoNaoExisteException e) {
            System.out.println("\nFicheiro está mal formado.\n");
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro. Tente outra vez.\n");
        }
    }

    public void gravarEmFicheiro() {
        System.out.print("Introduza o nome do ficheiro onde pretende gravar: ");
        String ficheiro = this.input.next();

        try {
            this.controller.gravarEstado(ficheiro);
            System.out.println("\nGravação concluída.\n");
        } catch (IOException e) {
            System.out.println("\nImpossível gravar no ficheiro " + ficheiro + ".\n");
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro. Tente outra vez.\n");
        }
    }

    public void lerFicheiroObjetos(){
        try{
            System.out.println("Introduza o nome do ficheiro que pretende ler: ");
            this.controller.lerCasasObjetos(this.input.next());
            System.out.println("\nInformação do ficheiro carregada.\n");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("\nImpossivel ler o ficheiro.\n" + e.toString());
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro. Tente outra vez.\n");
        }
    }

    public void gravarEmFicheiroObjetos() {
        System.out.print("Introduza o nome do ficheiro onde pretende gravar: ");
        String ficheiro = this.input.next();

        try {
            this.controller.gravarEstadoObjetos(ficheiro);
            System.out.println("\nGravação concluída.\n");
        } catch (IOException e) {
            System.out.println("\nImpossível gravar no ficheiro " + ficheiro + ".\n");
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro. Tente outra vez.\n");
            System.out.println(e.toString());
        }
    }

    public void adicionarCasa(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nIntroduza a Casa que pretende criar: ");
        System.out.println("Introduza o nome do dono: ");
        String dono = this.input.next();
        System.out.println("\nIntroduza o Comercializador: ");
        String com = this.input.next();
        System.out.println("\nIntroduza o NIF do dono: ");
        String nif = this.input.next();
        try{
            int NIF = Integer.parseInt(nif);
            this.controller.criarCasa(dono,NIF,com);
            System.out.println("\nCasa criada com sucesso\n");
        }catch(SmartHomeJaExisteException e){
            System.out.println("\nA casa " + nif + " já existe.\n");
        }catch (ComercializadorNaoExisteException e){
            System.out.println("\nO Comercializador " + com + " nao existe.\n");
        }catch (NumberFormatException e) {
            System.out.println("\nNumero NIF apenas pode conter números.\n");
        }catch(Exception e){
            System.out.println("\nOcorreu um erro. Tente outra vez.\n");
        }
    }

    public void adicionarDivisao(){
        try{
            System.out.println("Escolha a casa a que pretende adicionar a divisao: ");
            List<Integer> donosCasas = this.controller.donosCasas(); //obtem se a lista de todos os donos de casas até ao momento

            for(int i = 0; i< donosCasas.size();i++) System.out.println(i + "->" + donosCasas.get(i)); //nao printou o i ver isto
            System.out.println("\nCasa [0-" + (donosCasas.size()-1) + "]: ");

            int h = Integer.parseInt(this.input.next());


            //nao sei se ta bem, mas acho que sim verificar depois
            int casa = donosCasas.get(h); //seleciona a casa que quer atraves do seu NIF (escolhido pelos que foram apresentados

            System.out.println("Introduza o nome da divisao");
            String nome = input.next();
            //vamos aos valores de casas e adicionamos na casa que queremos acho
            this.controller.adicionarDivisao(nome,casa);
            System.out.println("Divisao adicionada com sucesso");
        }catch (DivisaoJaExisteException e){
            System.out.println(e.getMessage());
        }catch (IndexOutOfBoundsException e) {
            System.out.println("\nA casa que escolheu nao existe.\n");
        }catch (Exception e) {
            System.out.println("\nA informaçao submetida nao está correta.\n");
        }
    }

    public void adicionarDevice(){
        try{
            System.out.println("Escolha a casa a que pretende adicionar o device:");
            List<Integer> donosCasass = this.controller.donosCasas(); //obtem se a lista de todos os donos de casas até ao momento mas nao funciona quando carrego o ficheiro tenho de criar uma funçao diferente

            for(int i = 0; i< donosCasass.size();i++) System.out.println(i + "->" + donosCasass.get(i));
            System.out.println("\nCasa [0-" + (donosCasass.size()-1) + "]: ");
            /*
            * 0-> "11111"
            * 1-> "22222"
            * 2-> "33333"
            * ...  (casa)
            * */

            int casa = Integer.parseInt(this.input.next());
            String home = String.valueOf(donosCasass.get(casa)); // obtendo assim por exemplo a casa "3333"

            //obter a divisao a adicionar o dispositivo
            //divisoes de uma casa é uma lista de Strings
            SmartHome s = this.controller.getCasa(donosCasass.get(casa));
            List<String> divs = this.controller.divisoes(s);
            System.out.println("\nIntroduza a Divisão da casa:");
            for(String div : divs){
                System.out.println(div);
            }
            String divisao = this.input.next();

            System.out.println("\nIntroduza o tipo de Device:");
            System.out.println("0. SmartBulb");
            System.out.println("1. SmartSpeaker");
            System.out.println("2. SmartCamera");
            System.out.println("Device: ");
            int device = Integer.parseInt(this.input.next()); //irá ser usado nos cases

            switch (device){
                case 0: //SmartBulb
                    //Estado
                    System.out.println("Introduza o id do Device");
                    String id = this.input.next();
                    System.out.println("\nEscolha o estado do Device [true/false]: ");
                    String estado = this.input.next(); //escrevemos true or false
                    boolean on = Boolean.parseBoolean(estado);
                    //Tonalidade
                    System.out.println("\nDigite a tonalidade do device: ");
                    String tonalidade = this.input.next(); //vamos ter que criar mais uma excecao para a Tonalidade
                    Tonalidade tone = Tonalidade.valueOf(tonalidade); //ver se ta correto depois
                    //Tamanho
                    System.out.println("\nDigite o tamanho da SmartBulb(em cm): ");
                    int tam = Integer.parseInt(this.input.next());
                    //Consumo
                    System.out.println("\nDigite o consumo da SmartBulb(em KWh): ");
                    double consumo = Double.parseDouble(this.input.next());
                    //String id, boolean estado, Tonalidade tone, int tam, double consumo
                    //nao usamos o numero obtido para fazer um switch no controller fiz diferente
                    this.controller.criarDeviceSMartBulb(id,home,divisao, on,tone,tam,consumo);
                    break;
                case 1: // SmartSpeaker
                    System.out.println("Introduza o id do Device");
                    String idd = this.input.next();
                    //Estado
                    System.out.println("\nEscolha o estado do Device [true/false]: ");
                    String estadoo = this.input.next(); //escrevemos true or false
                    boolean onn = Boolean.parseBoolean(estadoo);
                    //Volume
                    System.out.println("\nDigite o volume que pretende que a coluna tenha quando ligada:");
                    int vol = Integer.parseInt(this.input.next());
                    //Radio
                    System.out.println("\nDigite a radio FM a tocar: ");
                    String radio = this.input.next();
                    //Marca
                    System.out.println("Digite a marca da SmartSpeaker: ");
                    String marca = this.input.next();
                    //Consumo
                    System.out.println("\nDigite o consumo da SmartSpeaker(em KWh): ");
                    double consumoo = Double.parseDouble(this.input.next());
                    //String id,boolean estado,int vol, String rad, String marca, double consumo
                    //caso a lista de ids nao tenha o input que for passado entao cria device e adiciona esse id à lista
                    this.controller.criarDeviceSMartSpeaker(idd,home,divisao,onn,vol,radio,marca,consumoo);
                    break;
                case 2:
                    System.out.println("Introduza o id do Device");
                    String iddd = this.input.next();
                    //Estado
                    System.out.println("\nEscolha o estado do Device [true/false]: ");
                    String estadooo = this.input.next(); //escrevemos true or false
                    boolean onnn = Boolean.parseBoolean(estadooo);
                    //Resolucao -> ainda nao alterei o calculo do consumo conforme as resolucoes e nao criei nada para excecoes caso se insira uma resolucao errada
                    System.out.println("\nDigite a resolucao da camera: ");
                    String res = this.input.next();
                    //Tamanho
                    System.out.println("\nDigite o tamanho do ficheiro filmado pela SmartCamera(em cm): ");
                    int tamm = Integer.parseInt(this.input.next());
                    //Consumo
                    System.out.println("\nDigite o consumo da camera(em KWh): ");
                    double consumooo = Double.parseDouble(this.input.next());
                    //String id, boolean estado, String res, int tamanho, double consumo
                    this.controller.criarDeviceSMartCamera(iddd,home,divisao,onnn,res,tamm,consumooo);
                    break;
            }
            //this.controller.criarDevice(id,casa);  //criamos o device X com um id Y na casa Z     //id passa como Int mas ao criar o device tem de ter o id em Stringm tal como a classe decice assim manda
            System.out.println("Device adicionado com sucesso.\n");
            //id dos devices é igual a todos
            //depois cases para adicionar as particularidades de cada device
        }catch(DivisaoNaoExisteException e){
            System.out.println("\nDivisao nao existe nessa casa.\n");
        } catch (SmartDeviceJaExisteException e){
            System.out.println("\nDevice com este ID já existe.\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nA casa que escolheu nao existe.\n");
        } catch (Exception e) {
            System.out.println("\nA informaçao submetida não esta correta.\n");
        }
    }

    public void adicionarComercializador(){
        System.out.println("Introduza o Comercializador que pretende criar: ");
        String comercializador = this.input.next();

        try{
            this.controller.criarComercializador(comercializador);
            System.out.println("Comercializador criado com sucesso");
        }catch(ComercializadorjaExisteException e){
            System.out.println("\nO Comercializador " + comercializador + " já existe.\n");
        } catch(Exception e){
            System.out.println("\nOcorreu um erro. Tente outra vez.\n");
        }
    }

    //PENSAR SE DEIXAMOS UTILIZADOR ESCOLHER QUAIS AS CASAS OU METEMOS TODAS E PASSAMOS APENAS OS COMERCIALIZADORES COM AS LISTASCLIENTES JA COMPLETAS
    //PEDIR AJUDA AO STOR

    /**
     * Getters e Setters usados para guardar a ultima data
     * */
    public LocalDate getDataInicial() throws DataImpossivelException {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) throws DataImpossivelException {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() throws DataImpossivelException{
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) throws DataImpossivelException {
        this.dataFinal = dataFinal;
    }

    public void simulacao(){
        try {
            List<Integer> donosCasasss = this.controller.donosCasas(); //lista de todas casas por nifs para o utilizador escolher quais incluir na simulacao
            Map<Integer,SmartHome> casasClientes = new HashMap<>(); //clientes usados na simuação
            int casa;
            do{
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("Escolha a(s) casa(s) que pretende que sejam incluidas nesta simulação: ");
                for(int i = 0; i< donosCasasss.size();i++) System.out.println(i + "->" + donosCasasss.get(i));
                System.out.println("Insira qualquer numero diferente das opções anteriores e termina a escolha de casas.");
                System.out.println("\nEscolha uma casa [0-" + (donosCasasss.size()-1) + "]: ");
                casa = Integer.parseInt(this.input.next());
                if(casa<donosCasasss.size()){
                    int home = donosCasasss.get(casa);
                    if(casasClientes.containsKey(home)) {
                        System.out.println("\nJá escolheu esta casa.\n"); //desta forma nao retorna ao menu inicial, com exceção voltaria, deve haver forma de fazer isto mas o tempo é pouco já nao tenho tempo
                    }
                    else casasClientes.put(home,this.controller.getCasas().get(home));
                }
            }while( casa < donosCasasss.size());            //casa != 99 da erro porque ele executa a linha de baixo e 99 nao exite no numero de casas

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            //falta definir a data inicial e simular
            System.out.println("Insira a data inicial da simulação[dd/mm/yyyy]: ");
            String data1 = this.input.next();
            //transformar data1 em LocalDate d1
            LocalDate d1 = LocalDate.parse(data1,formatter);
            //guardamos d1 como data inicial
            setDataInicial(d1);
            System.out.println("\nInsira a data final da simulação[dd/mm/yyyy]: ");
            String data2 = this.input.next();
            //transformar data1 em LocalDate d2
            LocalDate d2 = LocalDate.parse(data2,formatter);
            //guardamos d2 como data final
            setDataFinal(d2);
            this.controller.simular(casasClientes,d1,d2); //passamos <"NIF",casa>
            //guardamos d2 como nova data inicial
            //setDataInicial(d2);
            /*
            * String d2 = this.input.next();
            *
            *
            * */
            String resposta;
            LocalDate temp = null;
            do {
                System.out.println("\nPretende simular novamente?[s/n]: "); //1 -> sim 0 -> nao
                resposta = this.input.next();
                if (resposta.equals("s")) {
                    setDataInicial(getDataFinal());
                    //novo dim de contrato
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nUltima data inserida: [" + getDataFinal() + "]");
                    System.out.println("\nDigite uma nova data final para a nova simulação[dd/mm/yyyy]: ");
                    String data3 = this.input.next();
                    LocalDate d3 = LocalDate.parse(data3, formatter);
                    if (getDataInicial().compareTo(d3) >= 0)
                        throw new DataImpossivelException("Datas impossiveis de comparar.\n");
                    setDataFinal(d3);
                    System.out.println("\nPretende alterar o estado dos devices?[s/n]: ");
                    String resposta2 = this.input.next();
                    if (resposta2.equals("s")) {
                        System.out.println("\nIndique qual a alteração que pretende fazer:");
                        //printar devices que temos nas casas e deixar o utilizador escolher o que quer alterar

                        System.out.println(
                                "0.Desliga todos os devices de uma casa.\n" +
                                        "1.Liga todos os devices de uma casa.\n" +

                                        "2.Desliga todos os devices de todas as casas.\n" +
                                        "3.Liga todos os devices de todas as casas.\n" +

                                        "4.Desliga todos os devices de uma divisao de uma casa\n" +
                                        "5.Liga todos os devices de uma divisao de uma casa\n" +

                                        "6.Desliga todos os devices de uma divisao de todas as casas\n" +
                                        "7.Liga todos os devices de uma divisao de todas as casas\n" +

                                        "8.Desligar um device apenas.\n" +
                                        "9.Ligar um device apenas.\n");
                        System.out.println("Opção: ");
                        int resposta3;
                        resposta3 = Integer.parseInt(this.input.next());
                        switch (resposta3) {
                            case 0:
                                System.out.println("\nIndique a casa que pretende desligar todos os seus dispositivos:");
                                for (int i = 0; i < donosCasasss.size(); i++)
                                    System.out.println(i + "->" + donosCasasss.get(i));
                                System.out.println("\nCasa [0-" + (donosCasasss.size() - 1) + "]: ");
                                String casaa = this.input.next();
                                int h1 = Integer.parseInt(casaa);
                                //obter a casa nos clientes
                                int h = donosCasasss.get(h1);
                                this.controller.alteraCasaOff(casasClientes, h);
                                break;
                            case 1:
                                System.out.println("\nIndique a casa que pretende ligar todos os seus dispositivos:");
                                for (int i = 0; i < donosCasasss.size(); i++)
                                    System.out.println(i + "->" + donosCasasss.get(i));
                                System.out.println("\nCasa [0-" + (donosCasasss.size() - 1) + "]: ");
                                casaa = this.input.next();
                                int h2 = Integer.parseInt(casaa);
                                //obter a casa nos clientes
                                int hh = donosCasasss.get(h2);
                                this.controller.alteraCasaOn(casasClientes, hh);
                                break;
                            case 2:
                                //é so isto
                                this.controller.alteraTudoparaOff(casasClientes);
                                break;
                            case 3:
                                //é só isto
                                this.controller.alteraTudoparaOn(casasClientes);
                                break;
                            case 4:
                                System.out.println("\nIndique a casa que pretende ver alterado os seus dispositivos: ");
                                for (int i = 0; i < donosCasasss.size(); i++)
                                    System.out.println(i + "->" + donosCasasss.get(i));
                                System.out.println("\nCasa [0-" + (donosCasasss.size() - 1) + "]: ");
                                casaa = this.input.next();
                                int h3 = Integer.parseInt(casaa);
                                int hhh = donosCasasss.get(h3);
                                System.out.println("\nIndique a divisao que pretende alterar:");
                                //ciclo que printa divisoes
                                this.controller.printaDivisoes(hhh);
                                String divisao1 = this.input.next();
                                this.controller.alteradivCasaOff(casasClientes, divisao1, hhh);
                                break;
                            case 5:
                                System.out.println("\nIndique a casa que pretende ver alterado os seus dispositivos: ");
                                for (int i = 0; i < donosCasasss.size(); i++)
                                    System.out.println(i + "->" + donosCasasss.get(i));
                                System.out.println("\nCasa [0-" + (donosCasasss.size() - 1) + "]: ");
                                casaa = this.input.next();
                                int h4 = Integer.parseInt(casaa);
                                int hhhh = donosCasasss.get(h4);
                                System.out.println("\nIndique a divisao que pretende alterar:");
                                //ciclo que printa as divisoes
                                this.controller.printaDivisoes(hhhh);
                                String divisao2 = this.input.next();
                                this.controller.alteradivCasaOn(casasClientes, divisao2, hhhh);
                                break;
                            case 6:
                                System.out.println("\nIndique a divisao que pretende alterar em todas as casas: ");
                                String divisao3 = this.input.next();
                                this.controller.alteraDivisaoOff(casasClientes, divisao3);
                                break;
                            case 7:
                                System.out.println("\nIndique a divisao que pretende alterar em todas as casas: ");
                                String divisao4 = this.input.next();
                                this.controller.alteraDivisaoOn(casasClientes, divisao4);
                                break;
                            case 8:
                                System.out.println("\nIndique o device que pretende desligar: ");
                                //printar ciclo de devices
                                this.controller.printDevices();
                                String id = this.input.next();
                                this.controller.alteraDeviceOff(casasClientes, id);
                                break;
                            case 9:
                                System.out.println("\nIndique o device que pretende ligar: ");
                                this.controller.printDevices();
                                String idd = this.input.next();
                                this.controller.alteraDeviceOn(casasClientes, idd);
                                break;
                            default:
                                System.out.println("\nOpção Inválida. Escolha um número entre 0 e 9.\n");
                        }
                        this.controller.simular(casasClientes, getDataInicial(), getDataFinal());
                        //guardamos a ultima data para ser usada novamente numa nova simulação
                        temp = getDataInicial();
                        setDataInicial(getDataFinal());
                        //guardo assim a data final para a proxima simulacao ser a inicial

                    } else if (resposta2.equals("n")) {
                        this.controller.simular(casasClientes, getDataInicial(), getDataFinal()); //apenas simulamos com a nova data final
                        temp=getDataInicial();
                        setDataInicial(getDataFinal());
                    } else throw new RespostaImpossivelException("\nDigite apenas 's' ou 'n'.\n");
                } else {
                    setDataFinal(getDataInicial());
                    setDataInicial(temp);

                }
            }while(resposta.equals("s"));
        }catch (SmartHomeJaExisteException e ){
            System.out.println("\nSmartHome já foi escolhida insira outra por favor.\n");
        }catch (SmartDeviceNaoExisteException e){
            System.out.println("\nSmartDevice nao existe.\n");
        } catch (DataImpossivelException e){
            System.out.println("\nDatas Impossíveis de comparar.\n");
        }catch (SimulacaoImpossivelException e){
            System.out.println("\nNão foi possivel realizar a simulação.\n");
        } catch (Exception e){
            System.out.println("\nOcorreu um erro.Tente novamente e insira os dados nos formatos pedidos.\n");
        }
    }

    public void informacao(){
        try{
            System.out.println("\nInformação de todas as casas no ficheiro carregado.\n");
            List<Integer> donosCasa = this.controller.donosCasas();
            Map<Integer,SmartHome> casasDosClientes = new HashMap<>();
            //guardar todas as casas num Map
            for(Integer dono: donosCasa){
                casasDosClientes.put(dono,this.controller.getCasas().get(dono));
            }
            this.controller.informar(casasDosClientes);
        }catch (SmartHomeNaoExisteException e){
            System.out.println("\nSmartHome inexistente.\n");
        }catch (Exception e){
            System.out.println("\nOcorreu um erro.\n");
        }
    }
    public void faturasporComercializador(){
        try{
            System.out.println("\nFaturas emitidas por um Comercializador:");
            System.out.println("\nInsira o Comercializador: ");
            String com = this.input.next();
            this.controller.emitirFaturasComercializador(com,getDataInicial(),getDataFinal());
        }catch (ComercializadorNaoExisteException e) {
            System.out.println("\nComercializador nao existe.");
        }catch (DataImpossivelException e) {
            System.out.println("\nDatas impossiveis de comparar.\n");
        } catch (SmartHomeNaoExisteException e) {
            System.out.println("\nSmartHome nao existe.");
        }catch (Exception e){
            System.out.println("\nOcorreu um erro.Já simulou alguma vez?\n");
        }
    }
}
