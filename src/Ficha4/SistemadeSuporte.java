package Ficha4;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.stream.Collectors;

public class SistemadeSuporte {
    ArrayList<PedidoSuporte> pedidos;

    public SistemadeSuporte() {
        this.pedidos= new ArrayList<PedidoSuporte>();
    }

    public SistemadeSuporte(ArrayList<PedidoSuporte> pedidos) {
        this.pedidos = new ArrayList<PedidoSuporte>();
        for (PedidoSuporte pedido: pedidos)
            this.pedidos.add(pedido.clone());
    }

    public SistemadeSuporte(SistemadeSuporte sist) {
        this.pedidos=sist.clone().getPedidos();
    }

    public ArrayList<PedidoSuporte> getPedidos() {
        return this.pedidos;
    }

    public void setPedidos(ArrayList<PedidoSuporte> pedidos) {
        this.pedidos = pedidos;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SistemadeSuporte that = (SistemadeSuporte) o;
        return Objects.equals(pedidos, that.pedidos);
    }


    public String toString() {
        return "SistemadeSuporte {" +
                "pedidos = " + pedidos +
                '}';
    }

    public SistemadeSuporte clone(){
        return new SistemadeSuporte(this);
    }

    //i
    public void inserePedido(PedidoSuporte pedido) {
        this.pedidos.add(pedido.clone());
    }

    //ii
    //mudei no PedidoSuport LocalTime para LocalDataTime para poder usar equals
    public PedidoSuporte procuraPedido(String user, LocalDateTime data) {
        return (PedidoSuporte) this.pedidos.stream().filter(p -> (p.getNome().equals(user)) && (p.getInstantePedido().equals(data))).collect(Collectors.toList());  //se nao der bem colocar == nas datas
    }

    //iii
    public void resolvePedido(PedidoSuporte pedido, String tecnico, String info) {
        boolean resolvido = false;
        int i;
        for (i = 0;i<this.pedidos.size();i++) { //percorremos os pedidos todos até acharmos o que pretendemos
            resolvido = this.pedidos.get(i).equals(pedido);
        }
            if(resolvido) { //caso se encontre resolvido entao decrementamos i e seguimos
                i--;
            }
            else{   //senao
                this.pedidos.add(pedido);   //adiciona se o pedido
                i = this.pedidos.size() -1; // e colocamos i a sinalizar a ultima posicao para depois inserir la o que pretendemos
            }

            pedido.setInformatico(tecnico);
            pedido.setInformacaoDoProblema(info);
            pedido.setInstanteConclusao(LocalDateTime.now());
            this.pedidos.set(i,pedido);
    }

    //iv
    //iteradores internos
    public List<PedidoSuporte> resolvidos() {
        //usei filter e nao map porque senao iriamos retornar bools e nos queremos filtrar algo do tipo PedidoSuporte, ou seja, se for resolvido(True) guarda esse PedidoSuporte e nao o True
        return this.pedidos.stream().filter(PedidoSuporte::isResolvido).collect(Collectors.toList());
        // |ou| return this.pedidos.stream().filter(p -> !(p.getAssunto().equals("")) && !(p.getInformatico().equals(""))).collect(Collectors.toList());
    }
    //ou seja se existir assunto e informatico o problema foi resolvido

    //iteradores externos
    public List<PedidoSuporte> resolvidos2() {
        List<PedidoSuporte> p = new ArrayList<PedidoSuporte>();
        ListIterator<PedidoSuporte> it = this.pedidos.listIterator();
        while(it.hasNext()){
            PedidoSuporte ped = it.next();
            if(ped.isResolvido()) {
                p.add(ped);
            }
        }
        /* |ou|
        for (PedidoSuporte ped : this.pedidos) {
            if (ped.isResolvido()) {
                p.add(ped);
            }
        }
        */
        return p;
    }

    //v
    //internos
    public String colaboradorTop() {          //se fosse () p estaria sempre  vazio nao daria para comparar
        ArrayList<PedidoSuporte> p = new ArrayList<PedidoSuporte>(this.pedidos);
        int max = -1;
        String informatico = "";
        int contaresolv;
        for(PedidoSuporte ped: p){
            contaresolv = (int) p.stream().filter(a -> a.getInformatico().equals(ped.getInformatico())).count();
            if(contaresolv > max) {
                max = contaresolv;
                informatico = ped.getInformatico();
            }
        }

        return informatico;
    }
    //externos
    private int count(ArrayList<PedidoSuporte> lista, String tecnico){
        int num = 0;
        ListIterator<PedidoSuporte> iter = lista.listIterator();
        while(iter.hasNext()){
            PedidoSuporte ped = iter.next();
            if (ped.getInformatico().equals(tecnico)){
                num += 1;
            }
        }
        return num;
    }

    public String colaboradorTopExt2(){
        ListIterator<PedidoSuporte> it = this.pedidos.listIterator();
        int maxNumResol = 0;
        int res;
        String informatico = "";
        while(it.hasNext()){
            PedidoSuporte ped = it.next();
            res=count(this.pedidos,ped.getInformatico());
            if (res > maxNumResol){
                maxNumResol = res;
                informatico = ped.getInformatico();
            }
        }
        return informatico;
    }

    //vi


}