package projetologin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import static java.lang.Math.random;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.objects.ArrayBufferView.length;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 *
 * @author Asus
 */
public class Projetologin implements java.io.Serializable {

    static Scanner input = new Scanner(System.in);
    static Scanner input_1 = new Scanner(System.in);

    /**
     *
     * @param args args
     * @throws InterruptedException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        List merda = new ArrayList();
        Agencia c_1 = new Agencia();
        Ficheiro f = new Ficheiro();
        int x = 1;
        while (x == 1) {
        //c_1.criar_admin("abc abc", "abc", "abc", "abc", "abc", "abc");
            //c_1.criar_cliente(0, "a", "a", "a", "a", "a", "a");
            //c_1.criar_cliente(0, "cona", "cona", "cona", "cona", "cona", "cona");
            //c_1.criar_cliente(1, "Alex", "costa", "a", "b", "c", "d");

            System.out.println("----------login--------\n");
            System.out.println("Username:");
            String nome = input_1.nextLine();
            System.out.println("Pass:");
            String Pass = input_1.nextLine();
            c_1 = f.getInfoAgencia(c_1);
            f.escreveAgencia(c_1);
            c_1 = f.getInfoAgencia(c_1);
            c_1.login(nome, Pass,c_1,f);
            f.escreveAgencia(c_1);
            //System.out.println ("Pertende sair do programa(1-sim/0-nao)");
            //int n = input.nextInt();
            int y =1;
            while (y == 1){
                y=0;
                try{
                    System.out.println ("Pertende sair do programa(1-sim/0-não)");
                    int n = input.nextInt();
                    if (n == 1){
                        exit (0);
                    }else if (n==0){
                        x=1;
                    }
                }catch(InputMismatchException e){
                    System.out.println("Insira 1-sim ou 0-não");
                    input.next();
                    y=1;
                }
            }  
        }
    }
}

class Ficheiro {

    public Ficheiro() throws IOException {
        File Ficheiro_cliente = new File("Agencia.txt");
        if (!(Ficheiro_cliente.exists())) {
            Ficheiro_cliente.createNewFile();
        }
    }

    public Agencia getInfoAgencia(Agencia c_1) throws IOException, ClassNotFoundException {
        try {
            FileInputStream is = new FileInputStream(new File("Agencia.txt"));
            try (ObjectInputStream aux = new ObjectInputStream(is)) {
                c_1 = (Agencia) aux.readObject();
                // System.out.println(g);
            }

        } catch (java.io.EOFException e) {
            c_1.criar_admin("abc abc", "abc", "abc", "abc", "abc", "abc");
            System.out.println("Devido ao ficheiro estar vazio um Admin foi criado");
        } catch (java.io.InvalidClassException e) {
            e.printStackTrace();
        }
        return c_1;
    }

    public void escreveAgencia(Agencia c_1) throws IOException {

        try {
            FileOutputStream os = new FileOutputStream(new File("Agencia.txt"));
            ObjectOutputStream ab = new ObjectOutputStream(os);

            ab.writeObject(c_1);
            ab.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ficheiro.class.getName()).log(Level.SEVERE, null, ex);
            
        }

    }

}

class Agencia implements java.io.Serializable {
    /*
    A classe agencia é a principal calsse, é a classe onde se processa tudo
    */

    private static Scanner input = new Scanner(System.in);
    private static Scanner input_1 = new Scanner(System.in);
    private List<ID> Administrador;
    private List<Cliente> Clientes;
    private List<Cliente> Clientes_pr;
    private List<Viagens> lista_viagens;
    private List<Autocarros> lista_autocarro;
    private List<Viagens> lista_viagens_feitas;
    private List <Viagens> viagens_canceladas;
    

    public Agencia() {
        /*
        
        */
        this.Administrador = new ArrayList();
        this.Clientes = new ArrayList();
        this.lista_viagens = new ArrayList();
        this.Clientes_pr = new ArrayList();
        this.lista_autocarro = new ArrayList();
        this.lista_viagens_feitas = new ArrayList();
        this.viagens_canceladas = new ArrayList();
    }

