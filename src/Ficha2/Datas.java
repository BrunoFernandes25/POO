package Ficha2;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Datas {
    private LocalDate[] data;
    private int numinseridos;
    private int tam;


    public Datas(int tam) {     // array data
        this.numinseridos=0;    // 0 datas inseridas
        this.tam= this.tam;
        this.data=new LocalDate[this.tam];   // tamanho do array criado igual ao que nos quisermos passar
    }

    //3.1
    public void insereData(LocalDate data) {
        if(numinseridos<tam) {
            this.data[this.numinseridos++]=data;
        }
    }

    //3.2
    public LocalDate dataMaisProxima(LocalDate data) {
        long minDist = ChronoUnit.DAYS.between(this.data[0], data);
        int i;
        int index = 0;

        for(i=0; i<this.numinseridos; i+= 1){
            long time = ChronoUnit.DAYS.between(this.data[i], data);
            if(time < minDist) {
                index = i;
                minDist = time;
            }
        }

        return this.data[index];
    }

    // 3. c)
    public String toStringg(){
        String string = "";
        int i;

        for(i=0; i<this.numinseridos; i+=1)
            string += this.data[i] + "\n";

        return string;
    }
}
