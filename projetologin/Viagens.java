
package projetologin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class Viagens implements java.io.Serializable{
    private int Codigo;
    private String Origem;
    private String Destino;
    private Data Data_inicio;
    private Data Data_fim;
    private double preco;
    private Autocarros auto;
    private Cliente [] lugares;
    private List <Comentarios> lista_comentarios;

    /**
     *
     * @param Codigo codigo (int)cada viagem tem um unico codigo
     * @param Origem Local da origem da viagem
     * @param Destino Local do destino da viagem.
     * @param Data_inicio Data de inicio da viagem
     * @param Data_fim Data fim de Viagem
     * @param preco int preco da viagem
     * @param auto Autocarros associado à viagem
     * @throws IOException
     */
    public Viagens(int Codigo,String Origem,String Destino, Data Data_inicio, Data Data_fim, double preco, Autocarros auto)throws IOException{
        this.Codigo=Codigo;
        this.Origem=Origem;
        this.Destino=Destino;
        this.Data_inicio=Data_inicio;
        this.Data_fim=Data_fim;
        this.preco=preco;
        this.auto =auto;
        this.lista_comentarios = new ArrayList();
        this. lugares = new Cliente [auto.getcapacidade()];
    }

    /**
     *
     * @return listas dos comentarios
     */
    public List <Comentarios> getListaComentarios(){
        return lista_comentarios;
    } 

    /**
     *
     * @return o preco da viagem
     */
    public double get_preco(){
        /*
        Retorna o Preço da Viagem.
        */
        return preco;
    }

    /**
     *
     * @return o codigo da viagem
     */
    public int getcodigo(){
        /*
        Retorna o codigo da Viagem.
        */
        return Codigo;
    }

    /**
     *
     * @return Data de inicio da viagem
     */
    public Data getDataInicio(){
        /*
        retorna a data de inicio da viagem
        */
        return Data_inicio;
    }

    /**
     *
     * @return Data de fim da viagem
     */
    public Data getDataFim(){
        /*
        Retorna a data de fim da viagem.
        */
        return Data_fim;
    }
   
/**
 * Muda a origem da viagem
 * @param Origem Nova origem da viagem.
 * 
 */
    public void muda_origem(String Origem){
        /*
        Dado uma origem nova a função vai mudar a Origem para a nova.
        */
        this.Origem = Origem;
    }

/**
 * Muda o Destino da viagem 
 * @param Destino Nova rigem da viagem. 
 */
    public void muda_destino(String Destino){
        /*
        Dado um Destino novo a função vai mudar o Destino para o novo.
        */
        this.Destino = Destino;
    }
/**
 * Altera o preco da viagem.
 * @param preco novo preco da viagem 
 */
    public void muda_preco(int preco){
        /*
        Dado um novo preço atualiza na viagem.
        */
        this.preco = preco;
    }

/**
 * Muda a data de inicio da Viagem.
 * @param data_ini nova data de inicio da viagem
 */
    public void muda_data_ini(Data data_ini){
        /*
        Dado uma nova data de inicio atualiza na viagem.
        */
        this.Data_inicio = data_ini;
    }

/**
 * Muda a data do fim da viagem.
 * @param data_fim nova data de fim da viagem
 */

    public void muda_data_fim(Data data_fim){
        /*
        Dado uma nova deta de fim atualiza na viagem.
        */
        this.Data_fim = data_fim;
    }

/**
 * 
 * @return auto 
 */
    public Autocarros getAuto(){
        /*
        Returna o autocarro da Viagem
        */
        return auto;
    }

/**
 * 
 * @param v_1 Viagem a que pretendes mudar o preço 
 * @return o valor devolvido
 */
    public double MudaPrecoPr(Viagens v_1){
        /*
        Muda o preço da viagem para clientes premiun
        */
        double custo = v_1.get_preco()*0.9;
        return custo;
    }

    /**
     *
     * @param v_1 viagem onde se pretende mudar o preço
     * @return valor devolvido
     */
    public double MudaPrecoR(Viagens v_1){
        /*
        Muda o o preço da reserva para clientes regulares
        */
        double custo = v_1.get_preco()*0.5;
        return custo;
    }
    /**
     * 
     * @param viagem Viagem
     * @param cliente Cliente
     * @param comentario Comentarios
     * @param pontuacao Pontuação
     * @throws IOException 
     */
public void adicionaComentarios(Viagens viagem,Cliente cliente,String comentario,int pontuacao) throws IOException{
    Comentarios comet = new Comentarios (viagem,cliente,comentario,pontuacao);
    lista_comentarios.add(comet);
    
}
/**
 * Imprimi os comentarios dos utilizadores
 */
public void verComentarios(){
    if (lista_comentarios.isEmpty()==true){
        System.out.println("Nâo existe nenhum comentario\n");
    }else{
        for (Comentarios U:lista_comentarios){
            System.out.println(U);
        }
    }
}

    /**
     *
     * @return objeto do tipo viagens
     */
    @Override
    public String toString(){
        return "\nCodigo:"+Codigo+"\nOrigem:"+Origem+"\nDestino:"+Destino+"\nData inicio:"+Data_inicio+"\nData fim:"+Data_fim+"\nPreço:"+preco+"\nAuto:"+auto;
    }
            
    /**
     * 
     * @return lugares do autocarro
     */
    public Cliente[] listarLugares(){
        /*
       Retorna o array de lugares do autocarro.
        */
        return lugares;
    }
    /**
     * Associa lugar no autocarro
     * @param U Cliente
     * @return O numero do lugar no autocarro.
     */
    public int AssociarLugar (Cliente U){
        /*
        Associa um lugar ao Cliente no autocarro.
        */
        int i=0;
        for (Cliente lug:lugares){
            i++;
            if (lug == null){
                this.lugares[i-1]=U;
                
                return i;                          //lugar zero não existe
                
            }
        }   
        return 0;
    }
    /**
     * Retira o cliente do autocarro, dessacocia lugar.
     * @param U Cliente 
     */
    public void retiraLugar(Cliente U){
        /*
        Retira o Cliente do autocarro.
        */
        int i=0;
        
        for (Cliente lug:lugares){
            i++;
            
            if (lug == U){
                this.lugares[i-1]= null;
               
            }
            break;
        }
      
    }
    /**
     * Verifica se há lugares livre no autocarro.
     * @return true se existir ou false se não existir.
     */
    public boolean verificaAutoLugar(){
        /* Percorre os lugares todos do autocarro e se algum estiver vazio retorna true, se nenhum lugar estiver vazio retorna false*/ 
        //for (int i=0;i<this.capacidade;++i){
        for (Cliente lug:lugares){
            
            if (lug == null){
                
                return true;
            } 
            
        }
        return false;
    }
    
}