    public void verificaData(Data data_geral) {
        /*
        Verifica a data e ve se as viagens ja ocorreram
        Percorre a lista de Viagens e compara as data para ver se elas ja ocorreram.
        As viajas efeituadas vão passar para a lista-lista_viagens_feitas.
        */
        for (Viagens Viagem_viajada : lista_viagens) {
            if ((Viagem_viajada.getDataInicio().getAno() == data_geral.getAno() && Viagem_viajada.getDataInicio().getMes() == data_geral.getMes() && Viagem_viajada.getDataInicio().getDia() < data_geral.getDia())
                    || (Viagem_viajada.getDataInicio().getAno() == data_geral.getAno() && Viagem_viajada.getDataInicio().getMes() < data_geral.getMes())
                    || (Viagem_viajada.getDataInicio().getAno() < data_geral.getAno())) {
                lista_viagens_feitas.add(Viagem_viajada);
                //lista_viagens.remove(Viagem_viajada);
                if (lista_viagens.isEmpty() == true) {
                    break;
                }

            } else {
                if (lista_viagens.isEmpty() == true) {
                    break;
                }
            }
       }
        for (Viagens v_1:lista_viagens_feitas){
            if (lista_viagens.contains(v_1)){
                lista_viagens.remove(v_1);
                }
            }
        for (Viagens v_1:lista_viagens_feitas){
            
            for (Cliente clien:Clientes){
                v_1.retiraLugar(clien);
            }for (Cliente  clien_pr:Clientes_pr){
                v_1.retiraLugar(clien_pr);
            }
        }

    }

    public void criar_admin(String Nome, String Pass, String morada, String telefone, String email, String NIF) throws IOException {
       /*
        Cria os adminstradores
        */
        for (ID U : Administrador) {
            if (U.Nome == Nome) {
                System.out.print("Ja existe um Administrador com esse nome\n");
                exit(0);
            }
        }
        for (Cliente U : Clientes) {
            if (U.Nome.equals(Nome)) {
                System.out.print("Ja existe um cliente com esse nome\n");
                exit(0);
            }
        }
        for (Cliente U : Clientes_pr) {
            if (U.Nome.equals(Nome)) {
                System.out.print("Ja existe um cliente com esse nome\n");
                exit(0);
            }
        }
        Adimin admin = new Adimin(Nome, Pass, morada, telefone, email, NIF);
        Administrador.add(admin);

    }

    public void criar_cliente(int categoria, String Nome, String Pass, String morada, String telefone, String email, String NIF) throws IOException {
        /*
        Cria os varios tipo de cliente
        categoria = 0 cliente regular
        categoria = 1 cliente premiun
        */
        int x=0;
        for (Cliente U : Clientes) {
            if (U.Nome.equals(Nome)) {
                System.out.print("Ja existe um Cliente com esse nome\n");
                x = 1;
                break;
            }
        }
        for (Cliente U : Clientes_pr) {
            if (U.Nome.equals(Nome)) {
                System.out.print("Ja existe um cliente com esse nome\n");
                x = 1;
                break;
            }
        }
        for (ID U : Administrador) {
            if (U.Nome.equals(Nome)) {
                System.out.print("Ja existe um Administrador com esse nome\n");
                x = 1;
                break;
            }
        }
        if (categoria == 0 && x == 0) {
            Cliente clien = new Cliente(categoria, Nome, Pass, morada, telefone, email, NIF);
            Clientes.add(clien);
        } else if (categoria == 1 && x == 0) {
            Cliente clien = new Cliente(categoria, Nome, Pass, morada, telefone, email, NIF);
            Clientes_pr.add(clien);
        }

    }
    public boolean validaData(Data data){
        
        int dia =data.getDia();
        int mes = data.getMes();
        int ano = data.getAno();
        if ((mes>0&&mes<=12)&&(mes == 2 && (dia>0 && dia<=28) || mes == 1 &&(dia>0 && dia<=31)||
          (mes<=7 && mes % 2!=0) && (dia>0 && dia<=31 )||(mes >7 && mes%2==0)&&(dia>0 &&dia<31)
                || (mes<=7 && mes %2 ==0)&&(dia>0 && dia<30)||(mes > 7 && mes%2!=0)&&(dia>0&&dia<30))){
            return true;
        }
        return false;
    }

