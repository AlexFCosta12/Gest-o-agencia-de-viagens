
package projetologin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static projetologin.reservas.input;

/**
 *
 * @author Asus
 */
public class Cliente extends ID implements java.io.Serializable{
    /*
    classe onde estão presentes as varias funcionalidades dos clientes.
    */
    static Scanner input = new Scanner(System.in);
    private List <valida> lista_reserva;
    private List <espera> lista_esperas;
    private List <cancelada> lista_cancelada;
    private List <reservas> lista_reserva_viajada;
    private int categoria; // 0 se for regular e 1 se for premiun

    /**
     *
     * @param categoria categoria 0 - cliente regular 1-cliente premiun 
     * @param Nome Nome do cliente
     * @param Pass Pass do cliente
     * @param morada morada do cliente
     * @param telefone Telefone do Cliente (9digitos)
     * @param email email do cliente (tem de conter@ e.)
     * @param NIF Nif do cliente(9digitos)
     * @throws IOException
     */
    public Cliente (int categoria,String Nome, String Pass, String morada, String telefone, String email, String NIF)throws IOException{
        this.categoria = categoria;
        this.Nome=Nome;
        this.Pass=Pass;
        this.morada=morada;
        this.email=email;
        this.telefone=telefone;
        this.NIF=NIF;
        this.lista_reserva = new ArrayList();
        this.lista_cancelada = new ArrayList();
        this.lista_esperas = new ArrayList();
        this.lista_reserva_viajada = new ArrayList();
        
    }
    /**
     * 
     * @return 0 se é cliente regular ou 1 se é cliente premiun.
     */
    public int get_categoria (){
        /*
        returna a categoria do cliente
        categoria = 0 ->cliente regular
        categoria = 1 -> cliente premiun
        */
        return categoria;
    }

    /**
     *
     * @return nome do cliente
     */
    public String getNome(){
        /*
        returna o nome do cliente.
        */
        return Nome;
    }

    /**
     *
     * @return lista de esperas
     */
    public List getListaEspera(){
        /* Devolve a lista de esperas para a reserva*/
        return lista_esperas;
    }

