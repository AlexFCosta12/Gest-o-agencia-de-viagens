
package projetologin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Asus
 */
public class Autocarros implements java.io.Serializable {
    private String Matricula;
    private int capacidade;

    /**
     *
     * @param Matricula matricula do autocarro
     * @param capacidade capacidade do autocarro
     * @throws IOException
     */
    public Autocarros(String Matricula, int capacidade)throws IOException{
        this.Matricula=Matricula;
        this.capacidade = capacidade;
    }
/**
 * 
 * @return A matricula de um Autocarro 
 */
    public String getmatricula(){
        /*
        Retorna a matricula do autocarro
        */
        return Matricula;
    }
    /**
     * 
     * @return a capacidade do autocarro.
     */
    public int getcapacidade(){
        /*
        Retorna a capacidade do autocarro
        */
        return capacidade;
        
    }    

    /**
     *
     * @return o objeto autocarros
     */
    @Override
     public String toString(){
         return "Matricula: "+Matricula+"\nCapacidade:"+capacidade;
     }
}
