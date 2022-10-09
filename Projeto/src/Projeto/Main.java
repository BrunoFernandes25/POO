package Projeto;

import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in).useDelimiter("\n"); //enter termina com o input
        Map<Integer,SmartHome> casas = new HashMap<>();
        Map<String,Comercializador> comercializadores = new HashMap<>();
        Map<String,SmartDevice> devices = new HashMap<>();

        Controller controller = new Controller(casas,comercializadores,devices);
        View view = new View(controller,input);

        view.run();
    }
}