    /**
     *
     * @return lista de reservas validas
     */
    public List getListaReservas(){
        /*Devolve a lista de reservas validas efetuada*/
        return lista_reserva;
    }
    /**
     * Esta função vai verificar se há autocarros disponiveis se houver vai ainda varificar se há lugares livres nesse autocarro.
     * Se houver lugares livre cria uma reserva valida e conclui a reserva.
     * Se não houver lugar livre cria um reserva em espera a adcinona essa reserva a lista de esperas.
     * @param cliente cliente que pertende reservar a viagem
     * @param Viagem Viagem que pertende reservar
     * @param data Data da reserva
     * @throws IOException 
     */
    public void reservar_viagens (Cliente cliente,Viagens Viagem, Data data) throws IOException{
        /*
        Faz a reserva da viagens se ainda houverem lugares livres.
        */
        //Autocarros auto = Viagem.getAuto();
        if (Viagem.verificaAutoLugar()== true){
            int lugar = Viagem.AssociarLugar(cliente);
            
            reservas nova_reserva = new valida(cliente,Viagem, Viagem.get_preco(),data,lugar);
            lista_reserva.add((valida) nova_reserva);
            //Viagem.adiciona_reserva(nova_reserva);
        }else{
            
            int lugar_1 = Viagem.AssociarLugar(cliente);
            if (lugar_1 == 0){
                System.out.println("Quer ir para a lista de espera dessa viagem(1-sim/0-não):");
                int escolha = input.nextInt();
                if (escolha == 1){
                    reservas reserva_espera = new espera(cliente,Viagem,Viagem.get_preco(),data);
                    lista_esperas.add((espera) reserva_espera);
                }
            }
            
        }
    }
    /**
     * Esta função cancela a reserva e desassocia o lugar no autocarro correspondente a sua reserva. 
     * @param cliente cliente regular que quer cancelar a reserva
     * @param Viagem Viagem que quer reservar
     * @param data Data do cancelamento
     * @throws IOException 
     */
    public void Cancelar_viagens(Cliente cliente,Viagens Viagem,Data data) throws IOException{
        /*
        Cancela a reserva do cliente regular e ainda livreta um lugar do autocarros corresponde a sua reserva.
        */
        
        if (lista_reserva.isEmpty()==true){
            System.out.println("Não tem reserva nessa viagem\n");
        }else{
            for (reservas v_1: lista_reserva){
                Viagens viagem_cancelada = v_1.getViagem();
                //Autocarros auto = viagem_cancelada.getAuto();
                if (Viagem.equals(viagem_cancelada)){
                    viagem_cancelada.retiraLugar(cliente);
                    if (data.DiferencaData(Viagem.getDataInicio(), data)>7){
                        System.out.println("valor que tem a receber:");
                        System.out.println(Viagem.MudaPrecoR(Viagem));
                        reservas nova = new cancelada(cliente,Viagem, Viagem.MudaPrecoR(Viagem),data);
                        lista_cancelada.add((cancelada) nova);
                        lista_reserva.remove(v_1);
                        //Viagem.remove_rerserva(v_1,nova);
                        break;
                    }else{
                    
                        reservas nova = new cancelada(cliente,Viagem,Viagem.get_preco(),data);
                        lista_cancelada.add((cancelada) nova);
                        lista_reserva.remove(v_1);
                        //Viagem.remove_rerserva(v_1,nova);
                        break;
                    }
                }
            
            }
        }
    } 
    /**
     * Esta função cancela a reserva e desassocia o lugar no autocarro correspondente a sua reserva. 
     * @param cliente cliente premiun que quer cancelar a reserva
     * @param Viagem Viagem que quer reservar
     * @param data Data do cancelamento
     * @throws IOException 
     */
    public void Cancelar_viagensPr(Cliente cliente,Viagens Viagem,Data data) throws IOException{
        /*
        Cancela a reserva do cliente premiun e ainda livreta um lugar do autocarros corresponde a sua reserva.
        */
        if (lista_reserva.isEmpty()==true){
            System.out.println("Não tem reserva nessa viagem\n");
        }else{
           for (reservas v_1: lista_reserva){
                //Autocarros auto = v_1.getViagem().getAuto();
                if (Viagem.equals(v_1.getViagem())){
                     v_1.getViagem().retiraLugar(cliente);
                    if (data.DiferencaData(Viagem.getDataInicio(), data)>2){
                        System.out.println("valor que tem a receber:");
                        System.out.println(Viagem.MudaPrecoPr(Viagem));
                        reservas nova = new cancelada(cliente,Viagem,Viagem.MudaPrecoPr(Viagem),data);
                        lista_cancelada.add((cancelada) nova);
                        lista_reserva.remove(v_1);
                        //Viagem.remove_rerserva(v_1,nova);
                        break;
                    }else{
                        reservas nova = new cancelada(cliente,Viagem,Viagem.get_preco(),data);
                        lista_cancelada.add((cancelada) nova);
                        lista_reserva.remove(v_1);
                        //Viagem.remove_rerserva(v_1,nova);
                        break;
                     }
                }
           }
            
        }

    } 
    /**
     * Lista todas as reservas do cliente.
     */
    public void listar_reservas(){
        /*
        lista as reservas efetuadas
        */
        for (reservas clien: lista_reserva){
            System.out.println(clien);
            
        }
    }
    /**
     * Esta função listas as reservas todas de uma determinada viagem.
     * @param U Clienre
     * @param V_1 viagem que pertende listar as reservas
     */
    public void listar_reserva_viagem (Cliente U, Viagens V_1){
        /*
        Dada uma viagem lista as revervas desssa viagem
        */
        for (reservas viagem :lista_reserva){
            if (viagem.getViagem().equals(V_1)){
                System.out.println(V_1);
            }
        }
        for (reservas viagem_1:lista_reserva){
            if (viagem_1.getViagem().equals(V_1)){
              
                System.out.println(U+"\nLugar:"+viagem_1.getLugar());
            }
        }
    }
    /**
     * Listas as reservas canceladas todas
     */
    public void listar_reserva_cancelada(){
        /*
        Lista as reservas canceladas
        */
        for (reservas viagem_1:lista_cancelada){
                System.out.println(viagem_1);
            }
        
    }
    /**
     * Dada uma viagem ve se o cliente cancelou alguma vez essa viagem.
     * @param clien cliente
     * @param viagem_cancelada viagem cancelada
     */
    public void listarClienteCancelada (Cliente clien, Viagens viagem_cancelada){
        /*
        Dada uma viagem ve se o cliente cancelou alguma vez essa viagem.
        */
        for (reservas viagem_1: lista_cancelada){
            if (viagem_1.getViagem().getcodigo() == viagem_cancelada.getcodigo()){
                System.out.println (clien);
            }
        }
    }

