package com.kleitonewerton.JogoDaVelha;

import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author KleitonEwerton
 */
public class JogoDaVelha {
    
    /**
   * @brief                                                             Função de controle do menu inicial                                                                                                         
   *                      
   * @param val 
   * @return int                                                        Retorno 1 para opção A selecionada e retorna 2 para opção B selecionada
   */
    private static int menu1(){

        Scanner teclado = new Scanner(System.in);                 //Variavel para leitura do teclado       
        int saida = 0;                                                  //Valor da saida inicial
        while(saida == 0){                                              //While de controle
            
            System.out.println("\n  A - Jogar contra humano");        //Apenas um print
            System.out.println("\n  B - Jogar contra um bot");      //Apenas um print
            System.out.println("\n  Q - Sair do jogo\n");  
            String entrada = teclado.nextLine();                        //Leitura do teclado
            
            try{                                                         //Try para tratar erros de leitura
                if(entrada.toUpperCase().equals("A"))            //If da opção A
                    return 1;                                            //Retorno da opção A
                
                if(entrada.toUpperCase().equals("B"))            //If da opção B
                    return 2;                                           //Retorno da opção B
                if(entrada.toUpperCase().equals("Q"))            //If da opção B
                    return 3;
                System.out.println("\nEntrada invalida\n");           //Apenas um print
                
            }catch (Exception ex){                                      //Caso ocorra um erro
                System.out.println("\nEntrada invalida\n");           //Apenas um print
            }
        }
        return saida;                                                   //return
    }
    
    /**
    * @brief                                                                Função jogando contra um outro jogador 
    * 
    * @param tabuleiro                                                      Matriz de string do tabuleiro atual
    */
    private static void pvp(String[][] tabuleiro){
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("\nDigite o seu nome jogador 1");
        String nomejg1 = teclado.nextLine();                                        //Lê o nome do jogador 1
        String jg1 = leituraSimbolo(1, "*");
        
        System.out.println("\nDigite o seu nome jogador 2");
        String nomejg2 = teclado.nextLine();                                        //Lê o nome do jogador 2
        String jg2 = leituraSimbolo(2, jg1);
        
        while(true){                                                                //Loop caso for nessesário jogar novamente
            if(!pvpAux(tabuleiro, nomejg1,jg1,nomejg2,jg2)){
                
                if(!jogarNovamente())
                    break;
                iniciaMatriz(tabuleiro);
            }else{
                break;
            }
                
        }
    
    }
     
    /**
    * @brief                                                                 Função auxiliar do modo pvp
    * 
    * @param Tabuleiro                                                       Matriz de string do tabuleiro atual
    * @param nomejg1                                                         Nome do jogador 1
    * @param jg1                                                             Simbolo do jogador 1
    * @param nomejg2                                                         Nome do jogador 2
    * @param jg2                                                             Simbolo do jogador 2
    * 
    * @return                                                                retorna se houve algum vencedor ou não
    */
    private static boolean pvpAux(String[][] tabuleiro, String nomejg1,String jg1 ,String nomejg2, String jg2){
       
        int jogadaAtual = 1;                                                    //Aux da jogada atual
        int jogadorAtual = 1;                                                   //Aux do jogador atual
        boolean algumVencedor = false;                                          //Se existe um ganhador
        String nomeAtual = nomejg1;                                             //Aux do nome do jogador atual
        
        printTabuleiro(tabuleiro);                                              //Imprime o tabuleiro antes de jogar
        
        while((!algumVencedor) && (jogadaAtual <= 9)){                          //Loop das jogas

            int[] coordenadas = selecionaNumero(jogadorAtual);                  //Array linha x coluna

            if(jogadorAtual == 1){                                              //O que fazer quando o for o jogador 1

                if(inserirValor(tabuleiro, coordenadas[0],coordenadas[1], jg1)){

                    if(vencedor(tabuleiro,jg1)){
                        algumVencedor = true;                                   //Agora há um vencedor
                        break;                   
                    }

                    jogadaAtual += 1;                                           //Aumenta a jogada atual
                    jogadorAtual = 2;                                           //Altera o jogador atual
                    nomeAtual = nomejg2;                                        //Altera o nome do jogador atual

                }

            }
            else{                                                               //O que fazer quando for o jogador 2

                if(inserirValor(tabuleiro, coordenadas[0],coordenadas[1], jg2)){

                    if(vencedor(tabuleiro,jg2)){
                        algumVencedor = true;                                   //Agora há um ganhador
                        break;  
                    }

                    jogadaAtual += 1;                                           //Aumenta a jogada atual
                    jogadorAtual = 1;                                           //Altera o jogador atual
                    nomeAtual = nomejg1;                                        //Altera o nome do jogador atual

                }

            }

            printTabuleiro(tabuleiro);
        }
        printTabuleiro(tabuleiro);
        imprimirVencedor(algumVencedor, nomeAtual, jogadorAtual,true);
        
        return algumVencedor;
       
   }
    
