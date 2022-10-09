package Projeto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Simulacao { //agregaçao pois irei usar metodos da classe Comercializador (Fatura) e da classe Casa(custos total dos devices)
    //Necessario aqui:
    /*
     * Avancar no tempo e emitir a fatura desse tempo(diferenca entre dias)
     * guardar essa nova data para depois avancar mos novamente
     * ser possivel alterar o estado dos devices de uma casa
     *
     *
     * Cada casa já tem obrigatoriamente dispositivos e comercializador
     * */

    //private LocalDate dInicial;    //data inicial a pedir para começar a simulaçao
    //private LocalDate dAtual;       //data que irá guardar cada dFinal a cada pedido de avançar no tempo
    //private LocalDate dFinal; //data a pedir para inserir
    //private Map<String, List<SmartHome>> clientes;

    //galp,casas
   /* public Simulacao(Map<String,Comercializador> clientes, LocalDate dInicial, LocalDate dFinal) throws NullPointerException {
        //calcular custos de cada casa e emitir respetiva fatura(ataves do view.printFaturas ou algo assim)
        //a cada Comercializador, emitir faturas de todas as casas
        //obter lista de clientes e para cada uma calcular gastos
        for(Comercializador c : clientes.values()){
            List<SmartHome> clientesComercializador = c.getCasasClientes();
            for(SmartHome casa: clientesComercializador){
                c.emiteFatura(casa,dInicial,dFinal);
                //casa.custoDevices(casa.getDevices()); //obtemos da casa x o custo dos devices todos
                //casa.getComercializador().emiteFatura(casa); // emitimos a fatura da casa x // sim nao usamos o view neste caso mas amanha vejo isto melhor
            }
        }

        //Comercializador consegue calcular o preco total de cada casa e emitir fatura logo ver acima


    }

        //data inical depois  igaual se a final e pede se novamente a final
        //ir as casas todas de um comercializador e aplicar custoEnergetico e emitir fatura
        //alterar estado dos devices
*/
    private LocalDate DataInicial;

    public LocalDate getDataInicial() {
        return DataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        DataInicial = dataInicial;
    }
}