    public ID login(String Nome, String Pass,Agencia c_1,Ficheiro f) throws InterruptedException, IOException {
        /*
        Esta funcão faz o login se o cliente ou administrador existirem
        */
        Data Data_geral;
        do {
            System.out.println("Data do dia de hoje(dd/mm/aa):");
            String Data_fim = input_1.nextLine();
            String[] data_fim_1 = Data_fim.split("/");
            String dia_c_f = data_fim_1[0];
            String mes_c_f = data_fim_1[1];
            String ano_c_f = data_fim_1[2];
            Data_geral = new Data(dia_c_f, mes_c_f, ano_c_f);
        }while (validaData(Data_geral )==false);
        int codigo = 0;
        for (Viagens via : lista_viagens) {
            codigo++;
        }for (Viagens via:lista_viagens_feitas){
            codigo ++;
        }for (Viagens via: viagens_canceladas){
           codigo ++;
            }
        verificaData(Data_geral);
        for (Cliente U_1:Clientes){
            U_1.viajaReverva(lista_viagens_feitas);
        }
        for (ID U : Administrador) {
            if (U.Nome.equals(Nome) && U.Pass.equals(Pass)) {
                System.out.print("Existe!\n");
                Menu_administrador((Adimin) U, (Data) Data_geral, codigo, c_1,f);
                return U;
            }
        }
        for (Cliente U : Clientes) {
            if (U.Nome.equals(Nome) && U.Pass.equals(Pass)) {
                System.out.print("Existe!\n");
                Menu_cliente((Cliente) U, (Data) Data_geral,c_1,f);
                return U;
            }
        }
        for (Cliente U : Clientes_pr) {
            if (U.Nome.equals(Nome) && U.Pass.equals(Pass)) {
                System.out.print("Existe!\n");
                Menu_cliente((Cliente) U, (Data) Data_geral,c_1,f);
                return U;
            }
        }
        System.out.println("Não existe!!");
        return null;
    }