    /**
    * @brief                                                    Função jogando contra um bot 
    * 
    * @param tabuleiro                                          Matriz de string do tabuleiro atual
    */
    private static void pvc(String[][] tabuleiro){
    
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("\nDigite o seu nome jogador 1");
        String nomejg1 = teclado.nextLine();                                        //Lê o nome do jogador 1
        String jg1 = leituraSimbolo(1, "*");
        
        while(true){                                                                //Loop caso for nessesário jogar novamente
            if(!pvcAux(tabuleiro, nomejg1,jg1)){
                
                if(!jogarNovamente())
                    break;
                iniciaMatriz(tabuleiro);
            }else{
                break;
            }      
        }
    }
    
    /**
    * @brief                                                                 Função auxiliar do modo pvc
    * 
    * @param Tabuleiro                                                       Matriz de string do tabuleiro atual
    * @param nomejg1                                                         Nome do jogador 1
    * @param jg1                                                             Simbolo do jogador 1
    * 
    * @return                                                                retorna se houve algum vencedor ou não
    */
    private static boolean pvcAux(String[][] tabuleiro, String nomejg1,String jg1){
       
        int jogadaAtual = 1;                                                    //Aux da jogada atual
        int jogadorAtual = 1;                                                   //Aux do jogador atual
        boolean algumVencedor = false;                                          //Se existe um ganhador
             
        Bot bot = new Bot(jg1);
   
        printTabuleiro(tabuleiro);                                              //Imprime o tabuleiro antes de jogar
        
        while((!algumVencedor) && (jogadaAtual <= 9)){                          //Loop das jogas

            if(jogadorAtual == 1){                                              //O que fazer quando o for o jogador 1
                int[] coordenadas = selecionaNumero(jogadorAtual);              //Array linha x coluna
                if(inserirValor(tabuleiro, coordenadas[0],coordenadas[1], jg1)){

                    if(vencedor(tabuleiro,jg1)){
                        algumVencedor = true;                                   //Agora há um vencedor
                        break;                   
                    }
                    jogadaAtual += 1;                                           //Aumenta a jogada atual
                    jogadorAtual = 0;                                           //Altera o jogador atual
                
                }
            }
            else{                                                               //O que fazer quando for o jogador 2
                bot.fazerJogada(tabuleiro);
                
                if(vencedor(tabuleiro,bot.getSimbolo())){
                        algumVencedor = true;                                   //Agora há um ganhador
                        break;  
                }
                jogadaAtual += 1;                                               //Aumenta a jogada atual
                jogadorAtual = 1; 
            }
            printTabuleiro(tabuleiro);
        }
        printTabuleiro(tabuleiro);
        imprimirVencedor(algumVencedor, nomejg1, jogadorAtual,false);
        
        return algumVencedor;
       
   }
    
    /**
    * @brief                                        Função para verificar se jogará novamente
    */
    private static boolean jogarNovamente(){
        
        Scanner teclado = new Scanner(System.in);                                   //Variavel para leitura do teclado    
        String opcao;                                                                     //String para armazenar o simbolo do jogador
        
        while(true){
            System.out.println("\n  Deseja jogar novamente?\n");
            System.out.println("\n  S - Nao jogar novamente");
            System.out.println("\n  N - Nao jogar novamente");
            
            opcao = teclado.nextLine();                                                   //Opção do usuária

            if(opcao.toUpperCase().equals("S")){                                        
                System.out.println("Voce escolheu jogar novamente");
                return true;
            }else{

                if(opcao.toUpperCase().equals("N")){                                        
                    System.out.println("Voce escolheu nao jogar novamente");
                    return false;
                }   

            }
        
        }
    
    }
    
    /**
    * @brief                                                                Função para leitura do simbolo
    * 
    * @param nJogador                                                       O número que representa esse jogador
    * @param valorOponente                                                  Simbolo que o seu oponente utiliza, contra o bot basta passar qualquer string de dois valores
    *
    * @return                                                               return a string que representa o simbolo escolhido pelo jogador
    */
    private static String leituraSimbolo(int nJogador,String valorOponente){
        Scanner teclado = new Scanner(System.in);                                       //Variavel para leitura do teclado    
        String jgS = " ";                                                                     //String para armazenar o simbolo do jogador
        char jgC;                                                                             //Char para pegar apenas a primeira letra
    
        while(jgS.equals(" ")){
            try{                                                                              //Tratamento de erros

                System.out.println("\n  Jogador "+nJogador+" digite o seu simbolo\n");                 //Apenas um print

                jgC = teclado.next().charAt(0);                                          //Pega apenas a primeira letra e convete para um char
                
                jgS = Character.toString(jgC);                                              //Converte o char anterior no simbolo do jogador
                
                if(jgS.equals(valorOponente)){                                         //Para os simbolos não serem iguais
                    jgS = " ";
                    System.out.println("Seu oponente ja esta usando esse simbolo");         //Apenas um print
                }
                
                if(!jgS.equals(" "))                                                   //Controle do print
                    System.out.println("JOGADOR " + nJogador+ ", voce "
                            + "escolheu jogar com o simbolo " + jgS);                          //Apenas um print

            }catch (Exception ex){                                                             //Tratamento de exeção

                System.out.println("\nSimbolo invalido\n");

            }

        }
        return jgS;
    }
  