    /**
     *
     * @return Lista de reservas
     */
    public List getListReserva(){
        return lista_reserva;
    }

    /**
     *
     * @return lista de reversas de viagens efetuadas
     */
    public List<reservas> getListReservaEfetuada(){
        return lista_reserva_viajada;
    }

    /**
     *
     * @return obejto do tipo cliente
     */
    @Override
    public String toString(){
        return "Nome:"+Nome+"\nPass:"+Pass+"\nMorada:"+morada;
    }
    /**
     * Verifica se existe algum lugar disponivel.
     * @param U Cliente
     * @param Data_geral Data do sistema
     * @throws IOException 
     */
    public void verificaLugarAuto(Cliente U, Data Data_geral) throws IOException{
        /* Se houver algum lugar vazio no autocarro o cliente vai passar da lista de espera de reserva para a lista de reservas valida, a sua reserva vai ficar assim valida*/
        if (lista_esperas.isEmpty()!=true){
            
        
            for (espera reserva_espera:lista_esperas){
                Viagens v_2 = reserva_espera.getViagem();
                boolean lugar_vago = v_2.verificaAutoLugar();
                if (lugar_vago == true){
                    System.out.println("Ja se encontra um lugar disponivel na Viagem:\n");
                    System.out.println(reserva_espera);
                    System.out.println("Pretende reservas a Viagem(1-sim/0-nao):");
                    int resposta = input.nextInt();
                    if (resposta == 1){
                        Viagens v_1=reserva_espera.getViagem();
                        reservar_viagens (U,v_1,Data_geral);
                        lista_esperas.remove(reserva_espera);
                        if (lista_esperas.isEmpty()==true){break;}
                    
                    }
                }   }
        }
    } 

    /**
     *Lista a lista de espera de um cliente
     */
    public void listarEspera(){
        /*
        Lista a lista de espera de uma cliente
        */
        for (espera U:lista_esperas){
            System.out.println(U);
        }
    }/**
     * 
     * @param viagem_removida reserva que se pertende remover
     * @param clien cliente a quem se pertende remover a reserva
     */
    public void removeReserva(Viagens viagem_removida,Cliente clien){
        /*
        remove a reserva
        */
        for (reservas valida:lista_reserva){
            if (viagem_removida.equals(valida.getViagem())){
                lista_reserva.remove(valida);
                viagem_removida.retiraLugar(clien);
                break;
            }
        }
        for (reservas espera:lista_esperas){
            if (viagem_removida.equals(espera.getViagem())){
                lista_esperas.remove(espera);
                break;
            }
        }
        for (reservas cancelada:lista_cancelada){
            if (viagem_removida.equals(cancelada.getViagem())){
                lista_cancelada.remove(cancelada);
                break;
            }
        }
    }/**
     * 
     * @param viagens_viajadas lista de Viagem ja feitas. 
     */
    public void viajaReverva (List<Viagens> viagens_viajadas){
        for (Viagens via:viagens_viajadas){
            for (reservas reserva_feita:lista_reserva){
                if (reserva_feita.getViagem().equals(via)){
                    lista_reserva_viajada.add(reserva_feita);
                    lista_reserva.remove(reserva_feita);
                    break;
                }
            }for (reservas reserva_espera:lista_esperas){
                if (reserva_espera.getViagem().equals(via)){
                    lista_esperas.remove(reserva_espera);
                    break;
                }
            }for (reservas reserva_cancelada:lista_cancelada){
                if (reserva_cancelada.getViagem().equals(via)){
                    lista_cancelada.remove(reserva_cancelada);
                    break;
                }
            }
        }
    }
    /**
     * Lista as Viagens que ja ocorreram 
     */
    public void viagensExecutadas(){
        if (lista_reserva_viajada.isEmpty()){
            System.out.println("Ainda não realizou nenhuma viagem.");
        }else {
            for (reservas reserva_executada:lista_reserva_viajada){
                System.out.println(reserva_executada.getViagem());
            }
        }
    }

}
    