    public void Menu_administrador(Adimin U, Data Data_geral, int codigo,Agencia c_1,Ficheiro f) throws InterruptedException, IOException {
        /*
        Função onde estão presentes todas as funcionalidades do Administrador
        */
       
        
        int x = 1;

        while (x == 1) {
            f.escreveAgencia(c_1);
            
            x = 0;
            System.out.println("\n--------------- Viagens.sa---------------\n");
            System.out.println("------------- Menu principal-----------\n"
                    + "1)Criar viagens\n"
                    + "2)Alterar viagens\n"
                    + "3)Listar viagens\n"
                    + "4)Eliminar viagens\n"
                    + "5)Eliminar cliente\n"
                    + "6)Criar cliente regular\n"
                    + "7)Criar cliente premiun\n"
                    + "8)Comentarios\n"
                    + "9)Criar Administrador\n"
                    + "10)Listar Clientes\n"
                    + "11)Estatissticas\n"
                    + "12)Criar autocarros\n"
                    + "13)Listar Autocarros\n"
                    + "14)Sair\n\n"
                    + "Opção:");
            int numero = input.nextInt();
            switch (numero) {
                case 1:
                    if (lista_autocarro.isEmpty() == true){
                        System.out.println("Não há nenhum autocarro disponivel, crie um autocarro.\n");
                        x=1;
                        break;
                    }else{
                        System.out.println("\n----------Criar Viagens----------\n");
                        codigo++;
                        String Origem;
                        System.out.println("Origem:");
                        Origem = input.next();

                        String Destino;
                        System.out.println("\nDestino:");
                        Destino = input.next();
                        Data data_in;
                        do {
                            System.out.println("\nData de inicio(dd/mm/aa):");
                            String Data_inicio = input_1.nextLine();
                            String[] data_fim_1 = Data_inicio.split("/");
                            String dia = data_fim_1[0];
                            String mes = data_fim_1[1];
                            String ano = data_fim_1[2];
                            data_in = new Data(dia,mes,ano);
                        }while (validaData(data_in )==false);
   
                        Data data_fi;
                        do{
                            System.out.println("\nData de fim (dd/mm/aa)");
                            String Data_Final_1 = input.next();
                            String[] data_fim = Data_Final_1.split("/");
                            String dia_f = data_fim[0];
                            String mes_f = data_fim[1];
                            String ano_f = data_fim[2];
                            data_fi = new Data(dia_f,mes_f,ano_f);
                        }while(validaData(data_fi)==false);
                        double preco;
                        System.out.println("\nPreço:");
                        preco = input.nextDouble();
                        String matricula;
                        int w = 1;int conta=0;
                        while (w == 1) {
                            w=0;
                            System.out.println("\nMarticula:");
                            matricula = input_1.nextLine();
                            //ver se o autocarros existe.
                            for (Autocarros auto:lista_autocarro){
                                if (matricula.equals(auto.getmatricula())){
                                    if (lista_viagens.isEmpty()==true){
                                         U.Criar_viagens(lista_viagens,codigo,Origem,Destino,data_in,data_fi,preco,auto);
                                         System.out.println("\nViagem criada com sucesso");
                                         break;
                                    }else{
                                        for (Viagens v_2:lista_viagens){
                                            Autocarros auto_1 = v_2.getAuto();
                                            if (auto_1.getmatricula().equals(matricula)){
                                                boolean resp = verificaAuto(v_2.getDataFim(),data_in);
                                                if (resp == true){
                                                    U.Criar_viagens(lista_viagens,codigo,Origem,Destino,data_in,data_fi,preco,auto);
                                                    System.out.println("\nViagem criada com sucesso");
                                                    w=0;
                                                    break;
                                                
                                                }else{
                                                    w=1;
                                                    break;
                                                }
                                           
                                            }else{
                                                U.Criar_viagens(lista_viagens, codigo, Origem, Destino, data_in, data_fi, preco, auto);
                                                System.out.println("\nViagem criada com sucesso");
                                                w=0;
                                                break;
                                            }
                                            
                                        }
                                    }
                                }

                            }
                           

                        }
                    }
                    x = 1;
                    break;
                case 2:
                    System.out.println("\n----------Alterar Viagem----------\n");
                    if (lista_viagens.isEmpty()==true){
                        System.out.println("Não existe nenhuma viagem disponivel.\n");
                        x=1;
                    }else{
                        System.out.println("1)Origem\n2)Destino\n3)Data inicio\n4)Data fim\n5)Preco\n6)Sair para o menu inicial");
                        int escolha = input.nextInt();
                        switch (escolha) {
                            case 1:
                                System.out.println("Qual o codigo da Viagem:");
                                int codigo_viagem = input.nextInt();
                                System.out.println("Origem:");
                                String nova_origem = input_1.nextLine();
                                Viagens viagem = retorna_viagem(codigo_viagem);
                                viagem.muda_origem(nova_origem);
                                x = 1;
                                break;
                            case 2:
                                System.out.println("Qual o codigo da Viagem:");
                                codigo_viagem = input.nextInt();
                                System.out.println("Destino:");
                                String novo_Destino = input_1.nextLine();
                                Viagens viagem_1 = retorna_viagem(codigo_viagem);
                                viagem_1.muda_destino(novo_Destino);
                                x = 1;
                                break;
                            case 3:
                                System.out.println("Qual o codigo da Viagem:");
                                codigo_viagem = input.nextInt();
                                Data data_inicio;
                                do{
                                    System.out.println("Data de inicio(dd/mm/aa):");
                                    String Data_inicio = input_1.nextLine();
                                    String[] data_inic = Data_inicio.split("/");
                                    String dia_c = data_inic[0];
                                    String mes_c = data_inic[1];
                                    String ano_c = data_inic[2];
                                    data_inicio = new Data(dia_c, mes_c, ano_c);
                                }while(validaData(data_inicio)==false);
                                Viagens viagem_3 = retorna_viagem(codigo_viagem);
                                viagem_3.muda_data_ini(data_inicio);
                                x = 1;
                                break;
                            case 4:
                                System.out.println("Qual o codigo da Viagem:");
                                codigo_viagem = input.nextInt();
                                Data data_f_1;
                                do{
                                    System.out.println("Data de fim(dd/mm/aa):");
                                    String Data_fim = input_1.nextLine();
                                    String[] data_fim_1 = Data_fim.split("/");
                                    String dia_c_f = data_fim_1[0];
                                    String mes_c_f = data_fim_1[1];
                                    String ano_c_f = data_fim_1[2];
                                    data_f_1 = new Data(dia_c_f, mes_c_f, ano_c_f);
                                }while(validaData(data_f_1)==false);
                                Viagens viagem_4 = retorna_viagem(codigo_viagem);
                                viagem_4.muda_data_fim(data_f_1);
                                x = 1;
                                break;
                            case 5:
                                System.out.println("Qual o codigo da Viagem:");
                                codigo_viagem = input.nextInt();
                                System.out.println("Preço:");
                                int novo_preco = input.nextInt();
                                Viagens viagem_2 = retorna_viagem(codigo_viagem);
                                viagem_2.muda_preco(novo_preco);
                                x = 1;
                                break;

                        case 6:
                            x = 1;
                            break;
                        default:
                            System.out.println("Opção invalida\n Opção");
                            escolha = input.nextInt();
                        }
                     }
                    break;
                case 3:
                    System.out.println("\n----------Listar Viagens----------\n");
                    if (lista_viagens.isEmpty()==true && lista_viagens_feitas.isEmpty()==true){
                        System.out.println("Não existe nenhuma Viagem\n");
                        x=1;
                        break;
                    }else {
                        
                        for (Viagens viagem : lista_viagens) {
                            System.out.println(viagem);
                        }
                        System.out.println("Viagens realizadas:\n");
                        for (Viagens viagem:lista_viagens_feitas){
                            System.out.println (viagem);
                        }
                    }
                    x = 1;
                    break;
                case 4:
                    System.out.println("\n---------Eliminar viagem----------\n");
                    if (lista_viagens.isEmpty()==true){
                        System.out.println("Não existe nenhuma Viagem\n");
                        x=1;
                        break;
                    }else{
                    int z=1;
                    int codigo_2 = 0;
                    while(z ==1){
                        z=0;
                        try {
                             System.out.println("Qual o codigo da viagem que pertende remover:");
                             codigo_2 = input.nextInt();
                        }catch (InputMismatchException e){
                            System.out.println("Insira o codigo(int):");
                            input.next();
                            z = 1;
                        }
                    }
                    U.Cancelar_viagens(Clientes,Clientes_pr,lista_viagens,viagens_canceladas, codigo_2);
                    x = 1;
                    }
                    break;

                case 5:
                    System.out.println("\n------------Remover Cliente------------\n");
                    if (Clientes.isEmpty() == true && Clientes_pr.isEmpty() == true){
                        System.out.println("Não existe nenhum cliente");
                        x=1;
                    }else{
                        
                        System.out.println("Nome do cliente que pertende eliminar:");
                        String Nome_cliente = input_1.nextLine();
                        remover_cliente(Nome_cliente);
                        x = 1;
                    }
                    break;

                case 6:
                    System.out.println("\n---------Criar Clientes----------\n");
                    System.out.println("Nome:");
                    String nome_cliente = input_1.nextLine();
                    System.out.println("Pass:");
                    String Pasword = input_1.nextLine();
                    System.out.println("Morada:");
                    String morada = input_1.nextLine();
                    String telefone;
                    do {
                        System.out.println("\nTelefone:");
                        telefone = input.next();
        
                    }while (telefone.isEmpty()|| telefone.contains("^[a-Z]") || telefone.length()<9 || telefone.length()>9);
                    String email;
                    int z=0;
                    do {
                        System.out.println("email:");
                        email = input.next();
                        if(email.indexOf("@") != -1 && email.indexOf(".") != -1){
                            z=0;
                        }else{
                            System.out.println("email inválido");
                            z=1;
                        }  
                    }while(z==1);
                    String NIF;
                    do {
                        System.out.println("\nNIF:");
                        NIF = input.next();
        
                    }while (NIF.isEmpty()|| NIF.contains("^[a-Z]") || NIF.length()<9|| NIF.length()>9);
                    criar_cliente(0, nome_cliente, Pasword, morada, telefone, email, NIF);
                    x = 1;
                    break;
                case 7:
                    System.out.println("\n---------Criar Clientes pre----------\n");
                    System.out.println("Nome:");
                    nome_cliente = input_1.nextLine();
                    System.out.println("Pass:");
                    Pasword = input_1.nextLine();
                    System.out.println("Morada:");
                    morada = input_1.nextLine();
                    String telefone_cli;
                    do {
                        System.out.println("\nTelefone:");
                        telefone_cli = input.next();
        
                    }while (telefone_cli.isEmpty()|| telefone_cli.contains("^[a-Z]") || telefone_cli.length()<9 || telefone_cli.length()>9);
                    String email_1;
                    int p=0;
                    do {
                        System.out.println("email:");
                        email_1 = input.next();
                        if(email_1.indexOf("@") != -1 && email_1.indexOf(".") != -1){
                            p=0;
                        }else{
                            System.out.println("email inválido");
                            p=1;
                        }  
                    }while(p==1);
                    String NIF_1;
                    do {
                        System.out.println("\nNIF:");
                        NIF_1 = input.next();
        
                    }while (NIF_1.isEmpty()|| NIF_1.contains("^[a-Z]") || NIF_1.length()<9|| NIF_1.length()>9);
                    criar_cliente(1, nome_cliente, Pasword, morada, telefone_cli, email_1, NIF_1);
                    x = 1;
                    break;
                case 8:
                    if (lista_viagens_feitas.isEmpty()== true){
                        System.out.println ("Como ainda não ocorreu nenhuma Viagem, não existe qualquer tipo de comentarios");
                        x=1;
                        break;
                    }else{
                        System.out.println("\n---------Comentários---------\n");
                        System.out.println("Ver os comentarios da Viagem(codigo):");
                        int codigo_1 = input.nextInt();
                        for (Viagens viagem_comet:lista_viagens_feitas){
                            if (viagem_comet.getcodigo()==codigo_1){
                                System.out.println(viagem_comet);
                                viagem_comet.verComentarios();
                            }
                        }
                    
                    }
                    x=1;
                    break;
                case 9:
                    System.out.println("\n---------Criar Admin----------\n");
                    System.out.println("Nome:");
                    String nome = input_1.nextLine();
                    System.out.println("\nPass:");
                    String Pass = input_1.nextLine();
                    System.out.println("\nMorada:");
                    String morada_ad = input_1.nextLine();
                    String telefone_ad;
                    
                    do {
                        System.out.println("\nTelefone:");
                        telefone_ad = input.next();
        
                    }while (telefone_ad.isEmpty()|| telefone_ad.contains("^[a-Z]") || telefone_ad.length()<9 || telefone_ad.length()>9);
                    String email_2;
                    int h=0;
                    do {
                        System.out.println("email:");
                        email_2 = input.next();
                        if(email_2.indexOf("@") != -1 && email_2.indexOf(".") != -1){
                            h=0;
                        }else{
                            System.out.println("email inválido");
                            h=1;
                        }  
                    }while(h==1);
                    String NIF_2;
                    do {
                        System.out.println("\nNIF:");
                        NIF_2 = input.next();
        
                    }while (NIF_2.isEmpty()|| NIF_2.contains("^[a-Z]") || NIF_2.length()<9|| NIF_2.length()>9);
                    criar_admin(nome, Pass, morada_ad, telefone_ad, email_2, NIF_2);
                    x = 1;
                    break;
                case 10:
                    System.out.println("-------Listar Clientes-------\n");
                    if (Clientes.isEmpty() == true && Clientes_pr.isEmpty() == true){
                        System.out.println("Não existe nenhum cliente\n");
                        x=1;
                    }else{
                        if (Clientes.isEmpty()==false){
                        System.out.println("regulares:");
                        for (Cliente clientes : Clientes) {
                            System.out.println(clientes.getNome());
                            }
                        }
                        if (Clientes_pr.isEmpty()==false){
                            System.out.println("Premiuns:");
                            for (Cliente clientes : Clientes_pr) {
                                System.out.println(clientes.getNome());
                                }
                        }
                    x = 1;
                    }
                    break;
                case 11:
                    int y = 0;
                    int opcao;
                    while (y == 0) {
                        y = 1;
                        System.out.println("1)Viagem mais vendida num determinado mês\n"//feito
                                + "2)Listar todas as viagens que não tiveram reservas\n" +//feito
                                "3)Listar as reservas de uma viagem\n" + //feito
                                "4)Listar as reservas canceladas de uma viagem\n" +//feito
                                "5)Listar as reservas/clientes em espera\n"//feito
                                + "6)Identificar a viagem com melhor pontuação num determinado mês\n"//feito
                                + "7)Voltar ao menu inicial\n");//feito
                        System.out.println("Opção:");
                        opcao = input.nextInt();
                        switch (opcao) {
                            case 1:
                                System.out.println("---Viagem mais vendida num determinado mês---");
                                int mes_3;
                                //do{
                                    System.out.println("\nmês:");
                                    mes_3 = input.nextInt(); 
                                    
                                //}while (mes_3>0 && mes_3<=12);
                                Viagens viagem;
                                viagem = U.ComparVendidas(lista_viagens_feitas, Clientes, Clientes_pr, mes_3);
                                System.out.println(viagem);

                                y = 0;
                                break;

                            case 2:
                                System.out.println("--Viagens que não tiveram nenhuma reserva--");
                                for (Viagens viagem_2:lista_viagens){
                                    boolean resposta = U.verReseva(Clientes, Clientes_pr, viagem_2);
                                    if (resposta == true){
                                        System.out.println(viagem_2);
                                    }
                                }
                                y = 0;
                                break;
                            case 3:
                                System.out.println("--Listar reservas de uma viagem--");
                                //System.out.println("\nQual o codigo da viagem:");
                                //int cod = input.nextInt();
                                int cod = 0;
                                z = 1;
                                while(z ==1){
                                z=0;
                                try {
                                    System.out.println("Qual o codigo da viagem:");
                                    cod = input.nextInt();
                                }catch (InputMismatchException e){
                                    System.out.println("Insira o codigo(int):");
                                    input.next();
                                    z = 1;
                                    }
                                }
                                Viagens viagem_1 = retorna_viagem(cod);
                                for (Cliente cli : Clientes) {
                                    cli.listar_reserva_viagem(cli, viagem_1);
                                }
                                y = 0;
                                break;
                            case 4:
                                System.out.println("--Listar reservas canceladas de uma viagem--");
                                System.out.println("\nQual o codigo da viagem:");
                                int cod_1 = input.nextInt();
                                Viagens viagem_canc = retorna_viagem(cod_1);
                                System.out.println("\n**********************");
                                System.out.println(viagem_canc);
                                System.out.println("\n**********************\n");

                                for (Cliente clien : Clientes) {
                                    clien.listarClienteCancelada(clien, viagem_canc);
                                }
                                y = 0;
                                break;
                            case 5:
                                System.out.println("--Listar reservas em espera--");
                                for (Cliente clien:Clientes){
                                    System.out.println("-------Cliente------\n");
                                    System.out.println(clien.getNome());
                                    clien.listarEspera();
                                }
                                for (Cliente clien:Clientes_pr){
                                    System.out.println("-------Cliente_pr------\n");
                                    System.out.println(clien.getNome());
                                    clien.listarEspera();
                                }
                                y = 0;
                                break;
                            case 6:
                                System.out.println("--Viagem com melhor pontuação--");
                                int mes_12;
                                //do{
                                    System.out.println("\nmês:");
                                    mes_12 = input.nextInt(); 
                                    
                                //}while (mes_3>0 && mes_3<=12);
                                Viagens viagem_12;
                                viagem_12 = U.ComprarPontuação(lista_viagens_feitas, mes_12);
                                System.out.println(viagem_12);
                                y = 0;
                                break;
                            case 7:
                                x = 1;
                                break;
                            default:
                                System.out.println("Opção invalida");
                                y = 0;
                                break;

                        }
                    }
                    break;
                case 12:
                    System.out.println("----------Cria Autocarros---------\n");
                    U.criar_autocarros(lista_autocarro);
                    x = 1;
                    break;
                case 13:
                    System.out.println("----------Listar Autocarros--------\n");
                    U.listar_autocarros(lista_autocarro);
                    x = 1;
                    break;
                case 14:
                    
                    break;

                default:
                    System.out.println("\nOpção inválida");
                    x = 1;
            }

        }
    }