    /**
    * @brief                                                                Função que permite retorna um numero valido lido pelo teclado
    *   
    * @param jogadorAtual                                                   O jogador atual que está selecionando o numero
    */
    private static int[] selecionaNumero(int jogadorAtual){
        int[] numero = new int[2];
        
        Scanner teclado = new Scanner(System.in);
        
        String entrada;
        String ent1, ent2;
        
        while(true){
            
            try{                                                                                        //tratamento de exeção
                    
                System.out.print("Jogador " + jogadorAtual + " digite a sua jogada ex: (1, 1)\n");
                entrada = teclado.nextLine();
                
                entrada = padronizaEntrada(entrada);
                    
                ent1 = entrada.substring(entrada.indexOf('(')+1, entrada.indexOf(','));
                ent2 = entrada.substring(entrada.indexOf(',')+1, entrada.indexOf(')'));
                
                numero[0] = Integer.parseInt(ent1); 
                numero[1] = Integer.parseInt(ent2); 
                
                
                if(validaNumero(numero[0]) && validaNumero(numero[1]))                                  //valida os numero de inserção
                    break;
                else
                    System.out.print("Numero invalido, deve ser 1, 2 ou 3\n");
                    
            }catch (Exception ex){
                System.out.print("Isso nao e um coordenada valida ex: (1, 2)\n");
            }            
        }
        return numero;
    }
    
    /**
    * @brief                                                            Função usada para validar os numeros de entrada
    * @return boolean                                                   returno true se forem validos false se não forem 
    */
    private static boolean validaNumero(int a){
        
        return a <= 3 && a >= 1;

    }
    
    /**
    * @brief                                                             Função para inserção de um valor
    * 
    * @param tabuleiro                                                   Matriz de string do tabuleiro atual
    * @param linha                                                       Linha para tentativa de inserção
    * @param coluna                                                      Coluna para tentativa de inserção
    * @param valor                                                       Valor para ser inserido, caso possivel
    * 
    * @return                                                            true para inserção com sucesso, false para falha ao inserir
    */
    public static boolean inserirValor(String[][] tabuleiro,int linha, int coluna, String valor){
        
        if(validaNumero(linha) && validaNumero(coluna)){                           //Primeiro válida o numero para inserção
            if(tabuleiro[linha-1][coluna-1].equals(" ")){        //Se o loval for vazio a inserção é feita
                
                tabuleiro[linha-1][coluna-1] = valor;                   //Adiciona o novo valor na posição linha-1 x coluna-1
                return true;                                            //Finaliza função retornando true
                
            }
            else{
                System.out.println("\nJa possui uma valor "
                        + "inserido nessa linha e coluna\n");           //Apenas um print
            }
        }else
            System.out.println("\nValor de linha e/ou "
                    + "coluna invalidos\n");                            //Apenas um print
            
        
        
        return false;                                                   //Fim da função com retorno false
    }
    
    /**
    * @brief                                    funçãoq que paadroniza a entrada para o formato (x,x), removendo os espaços
    * 
    * @param  entrada                           Parametro de entrada                                  
    */
    private static String padronizaEntrada(String entrada){
        
        
        String ent = "";                                                                 //String final
        boolean inicio = true;                                                           //Marca se está no inicio
        boolean fim = false;                                                             //Marca se está no final
            
        for(int i = 0; i < entrada.length();i++){
            
            if(fim && entrada.charAt(i) != ' ')                                     //Caso não se enchaixe no padrao
                return " ";                     
            
            if(!fim && entrada.charAt(i ) == ')')                                   //Define o fim do padrão
                fim = true;
            
         
            if(entrada.charAt(i) == '(' || entrada.charAt(i) == ' ')           //Define que o primeiro '(' foi encontrado
                inicio = false;
            
            if(inicio && (entrada.charAt(i) != ' ' || entrada.charAt(i) != '('))//Caso não se enchaixe no padrao
                return " ";
            
            if(entrada.charAt(i) != ' ')
                ent += Character.toString(entrada.charAt(i));
        
        }

        return ent;
        
    }
 
