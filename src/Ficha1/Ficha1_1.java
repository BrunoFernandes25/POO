package Ficha1;

import java.time.LocalDateTime; //Classe para o tratamento e registo de informação com data e tempo

/*
 *Criar um objecto com o valor do instante actual:
 * LocalDateTime currentDateTime = LocalDateTime.now();
 *
 *Criar um objecto com o valor de uma dada data/hora (ex: 2016-12-24 12:30):
 * LocalDateTime natal2016 = LocalDateTime.of(2016, 12, 24, 12, 30);
 *
 *O dia de Natal do ano passado:
 * LocalDateTime natal2021 = LocalDateTime.of(2021, Month.DECEMBER, 24, 12, 0);
 *
 *Exemplo de utilização das várias classes sobre data e hora:
 * LocalDate date = LocalDate.of(2022, 2, 21); // 2022-02-21
 * boolean isBefore = LocalDate.now().isBefore(date); // false
 *
 * //information about the month
 * Month february = date.getMonth(); // FEBRUARY
 * int februaryIntValue = february.getValue(); // 2
 * int minLength = february.minLength(); // 28
 * int maxLength = february.maxLength(); // 29
 * Month firstMonthOfQuarter = february.firstMonthOfQuarter(); // JANUARY
 *
 * //information about the year
 * int year = date.getYear(); // 2022
 * int dayOfYear = date.getDayOfYear(); // 52
 * int lengthOfYear = date.lengthOfYear(); // 365
 * boolean isLeapYear = date.isLeapYear(); // false
 *
 * DayOfWeek dayOfWeek = date.getDayOfWeek();
 * int dayOfWeekIntValue = dayOfWeek.getValue();
 * String dayOfWeekName = dayOfWeek.name(); // MONDAY
 *
 * int dayOfMonth = date.getDayOfMonth(); // 21
 * LocalDateTime startOfDay = date.atStartOfDay(); // 2022-02-21 00:00
 *
 * // time information
 * LocalTime time = LocalTime.of(15, 30); // 15:30:00
 * int hour = time.getHour(); // 15
 * int second = time.getSecond(); // 0
 * int minute = time.getMinute(); // 30
 * int secondOfDay = time.toSecondOfDay(); // 55800
 * */

/*
* pensar na representacao dos dias da semana e respetivos dias que cada mes contem
* considerando que fevereiro tenha 28
* */

//static é o que nos permite aceder aos metodos, pois serao estaticos tambem

public class Ficha1_1 {

    public static final int[] diasNumMes={31,28,31,30,31,30,31,31,30,31,30,31}; //se tiver private apenas poderei usar este metodo dentro desta classem, o que nao me ajudará a usar a mesma main da ficha1
    private static final String[] diaDaSemana= {"Domingo","Segunda","Terça","Quarta","Quinta","Sexta","Sábado"};

    public static String diaSemana(int ano,int mes, int dia) {
        int conta = 0; // inicializamos a variavel que conta os dias que passaram a 0
        if (ano%4==0 && mes<=2) conta--;  // (iii) ano bissexto divisivel por 4 && mes<=2 -> janeiro ou fevereiro
        for (int i = 0; i<mes-1; i++) {  //mes de indice 0(mes1) até ao mes que estamos
            conta += diasNumMes[i]; // (iv)
        }                                                   //conta -> contem os dias dos meses todos até ao mes anterior ao do mes passado na funcao + dia -> dias do mes(passado por nos) que ja passaram no calendario
        return diaDaSemana[((ano-1900)*365 + (ano-1900)/4 + conta + dia)%7]; // (i) + (ii) + (iii)/(iv) + dia
    }
    /* ou de uma forma diferente..
    public int dayOfTheWeek(int day, int month, int year) {
        int totalDays = (int)((year - 1900) * 365.25);
        if(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0) && month < 3) totalDays--;
        for(int i = month; i > 0; i--) {
            if(i == 2) totalDays += 28;
            else if(i == 4 || i == 6 || i == 9 || i == 11) totalDays += 30;
            else totalDays += 31;
        }
        return totalDays % 7;
    }
    * */
    public static String somaDatas(int[] d1, int[] d2) {
        int tmp;
        tmp = d1[3] + d2[3]; //soma se os segundos
        int s = tmp%60; // tem se em conta caso ultrapasse o limite 60 ficando apenas com o resto (ex:60+22 segundos dá 22 segundos e nao 82)
        tmp = d1[2] + d2[2] + tmp/60;   // soma-se agora os minutos mais os segundos(em minutos) que restem (se olharmos para o exemplo de cima 82/60=1, tendo no total 82 segundos= 1 minuto e 22 segundos)
        int m = tmp%60; // o mesmo acontece aqui tal como nos segundos, limite dos minutos é 60 (ex: 33+50 dá 23 minutos e não 83)
        tmp = d1[1] + d2[1] + tmp/60;   // soma-se entao as horas e o resultado da divisao exata dos minutos(em horas) (olhando para cima novamente, 83/60=1, somando se assim 1 hora, tendo somado no total os 83 minutos= 1h e 23 min)
        int h = tmp%24; // as horas sao identicas aos minutos e segundos, com a exceção de o limite ser 24 (ex: 20+15 horas dá 11 e nao 35)
        int d = d1[0] + d2[0] + tmp/24; // soma-se por fim os dias(sem limite) e as horas(em dias) (olha se para o exemplo de cima e 35/24=1, dando assim 35 horas= 1 dia e 11 horas)
        return d + "D " + h + "H " + m + "M " + s + "S";
    }