    public void Menu_cliente(Cliente U, Data Data_geral,Agencia c_1,Ficheiro f) throws InterruptedException, IOException {
        /*
        Função onde estão presentes todas as funcionalidades do cliente
        */
        int x = 1;
        //U.viajaReverva(lista_viagens_feitas);
        U.verificaLugarAuto(U, Data_geral);
        while (x == 1) {
            f.escreveAgencia(c_1);
            x = 0;
            System.out.println("\n--------------- Viagens.sa---------------\n");
            System.out.println("------------- Menu principal-----------\n"
                    + "1)Listar viagens\n"
                    + "2)Suas viagens executadas\n"
                    + "3)Reservar viagens\n"
                    + "4)Cancelar viagens\n"
                    + "5)Listar reservas\n"
                    + "6)Comentarios\n"
                    + "7)Sair\n"
                    + "Opção:");
            int numero = input.nextInt();
            switch (numero) {

                case 1:
                    System.out.println("\n----------Listar Viagens----------\n");
                    if (lista_viagens.isEmpty()==true){
                        System.out.println("Não se encontra nenhuma viagem disponivél\n");
                        x=1;
                    }else{
                        for (Viagens viagem : lista_viagens) {
                            System.out.println(viagem);
                        }
                    }
                    x = 1;
                    break;
                case 2:
                    System.out.println("\n----------Viagens feitas----------\n");
                    U.viagensExecutadas();
                    x=1;
                    break;
                case 3:
                    System.out.println("\n----------Reservar Viagem---------\n");
                    if (lista_viagens.isEmpty()==true){
                        System.out.println("Não se encontra nenhuma viagem disponivél para reserva.\n");
                        x=1;
                    }else{
                        System.out.println("Qual o codigo da viagem que pertende reservar:");
                        int cod = input.nextInt();

                        for (Viagens viagem : lista_viagens) {
                            if (viagem.getcodigo() == cod) {
                                //auto.AssociarLugar(U);
                                U.reservar_viagens(U, viagem, Data_geral);
                            }
                        }
                    }
                    x = 1;
                    break;
                case 4:
                    System.out.println("\n----------Cancelar Reserva---------\n");
                    System.out.println("Qual o codigo da viagem que pertende cancelar:");
                    int cod = input.nextInt();

                    for (Viagens viagem : lista_viagens) {
                        if (viagem.getcodigo() == cod) {
                            
                            if (U.get_categoria() == 0) {
                                
                                U.Cancelar_viagens(U, viagem, Data_geral);
                            } else {
                                U.Cancelar_viagensPr(U, viagem, Data_geral);
                            }
                        }
                    }
                    x = 1;
                    break;
                case 5:
                    System.out.println("\n----------Listar reservas---------\n");
                    U.listar_reservas();
                    System.out.println("Reservas em espera:\n");
                    U.listarEspera();
                    System.out.println("\nReservas canceladas: \n");
                    U.listar_reserva_cancelada();
                    x = 1;
                    break;
                case 6:
                    System.out.println("\n----------Comentarios------------\n");
                    if (lista_viagens_feitas.isEmpty()==true){
                        System.out.println("Ainda nenhuma viagem foi realizada");
                        x=1;
                        break;
                    }else {
                        int y=1;
                        while (y == 1 ){

                            System.out.println("1)Adicionar comentarios\n2)Ver comentarios\n3)Voltar ao Menu inicial");
                            int opcao = input.nextInt();
                            int codigo; 
                            switch (opcao){
                                case 1:
                                    System.out.println ("Qual o codigo da viagem pertendida");
                                    codigo = input.nextInt();
                                    for (Viagens viagem:lista_viagens_feitas){
                                        if (viagem.getcodigo()== codigo){
                                            System.out.println("Comentario:");
                                            String comentario = input_1.nextLine();
                                            System.out.println("Pontuação (1-5):");
                                            int pontuação = input.nextInt();
                                            viagem.adicionaComentarios(viagem,U,comentario,pontuação);
                                            break;
                                        }
                                    }
                                    y =1;
                                    break;
                                case 2:
                                    System.out.println ("Qual o codigo da viagem pertendida");
                                    codigo = input.nextInt();
                                    System.out.println("-----Comentarios-----\n");
                                    for (Viagens viagem:lista_viagens_feitas){
                                        if (viagem.getcodigo()== codigo){
                                            System.out.println(viagem.getcodigo()+"\n");
                                            viagem.verComentarios();
                                        }
                                    }
                                    break;
                                case 3:
                                    y=0;
                                    x=1;
                                    break;
                                default:
                                    System.out.println("Opção invalida");
                                    opcao = input.nextInt();
                            }
                        }
                        
                        break;
                    }
                case 7:
                    break;                
                default:
                    System.out.println("Opção invalida");
                    x = 1;
            }
        }
    }

