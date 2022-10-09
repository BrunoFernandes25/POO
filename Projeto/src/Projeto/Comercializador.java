package Projeto;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Comercializador implements Serializable {

    public static final int PrecoBase = 2;
    public static final double Imposto = 0.23;
    /**
     * Instâncias
     */
    private String nome;
    //private double precoBase; //no arranque temos de saber estes dois valores ver como fazer isso depois
    //private double Imposto; //ex:0.23
    private List<SmartHome> casasClientes;
    //datas com LocalDate para emitir fatura e calcular total energia gasta


    /**
     * Construtores
     */

    public Comercializador(){   //default
        this.nome="";
        this.casasClientes = new ArrayList<>();
    }

    public Comercializador (String n, List<SmartHome> c){
        this.nome = n;
        this.casasClientes=c;
    }

    public Comercializador (Comercializador com){
        this.nome = com.getNome();
        this.casasClientes=com.getCasasClientes();
    }

    /**
     * como txt do stor, depois pedir ao utilizador para adicionar o resto
     */
    public Comercializador(String name){
        this.nome=name;
        this.casasClientes = new ArrayList<>();//normal dar vazio quando se cria
    }

    /**
     *  Getters
     */
    public String getNome() {
        return this.nome;
    }

    public List<SmartHome> getCasasClientes() {
        return this.casasClientes;
    }

    /**
     *  Setters
     */

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCasasClientes(List<SmartHome> casasClientes) {
        this.casasClientes = casasClientes;
    }

    /**
     *  equals
     */

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comercializador c = (Comercializador) o;
        return this.nome.equals(c.nome) && this.casasClientes.equals(c.casasClientes);
    }

    /**
     * hascode
     */
    public int hashCode() {
        int hash = 7;
        hash = 37*hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 37*hash + (this.casasClientes != null ? this.casasClientes.hashCode() : 0);

        return hash;
        //return Objects.hash(precoBase, Imposto, casasClientes);
    }

    /**
     * toString
     */
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("\nComercializador de Energia[");
        s.append("\nNome: ");
        s.append(this.nome);
        s.append("\nPreço Base de Energia: ");
        s.append(PrecoBase);
        s.append("\nImposto: ");
        s.append(Imposto);
        s.append("\nLista de Clientes:\n");
        s.append(this.casasClientes);
        s.append("\n]");

        return s.toString();
    }


    /**
     * metodos da classe Comercializador
     */

    public List<SmartHome> addCasasClientes(SmartHome sh){
        List<SmartHome> casas = getCasasClientes();
        casas.add(sh);
        setCasasClientes(casas);
        return casas;
    }


    //custo Energetico de uma certa casa[kWh]
    public double custodevices(SmartHome s) throws SmartHomeNaoExisteException {
        if(!casasClientes.contains(s)) throw new SmartHomeNaoExisteException("Não existe esta CasaCliente para este Comercializador.\n");
        // caso exista na lista  aplicar a formula conforme o numero de dispositivos
        Map<String,SmartDevice> devicesss = s.getDevices();
        //percorrer valores de devices e obter os custos
        List<SmartDevice> d = devicesss.values().stream().filter(disp->disp.isOn()).collect(Collectors.toList());
        //tentar filtrar so para os que estao ligados e depois sim obter o consumo

        double custodvcs = d.stream().mapToDouble(SmartDevice::consumoEnergia).sum();

        return  custodvcs;
    }

    //custo Energetico de uma certa casa[Preço diario €]
    public double custoEnergetico(SmartHome s) throws SmartHomeNaoExisteException{ //casa x na lista
        //verificar se é cliente
        if(!casasClientes.contains(s)) throw new SmartHomeNaoExisteException("Não existe esta CasaCliente para este Comercializador.\n");
        // caso exista na lista  aplicar a formula conforme o numero de dispositivos
        Map<String,SmartDevice> devicesss = s.getDevices();
        //percorrer valores de devices e obter os custos
        List<SmartDevice> d = devicesss.values().stream().filter(disp->disp.isOn()).collect(Collectors.toList());
        //tentar filtrar so para os que estao ligados e depois sim obter o consumo

        double custodvcs = d.stream().mapToDouble(SmartDevice::consumoEnergia).sum();
        //podia so ter chamado o metodo acima mas yaa fica assim

         //double custoCom = custoComercializador();
        return (double)(PrecoBase * custodvcs*(1+Imposto))*0.9; //tirei o custoCom(); para nao me atrofiar o cerebro
    }

    public double custoENergeticoTotal(SmartHome sh, LocalDate d1, LocalDate d2) throws SmartHomeNaoExisteException,DataImpossivelException {

        //d1 = LocalDate.of(2020,10,5);
        //d2 = LocalDate.of(2020,9,10);

        if(d1.compareTo(d2)>=0) throw new DataImpossivelException("Datas impossiveis de comparar.\n");
        long dias = ChronoUnit.DAYS.between(d1,d2); //26   colocar semrpe data maior no inicio senao fazer o simetrico
        int d = ((int) dias);
        double  custoenergetico= Math.round(((d+1)*custoEnergetico(sh)) * 100.0) / 100.0;
        return custoenergetico;
    }

    public double TotalFaturacao() throws SmartHomeNaoExisteException {
        double maxFaturacao=0.0;
        for(SmartHome sh : this.casasClientes){
            maxFaturacao += custoEnergetico(sh);
        }
        return maxFaturacao;
    }
    public long contrato(LocalDate d1, LocalDate d2){
        return 1 + ChronoUnit.DAYS.between(d1,d2);
    }

    //quando se avancar no tempo
    public String emiteFatura(SmartHome ss,LocalDate d1,LocalDate d2) throws SmartHomeNaoExisteException, DataImpossivelException {
        StringBuilder sbb = new StringBuilder();
        sbb.append("\n-------------------------------------\n");
        sbb.append("[Fatura]");
        sbb.append("\n->Nome Cliente: ");
        sbb.append(ss.getName());
        sbb.append("\n->NIF: ");
        sbb.append(ss.getNIF());
        sbb.append("\n->Comercializador: ");
        sbb.append(ss.getComercializador().getNome());
        sbb.append("\n->Preço Base aplicado(€): ");
        sbb.append(PrecoBase);
        sbb.append("\n->Imposto aplicado: ");
        sbb.append(Imposto);
        sbb.append("\n->Período de contrato: ");
        sbb.append(contrato(d1,d2));
        sbb.append("\n->Custo diário(em KWh):");
        sbb.append(custodevices(ss));
        sbb.append("\n->Custo diário(em €): ");
        sbb.append(custoEnergetico(ss));
        sbb.append("\n->Custo Energético Total(em €): ");
        sbb.append(custoENergeticoTotal(ss,d1,d2));
        sbb.append("\n-------------------------------------\n");

        return sbb.toString();
    }

    public static Comercializador parse(String input){
        String[] campos = input.split(",");

        return new Comercializador(campos[0]); // passamos apenas o nome do Comercializador no ficheiro
    }

    public void escrever(FileWriter writer) throws IOException {
        writer.write("Comercializador:");
        writer.write(this.nome);


        //se for como o stor tem no ficheiro dele de texto nao precisamos do de baixo senao usaremos
        //List<SmartHome> casasClientes;
        /*
        for(SmartHome sh: this.casasClientes){
            writer.write(sh);
        }*/
        writer.write("\n");
        writer.flush();
    }

}

//merda que nao calculei foi -> as formulas de custoEnergia podem ser diferentes e neste caso sao todas iguais para qualquer fornecedor de Energia