    public static int[] classificacoes (int[] notas) {
    int[] classificacao = {0,0,0,0};  //ordenar notas de 5 em 5 valores
        for (int i : notas) { //ciclo for para percorrer array notas
            if (i==20)  //caso i seja 20 decrementamos 1 para que a divisao inteira funcione abaixo
                i--;
            classificacao[i/5]+=1; // aumenta 1 nota na posicao pretendida
        }
    return classificacao;
    }
    public static String temperaturas (int[] temps){      // String pois o nosso return será desse modo
        int i;
        int tam = temps.length;
        int conta = 0; // iniciamos o contador a 0 que ira guardar o total das temperaturas
        int max= temps[1]- temps[0];    //i+1 = 1 e i= 0
        int indice=0;
        for(i=0;i<tam;i++){
            conta+=temps[i];
        }
        for (i=0;i<tam-2;i++) { //ciclo que percorre a lista de temperaturas || aqui comeca o i em 1 oara
            //conta+=temps[i];  somamos as temperaturar a medid que se percorre o ciclo para assim podermos fazer a media, contundo com o caso de paragem deste ciclo for que usei para calcular os indices nao irá dar correta a media visto nao terminar o "ciclo dos indices"
            if (Math.abs(max)<Math.abs(temps[i+2] - temps[i+1])) { // vamos analisar a cada caso se essa temperatura e a anterior tem a maior variação até ao momento registada
                max = temps[i+2]-temps[i+1];
                indice= i+1;  //fica com a posicao do primeiro dia para depois retornar o indice deste dia e do seguinte
            }
        }       //podia ter feito tudo num ciclo for, mas nao me apeteceu

        String var; // criamos algo que nos permita decidir se a temperatura subiu ou desceu para colocar como string no return
        if (max<0) var = "descido";
        else var= "subido";

        return "A média das " + tam + " temperaturas foi de " + conta/tam + " graus.\nA maior variação registou-se entre os dias " + (indice+2) + " e " + (indice+3) + ", tendo a temperatura " + var + " " + Math.abs(max)+ " graus.";   //subido/descido bastou comparar se a temperatura teve uma diferenca positiva ou nao
    }

    public static String calculaidade (int ano, int mes, int dia) {   // data de nascimento
        int year,month,day,hour;                //considere se que todos os anos tem 365 dias
        LocalDateTime atuadata = LocalDateTime.now(); //obter data atual
        year = atuadata.getYear(); //obtem ano da data atual
        month = atuadata.getMonthValue(); //obtem mes da data atual
        day = atuadata.getDayOfMonth(); // obtem dia da data atual
        hour = atuadata.getHour(); // obtem hora da data atual
        int conta = (year- ano -1 )*365 + (year- ano -1)/4; //    primeira parcela da idade real da pessoa falta somar dias de anos bissextos ,somar os dias de todos os anos exceto o 1º e o último
        for(int i=mes;i<12;i++){      //falta somar entao os dias desde o nascimento até ao fim desse mesmo ano
            conta+= diasNumMes[i]; // definido no inicio desta ficha para poder ser calculado agora
        }
        conta+= diasNumMes[mes]-dia; // falta somar dias do mes do nascimento que passaram
       for (int i=0;i<month-1;i++) {  // somar dias deste ano em questao, month-1 senao iria contabilizar o mes total podendo nos estar apenas a meio do mes, a contagem dos dias que faltam encontra se abaixo
           conta+=diasNumMes[i];
       }
        conta+=day-1;    //somar dias do ano atual atraves da funcao usada do diadoanoatual acima (day =...)

        conta*=24; // dias convertido em horas
        conta+=hour; // somando a hora atual do dia em questao
        return "Tudo calculado  tem-se " + conta + " horas desde o nascimento " + ano + "/" + mes + "/" + dia + " e a data " + atuadata.getDayOfMonth() + "/" + month + "/" + year + " às " + hour + " horas.";
    }
}