    public Viagens retorna_viagem(int codigo) {
        /* Esta funcão vai retornar uma viagem dado um codigo.
        Vai percorrer a listade viagens criaddas e a viagem que tiver o mesmo codigo que o codigo inserido vai ser retorna
        */
        for (Viagens V : lista_viagens) {
            if (V.getcodigo() == codigo) {
                return V;
            }
        }
        return null;
    }

    public void remover_cliente(String Nome) {
        /* Elimina um cliente e autualiza as listas de cliente*/
        for (Cliente client : Clientes) {
            if (client.Nome.equals(Nome)) {
                Clientes.remove(client);
                break;
            }

        }
        for (Cliente client : Clientes_pr) {
            if (client.Nome.equals(Nome)) {
                Clientes_pr.remove(client);
                break;
            }
        }
    }
    public boolean  verificaAuto(Data Data_fim,Data data_nova_viagem){
        int dia_fim = Data_fim.getDia();
        int mes_fim = Data_fim.getMes();
        int ano_fim = Data_fim.getAno();
        int mult_fim = ano_fim*10000+mes_fim*100+dia_fim;
        int dia_ini = data_nova_viagem.getDia();
        int mes_ini = data_nova_viagem.getMes();
        int ano_ini = data_nova_viagem.getAno();
        int mult_ini = ano_ini*10000+mes_ini*100+dia_ini;
        if (mult_ini<=mult_fim){
            return false;
        }
        return true;
    }

}
