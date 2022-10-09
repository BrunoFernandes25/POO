package Projeto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SaveSimulacao {
    public static void gravar(Map<Integer,SmartHome> casas,Map<String,Comercializador> comercializadores,String ficheiro) throws IOException{
        FileWriter writer = new FileWriter(ficheiro);

        for(Comercializador com: comercializadores.values()){
            com.escrever(writer);
        }

        for(SmartHome sh: casas.values()) {
            sh.escrever(writer);
            sh.escreverdivs(writer);
            //obter todos os devices da casa
            //List<SmartDevice> sd = sh.getDevices().values().stream().collect(Collectors.toList());
            Map<String,SmartDevice> sd = sh.getDevices(); //obtemos o Map de devices numa casa
            for(SmartDevice device: sd.values()){  // para cada device nessa casa acedemos aos values(SmartDevices) e escrevemo-los
                device.escreverdevs(writer);
            }
        }

        writer.close();
    }
}