    /**
    * @brief                                                            Função que dado um valor retorna se o jogador venceu
    *
    * @param tabuleiro                                                  Matriz de string do tabuleiro atual
    * @param valor                                                      Simbolo/valor que o usuário utiliza como seu marcador no tabuleiro
    * @return                                                           true se o jogador for vencedor, false se o jogador não for um vencedor
    */
    private static boolean vencedor(String[][] tabuleiro, String valor){
        
        String valorJunto = valor+valor+valor;                                      //Valor composto pela união de tres valores(facilidade na verificação)
        String linha1 = tabuleiro[0][0] + tabuleiro[0][1] + tabuleiro[0][2];        //Valor total da linha 1
        String linha2 = tabuleiro[1][0] + tabuleiro[1][1] + tabuleiro[1][2];        //Valor total da linha 2
        String linha3 = tabuleiro[2][0] + tabuleiro[2][1] + tabuleiro[2][2];        //Valor total da linha 3
        
        String coluna1 = tabuleiro[0][0] + tabuleiro[1][0] + tabuleiro[2][0];       //Valor total da coluna 1
        String coluna2 = tabuleiro[0][1] + tabuleiro[1][1] + tabuleiro[2][1];       //Valor total da coluna 2
        String coluna3 = tabuleiro[0][2] + tabuleiro[1][2] + tabuleiro[2][2];       //Valor total da coluna 3
        
        String diagonalP = tabuleiro[0][0] + tabuleiro[1][1] + tabuleiro[2][2];     //Valor total da diagonal principal
        String diagonalS = tabuleiro[0][2] + tabuleiro[1][1] + tabuleiro[2][0];     //Valor total da diagonal secundária
        
        return linha1.equals(valorJunto) || linha2.equals(valorJunto)//Verifica possibilidades de vitoria e retorna o resultado
                || linha3.equals(valorJunto)
                    || coluna1.equals(valorJunto)|| coluna2.equals(valorJunto)|| coluna3.equals(valorJunto)
                        || diagonalP.equals(valorJunto) || diagonalS.equals(valorJunto);
        
    }
    
    /**
    * @brief                                                    Função que imprime o vencedor
    * 
    * @param algumVencedor                                      Se houve um vencedor
    * @param jagadorAtual                                       Nome do jogador atual
    * @param pvp                                                Se é o modo pvp
    */
    private static void imprimirVencedor(boolean algumVencedor, String jogadorAtual, int jg,boolean pvp){
        
        if(algumVencedor){
            
            if(pvp){
                System.out.println("\n\nParabens " + jogadorAtual + " voce ganhou\n");
                return;
            }
            
            if(jg == 0){
                System.out.println("\n\nVocê perdeu " + jogadorAtual + "!!! O bot derrotou voce\n");
                return;
            }
        
            if(jg == 1)
                System.out.println("\nParabens " + jogadorAtual + "!!! Voce derrotou o bot");
          
        }else
            System.out.println("\n\nOcorreu um empate!!!\n");
  
    }
    
    /**
    * @brief                                        Função que inicia a matriz do tabuleiro
    * 
    * @param tabuleiro                              Matriz do tabuleiro
    */
    public static void iniciaMatriz(String[][] tabuleiro){
        for(int i = 0;i<3;i++)
            for(int j = 0;j<3;j++)
                tabuleiro[i][j] = " ";
    }
    
    /**
    * @brief                                                            Função para imprimir o tabuleiro
    * 
    * @param tabuleiro                                                  Matriz de string do tabuleiro atual
    */
    public static void printTabuleiro(String[][] tabuleiro){
        System.out.println("\n");
        for(int i = 0; i < 3; i++){                                     //For percorrendo toda a matriz
            System.out.println(" " + tabuleiro[i][0] + " | "            //Print das posições com a formatação correta
                +tabuleiro[i][1] + " | " + tabuleiro[i][2]);
            
            if(i < 2)System.out.println("---|---|---");               //Print apenas para as duas primeiras interações
        }
        System.out.println("\n");
    }
    
    /**
    * @brief                                                             Função princiapal do programa
    *
    * @args                                                              Args passados
    */
    public static void main(String[] args){
        
        System.out.println("\n\nBEM VINDO AO MEU JOGO DA VELHA  \n\n");
        System.out.println("O que voce deseja jogar?");
        
        String[][] tabuleiro = new String[3][3];
        
        iniciaMatriz(tabuleiro);
        
        
        int opcao = menu1();
        switch(opcao){
            case 1:
                pvp(tabuleiro);
             
                break;
            case 2:
                pvc(tabuleiro);
                break;
            case 3:
                System.out.println("\nVoce escolheu sair\n");
                break;
        }   
        
      
    }
    
}
