package Projeto;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

//tendo em conta que já foi criado um comercializador pelo nome apenas ( ou seja sem clientes)
//ao criar uma Casa no Controller esta ja tem nome,NIF e nome de Comercializador
//pelo que temos de ir  ao Map dos comercializadores, obter o Comercializador atraves do seu Nome e adicionar esta casa à lista de clientes



public class Parser {

    //criar Map ou lista de devices para dar parse e alterar depois no controller conforme seja necessario                                           //Galp,(Galp,clientes)
                        //NIF,Casa                       //Nome,Comercializador                                 //DivisaoJaExisteException nao precisei porque no parser utilizamos um outro metodo mas acho que irei muda lo
    public static void parse(Map<Integer, SmartHome> casas, Map<String, Comercializador> comercializadores,Map<String,SmartDevice> devices,String ficheiro) throws LinhaIncorretaException, SmartDeviceJaExisteException ,DivisaoNaoExisteException,DivisaoJaExisteException{
        List<String> linhas = lerFicheiro(ficheiro);
        String[] linhaPartida;

        //lista de devices so para guardar os id's de cada um para ao adiconar em casas diferentes nao repetir devices


        String divisao = null;
        SmartHome casaMaisRecente = null;
        SmartDevice sd;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch(linhaPartida[0]){
                case "Casa":
                    SmartHome c = SmartHome.parse(linhaPartida[1]);
                    casaMaisRecente = c;
                    casas.put(c.getNIF(),c);
                    divisao = null;  // casa criada de raiz sem divisoes
                    break;
                case "Divisao":
                    //problema com as divisoes acho, pois nao criamos subclasses nem nada disso ver depois
                    if (casaMaisRecente == null) throw new LinhaIncorretaException("Casa inexistente");
                    divisao = linhaPartida[1];
                    casaMaisRecente.adicionaDivisao(divisao);
                    break;
                case "SmartBulb":
                    if (casaMaisRecente == null) throw new LinhaIncorretaException("Casa inexistente");
                    if (divisao == null) throw new LinhaIncorretaException("Divisão inexistente");
                    sd = SmartBulb.parse(linhaPartida[1]);
                    if(devices.containsKey(sd.getId())) throw new LinhaIncorretaException("SamrtDevice já existe");
                    devices.put(sd.getId(),sd);

                    //adicionar a casa e na respetiva divisao
                    //so adiciona casa nao exista device ta correto, se existir ira retornar o erro que ja existe esse device

                    //if((devices.containsKey(sd.getId()))) throw new LinhaIncorretaException("Device ja existe");
                    //devices.put(sd.getId(),sd);
                    casaMaisRecente.addicionaDevice(sd);
                    casaMaisRecente.addicionaDevicenumaDivisao(sd,divisao);

                    break;
                case "SmartSpeaker":
                    if (casaMaisRecente == null) throw new LinhaIncorretaException("Casa inexistente");
                    if (divisao == null) throw new LinhaIncorretaException("Divisão inexistente");
                    sd= SmartSpeaker.parse(linhaPartida[1]);
                    if(devices.containsKey(sd.getId())) throw new LinhaIncorretaException("SamrtDevice já existe");
                    devices.put(sd.getId(),sd);

                    //casaMaisRecente.addDevice(sd);
                    //casaMaisRecente.addToRoom(divisao, sd.getId());
                    // ?????????
                    //if((devices.containsKey(sd.getId()))) throw  new LinhaIncorretaException("Device ja existe");
                    //devices.put(sd.getId(),sd);

                    casaMaisRecente.addicionaDevice(sd);
                    casaMaisRecente.addicionaDevicenumaDivisao(sd,divisao);
                    break;
                case "SmartCamera":
                    if (casaMaisRecente == null) throw new LinhaIncorretaException("Casa inexistente");
                    if (divisao == null) throw new LinhaIncorretaException("Divisão inexistente");
                    sd = SmartCamera.parse(linhaPartida[1]);
                    if(devices.containsKey(sd.getId())) throw new LinhaIncorretaException("SamrtDevice já existe");
                    devices.put(sd.getId(),sd);
                    //casaMaisRecente.addDevice(sd);
                    //casaMaisRecente.addToRoom(divisao, sd.getId());

                    //if((devices.containsKey(sd.getId()))) throw  new LinhaIncorretaException("Device ja existe");
                    //devices.put(sd.getId(),sd);

                    casaMaisRecente.addicionaDevice(sd);
                    casaMaisRecente.addicionaDevicenumaDivisao(sd,divisao);
                    break;
                case "Comercializador": //aqui apenas criamos o comercializador pelo nome depois teremos que à medida que se acrecentam as casas atualizar a lista de SmartHome
                    Comercializador comercializador = Comercializador.parse(linhaPartida[1]);
                    comercializadores.put(comercializador.getNome(),comercializador);
                    break;
                default:
                    throw new LinhaIncorretaException("Linha incorreta");
            }
        }
        System.out.println("done!");
    }


    //ou seja
    // primeiro criamos a casa no txt, depois divisao ->devices sempre assim
    // i.e, definimos a divisao que queremos e depois adicionamos todos os devices nessa divisao para assim formar a casa
    //os fornecedores é so adicionar e depois colocar ans casas





    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) {
            System.out.println("Erro ao ler o  ficheiro: " + exc);
            lines = new ArrayList<>();
        }
        return lines;
    }



    /*
    //adicionar o comercializador à casa mais recente
    casaMaisRecente.setComercializador(c.getComercializador());                                                     //adicionei isto ao parser hoje 18/05/2022
    List<SmartHome> casass = comercializadores.get(c.getComercializador().getNome()).getCasasClientes();                        //e
    casass.add(c);
    comercializadores.get(c.getComercializador().getNome()).setCasasClientes(casass);
    */
}
