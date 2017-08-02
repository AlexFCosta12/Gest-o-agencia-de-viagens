
package projetologin;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

class reservas implements java.io.Serializable {
    static Scanner input = new Scanner(System.in);
  
    protected Viagens v;
    protected Cliente cliente;
    protected double valor;
    protected Data data;
    protected int lugar;
    public reservas(Cliente cliente, Viagens v, double valor, Data data)throws IOException{
        this.valor=valor;
        this.v=v;
        this.data = data;
        this.cliente = cliente;

        
        
    }
    
    @Override
    public String toString(){
       return "\nViagem:"+v+"\nData:"+data+"\nLugar:"+lugar;
    }
    public Data getData(){
        /*
        Returna a data em que foi efetuada a reserva.
        */
        return data;
    }
    public Viagens getViagem(){
        /*
        Returna a viagem da reserva.
        */
        return v;
    }
    public int getLugar (){
        /*
        Retorna o lugar no autocarros da reserva.
        */
        return lugar;
    }
    public Cliente getCliente(){
        /*
        Retorna o cliente da reserva.
        */
        return cliente;
    }

}
class cancelada extends reservas implements java.io.Serializable{
    cancelada(Cliente cliente,Viagens v,double valor, Data data) throws IOException{
       super (cliente,v,valor,data);
       v.retiraLugar(cliente);
       
   }
}
class valida extends reservas implements java.io.Serializable{
   valida (Cliente cliente,Viagens v, double valor,Data data,int lugar) throws IOException{
        super (cliente,v,valor,data);
        this.lugar =lugar;

    }
}
class espera extends reservas implements java.io.Serializable{
     espera(Cliente cliente,Viagens v , double valor,Data data) throws IOException{
        super (cliente,v,valor,data);
        
        
     }  
}
