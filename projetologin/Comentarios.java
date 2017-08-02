
package projetologin;

import java.io.IOException;

/**
 *
 * @author Asus
 */
public class Comentarios  implements java.io.Serializable {
    private Viagens viagem;
    private Cliente cliente;
    private String Comentario;
    private int pontuacao;

    /**
     *
     *@param viagem Viagem 
     * @param cliente Cliente
     * @param Comentario Comentario
     * @param pontuacao Pontuação
     * @throws IOException
     */
    public Comentarios (Viagens viagem,Cliente cliente,String Comentario,int pontuacao)throws IOException{
        this.viagem = viagem;
        this.cliente =cliente;
        this.Comentario = Comentario;
        this.pontuacao = pontuacao;
    }

    /**
     *
     * @return toString 
     */
    @Override
    public String toString (){
        return "Cliente:"+cliente.getNome()+"->Comentario:"+Comentario+"->Pontuação:"+pontuacao+"\n";
    }

    /**
     *Retorna a Viagem
     * @return viagem
     */
    public Viagens getViagem(){
        return viagem;
    } 

    /**
     *Retorna a pontuação da viagem
     * @return pontuação
     */
    public int getPontuação(){
        return pontuacao;
    }
}

