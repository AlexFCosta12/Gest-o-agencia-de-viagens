
package projetologin;

import java.io.IOException;

/**
 *
 * @author Asus
 */
public class Data implements java.io.Serializable {
    private String Dia;
    private String mes;
    private String ano;
    
    /**
     *
     * @param Dia Dia 
     * @param mes Mes
     * @param ano Ano
     * @throws IOException
     */
    public Data(String Dia, String mes, String ano )throws IOException{
        this.Dia = Dia;
        this.mes = mes;
        this.ano = ano;
    }

    /**
     *
     * @return um objeto do tipo data
     */
    @Override
    public String toString (){
        return ""+Dia+"/"+mes+"/"+ano;
    }
    /**
     * compara duas data e retorna o numero de dias passados entre elas
     * @param data_viagem Data da viagem
     * @param data_reserva Data de reserva.
     * @return Retorna os disas que passarm.
     */
    public int DiferencaData(Data data_viagem, Data data_reserva){
        /*
        compara duas data e retorna o numero de dias passados entre elas
        */
            int mes_aux= data_reserva.getMes();
            int mes_reserva=data_reserva.getMes()-1;
            int dias = 0;
            int dias_final =0;
            int dias_totais=0;
            if(mes_aux == 1){
                dias=365-data_reserva.getDia();
            }else if(mes_aux==2){
                dias = 334-data_reserva.getDia();
            }else if(mes_reserva>2 && mes_reserva<8){
                 dias=365-data_reserva.getDia()-31*((mes_aux/2))-30*((mes_aux/2)-1)-28;
            }else if(mes_reserva>=8){
                if(mes_aux%2!=0){
                    dias=365-data_reserva.getDia()-212-(((mes_reserva-8)/2)+1)*31-((((mes_aux-8)/2)))*30;
                }else{
                   dias=365-data_reserva.getDia()-212-(((mes_aux-8)/2))*31-((((mes_aux-8)/2)))*30;
                }}
            mes_aux=data_viagem.getMes();
            int mes1=data_viagem.getMes()-1;    
            if(mes1<=2){
                dias_final=data_viagem.getDia()+31;
                if(mes1==2){
                    dias_final+=28;
                }}
            else if(mes1>2 && mes1<8){
                dias_final=data_viagem.getDia()+31*(mes_aux/2)+30*((mes_aux/2)-2)+28;
            }else if(mes1>=8){
                if(mes_aux%2!=0){
                    dias_final=data_viagem.getDia()+212+(((mes1-8)/2)+1)*31+((((mes_aux-8)/2)))*30;
                }else{
                    dias_final=data_viagem.getDia()+212+(((mes_aux-8)/2))*31+((((mes_aux-8)/2)))*30;
                }}
            if(data_viagem.getAno()!=data_reserva.getAno()){
                dias_totais=dias_final+dias+(data_viagem.getAno()-data_reserva.getAno()-1)*365+1;
            }else{      
                dias_final=365-dias_final;
                dias_totais= dias - dias_final;
            }
            return dias_totais;
    }

    /**
     *
     * @return mes
     */
    public int getMes(){
        /*
        Retorna o mes em inteiro
        */
        return Integer.parseInt(mes);
    }

    /**
     *
     * @return dia
     */
    public int getDia(){
        /*
        Retorna o dia em inteiro
        */
        return Integer.parseInt(Dia);
    }

    /**
     *
     * @return ano
     */
    public int getAno (){
        /*
        Retona o ano em inteiro
        */
        return Integer.parseInt(ano);
    
}
   
}


