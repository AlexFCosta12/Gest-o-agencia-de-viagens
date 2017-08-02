package projetologin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class Adimin extends ID implements java.io.Serializable{
    static Scanner input_1 = new Scanner (System.in);
    static Scanner input = new Scanner(System.in);
    
    /**
     *
     * @param Nome Nome do admin
     * @param Pass pass
     * @param morada morada
     * @param telefone telefone (9digitos)
     * @param email email(tem de conter@ e'.')
     * @param NIF NIF(9 digitos)
     * @throws IOException
     */
    public Adimin (String Nome, String Pass, String morada, String telefone, String email, String NIF)throws IOException{
        this.Nome=Nome;
        this.Pass=Pass;
        this.morada=morada;
        this.email=email;
        this.telefone=telefone;
        this.NIF=NIF;
    }

    /**
     *Esta função cria viagem
     * @param lista_viagens Lista de Viagens
     * @param Codigo Codigo (cada viagem tem um codigo diferente)
     * @param Origem Local de Origem 
     * @param Destino Local de Destino
     * @param Data_inicio Data de inicio
     * @param Data_fim Data de fim
     * @param preco Preco da viagem
     * @param auto Autocarro que vai ser usado na viagem
     * @throws IOException
     */
    public void Criar_viagens(List <Viagens> lista_viagens, int Codigo,String Origem,String Destino, Data Data_inicio, Data Data_fim, double preco,Autocarros auto) throws IOException{
        /*
        dado os argumetos cria uma viagem.
        */
        Viagens v_1=new Viagens(Codigo,Origem,Destino,Data_inicio,Data_fim,preco,auto);
        lista_viagens.add(v_1);
      
    }
    /**
     * Cancela a viagem com os respetivo codigo
     * @param clientes Lista dos cliente regulares
     * @param clientes_pr Lista dos cliente premuin
     * @param lista_viagens Lista de todas as viagens
     * @param viagens_canceladas lista de todas as viagens ja canceladas
     * @param Codigo codigo da viagem
     */
    public void Cancelar_viagens(List <Cliente> clientes,List <Cliente> clientes_pr, List <Viagens> lista_viagens, List<Viagens> viagens_canceladas,int Codigo){
        /*
        Cancela uma viagem caso essa viagem exista.
        */
        for (Viagens U:lista_viagens){
            if (U.getcodigo() == Codigo){
                System.out.println("Viagem Cancelada com sucesso");
                lista_viagens.remove(U);
                viagens_canceladas.add (U);
                for (Cliente clien:clientes){
                    clien.removeReserva(U,clien);
                }for (Cliente clien:clientes_pr){
                    clien.removeReserva(U,clien);
                }
                
                
                break;
            }
            System.out.print("A viagem com esse codigo não existe.");
        }

        }
    /**
     * Cria autocarros
     * @param autocarros lista de autocarros
     * @throws IOException 
     */
    public void criar_autocarros(List <Autocarros> autocarros) throws IOException{
        /*
        Cria um autocarros com uma dada matricula e capacidade e adiciona na lista dos autocarros.
        */
        int x=1;
        Autocarros auto_1;
        int cap=0;
        int cod;
        String matricula;
        while(x==1){
            x=0;
            System.out.println("Matricula:");
            matricula = input_1.next();
            cod=0;
            System.out.println("Capacidade do autocarro(pessoas):");
            cap = input.nextInt();
            auto_1 = new Autocarros (matricula,cap);
            if (autocarros.isEmpty()){
                autocarros.add(auto_1);
                break;
            }
            for (Autocarros auto: autocarros){
                cod++;
                if (auto.getmatricula().equals(auto_1.getmatricula())){
                    System.out.println("Ja existe um autocarro com essa matricula");
                    x=1;
                    break;
                }else if (cod == autocarros.size()){
                    autocarros.add(auto_1);
                    break;
                }
            }

         }
    }

    /**
     *Lista  os autocarros existentes
     * @param autocarros Autocarros
     */
    public void listar_autocarros(List <Autocarros> autocarros){
        /*
        Lista todos os autocarros existentes.
        */
        for (Autocarros auto: autocarros){
            System.out.println(auto);
            
        }
    }
        
    /**
     * imprimi o objeto
     * @return objeto
     */
    @Override
    public String toString(){
        return "Nome:"+Nome+" \nPass:"+Pass+" \nMorada:"+morada+" \nemail:"+email+" \nTelefone:"+telefone+" \nNIF:"+NIF;
    }
    /**
     * Compara as viagens feitas com os utilizadores e ve qual a viagem que teve mais volume de vendas num determinado mes.
     * @param lista_viagens_feitas Lista de todas as viagens ja executadas
     * @param lista_clientes_r Lista dos Clientes regulares
     * @param lista_clientes_pr Lista dos clientes premiun
     * @param mes mes que serve de comparacao
     * @return a viagem mais vendida
     */
    public Viagens ComparVendidas(List <Viagens> lista_viagens_feitas,List<Cliente> lista_clientes_r,List<Cliente> lista_clientes_pr, int mes){
        int cont=0;
        int menor =0;
        Viagens viagem_maior = null;
        for (Cliente clien:lista_clientes_r){
            List <reservas> lista = clien.getListReservaEfetuada();
            for (reservas viagem_reserva:lista){
                for (Viagens viagem:lista_viagens_feitas){
                    if (viagem_reserva.getViagem().getcodigo()== viagem.getcodigo()){  
                        if (viagem_reserva.getData().getMes() == mes){
                            cont++;
                            if (cont>menor){
                                menor=cont;
                                viagem_maior = viagem;
                            }
                        }
                    }
                }
            }
        }
        return viagem_maior;
    }
    /**
     * Compara as viagens todas efetudas e ve qual é que obteve maior pontuação.
     * @param lista_vigens_executadas Lista das viagens que ja foram efetuadas.
     * @param mes mes em que se pretende da viagem em que se quer obter maior pontuação 
     * @return A viagem que tem maior pontuação.  
     */
    public Viagens ComprarPontuação(List <Viagens> lista_vigens_executadas,int mes){
        int pontuacao;
        int pont_min=0;
        Viagens viagem_maior_pontuacao = null;
        for (Viagens viagem:lista_vigens_executadas){
            if (viagem.getDataInicio().getMes()== mes){
                List <Comentarios> lista_comentarios = viagem.getListaComentarios();
                for (Comentarios comet:lista_comentarios){
                    pontuacao = comet.getPontuação();
                    if (pontuacao>pont_min){
                        pont_min = pontuacao;
                        viagem_maior_pontuacao = viagem;
                    }
                }
            }
        }
        return viagem_maior_pontuacao;
    }
/**
 * Percorre tods os clientes regulares e premiun e verifica se existe ou não reserva.
 * @param clientes Lista de todos os clientes regulares
 * @param cliente_pr Lista de todos os clientes premiun
 * @param Viagem Viagem em que te pretende ver a reserva
 * @return false caso a reserva exista e true caso não exista. 
 */
    public boolean verReseva(List<Cliente> clientes, List <Cliente>cliente_pr,Viagens Viagem){
        for (Cliente clien:clientes){
            if (clien.getListReserva().contains(Viagem)){
                return false;
            }
        }
        for (Cliente clien_pr:cliente_pr){
            if (clien_pr.getListReserva().contains(Viagem)){
                return false;
            }
        }
        return true;
    }
}
