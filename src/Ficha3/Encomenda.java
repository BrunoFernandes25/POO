package Ficha3;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public class Encomenda {
    private String nomeCliente;
    private int numFiscal;
    private String morada;
    private int numEncomenda;
    private LocalDateTime dataEncomenda;
    private LinhaEncomenda[] linhasEncomenda;


    public Encomenda() {
        this.setNomeCliente("");
        this.setNumFiscal(0);
        this.setMorada("");
        this.setNumEncomenda(0);
        this.setDataEncomenda(LocalDateTime.now());
        this.linhasEncomenda = new LinhaEncomenda[0];
    }

    public Encomenda(String nomeCliente, int numFiscal, String morada, int numEncomenda, LocalDateTime dataEncomenda) {
        this.nomeCliente = nomeCliente;
        this.numFiscal = numFiscal;
        this.morada = morada;
        this.numEncomenda = numEncomenda;
        this.dataEncomenda = dataEncomenda;
        this.linhasEncomenda = new LinhaEncomenda[0];
    }

    public Encomenda(Encomenda umaEncomenda) {
        this.nomeCliente = umaEncomenda.getNomeCliente();
        this.numFiscal = umaEncomenda.getNumFiscal();
        this.morada = umaEncomenda.getMorada();
        this.numEncomenda = umaEncomenda.getNumEncomenda();
        this.dataEncomenda = umaEncomenda.getDataEncomenda();
        this.linhasEncomenda = umaEncomenda.getLinhasEncomenda();
    }

    public boolean equals(Object umaEncomenda) {
        if (this == umaEncomenda) {
            return true;
        }
        if (umaEncomenda == null || getClass() != umaEncomenda.getClass()) {
            return false;
        }
        Encomenda encomenda = (Encomenda) umaEncomenda;
        return numFiscal == encomenda.numFiscal && numEncomenda == encomenda.numEncomenda
                && Objects.equals(nomeCliente, encomenda.nomeCliente) && Objects.equals(morada, encomenda.morada)
                && Objects.equals(dataEncomenda, encomenda.dataEncomenda) && Arrays.equals(linhasEncomenda, encomenda.linhasEncomenda);
    }

    public String toString() {
        return "Encomenda{" +
                "\n nomeCliente='" + nomeCliente + '\'' +
                "\n numFiscal=" + numFiscal +
                "\n morada='" + morada + '\'' +
                "\n numEncomenda=" + numEncomenda +
                "\n dataEncomenda=" + dataEncomenda +
                "\n linhasEncomenda=" + Arrays.toString(linhasEncomenda) +
                "\n}\n";
    }

    public Encomenda clone() {
        return new Encomenda(this);
    }
    public String getNomeCliente() {
        return nomeCliente;
    }

    public int getNumFiscal() {
        return numFiscal;
    }

    public String getMorada() {
        return morada;
    }

    public int getNumEncomenda() {
        return numEncomenda;
    }

    public LocalDateTime getDataEncomenda() {
        return dataEncomenda;
    }

    public LinhaEncomenda[] getLinhasEncomenda() {
        return linhasEncomenda;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setNumFiscal(int numFiscal) {
        this.numFiscal = numFiscal;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setNumEncomenda(int numEncomenda) {
        this.numEncomenda = numEncomenda;
    }

    public void setDataEncomenda(LocalDateTime dataEncomenda) {
        this.dataEncomenda = dataEncomenda;
    }

    public void setLinhasEncomenda(LinhaEncomenda[] linhasEncomenda) {
        this.linhasEncomenda = linhasEncomenda;
    }

    //b)
    public double calculaValorTotal() {
        double precoTotal = 0;

        for(LinhaEncomenda l : this.getLinhasEncomenda()) {
            precoTotal += l.calculaValorLinhaEnc();
        }
        return precoTotal;
    }

    //c)
    public double calculaValorDesconto() {
        double descontoTotal = 0;

        for(LinhaEncomenda l : this.getLinhasEncomenda()) {
            descontoTotal += l.calculaValorDesconto();
        }

        return descontoTotal;
    }

    //d)
    public double numeroTotalProdutos() {
        int numTotal = 0;

        for(LinhaEncomenda l : this.getLinhasEncomenda()) {
            numTotal += l.getQuantidade();
        }

        return numTotal;
    }

    //e)
    public boolean existeProdutoEncomenda(String referencia) {
        for (LinhaEncomenda l : this.getLinhasEncomenda()){
            if(l.getReferencia().equals(referencia)) {
                return true;
            }
        }
        return false;
    }

    //f)
    public void adicionaLinha(LinhaEncomenda linha) {
        LinhaEncomenda[] encomendas = this.getLinhasEncomenda();
        LinhaEncomenda[] encomendasatualizadas = new LinhaEncomenda[this.linhasEncomenda.length + 1];
        System.arraycopy(encomendas,0,encomendasatualizadas,0,this.linhasEncomenda.length);
        encomendasatualizadas[this.linhasEncomenda.length] = linha;
        this.setLinhasEncomenda(encomendasatualizadas);
    }

    //g)
    public void removeProduto(String codProd) {
        // não vamos remover nada se não houver nada a remover
        if (this.linhasEncomenda.length > 0) {
            LinhaEncomenda[] encomendas = this.getLinhasEncomenda();
            LinhaEncomenda[] encomendasatualizadas = new LinhaEncomenda[encomendas.length - 1];
            boolean found = false;

            int index = -1;
            for (int i = 0; i < encomendas.length && !found; i += 1) {
                if (encomendas[i].getReferencia().equals(codProd))
                    found = true;
                index = i;

            }

            System.arraycopy(encomendas, 0, encomendasatualizadas, 0, index);
            System.arraycopy(encomendas, index + 1, encomendasatualizadas, index, encomendas.length - index - 1);
            this.setLinhasEncomenda(encomendasatualizadas);
        }
    }

}
