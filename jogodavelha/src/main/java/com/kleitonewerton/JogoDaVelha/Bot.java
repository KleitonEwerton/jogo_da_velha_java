/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kleitonewerton.JogoDaVelha;

import static com.kleitonewerton.JogoDaVelha.JogoDaVelha.*;
import java.util.Random;
/**
 *
 * @author KleitonEwerton
 */
public class Bot {
    
    String simbolo;
    String simboloAdversario;
    /** 
    * @brief                                                                    Função contrutora do bot
    * 
    * @param simboloAdversario                                                  Simbolo usado pelo adversario do bot, o player
    */
    public Bot(String simboloAdversario){
        
        this.simboloAdversario = simboloAdversario;
        if(simboloAdversario.toUpperCase().equals("X"))                 //Altera o simbolo como pedido na atividade    
            this.simbolo = "O";
        else 
            this.simbolo = "X";
        
        System.out.println("Bot usando o simbolo" + this.simbolo);
    }
    
    /**
     * @brief                                                                   função para realizar devidas jogadas de ataque ou defesa, dependendo dos paramentros
     * 
     * @param tabuleiro                                                         tabuleiro do jogo atual
     * @param alvo                                                              string que deseja buscar
     * @param inserir                                                           string que será inserida
     * @return                                                                  se true o algoritmo inseriu um novo valor
    */
    private boolean jogarJogada(String[][] tabuleiro, String alvo, String inserir){
   
        int[] linhas;       //Coordenadas das linhas, caso for necessario 'atacar' ou 'defender'
        int[] colunas;      //Coordenadas das colunass, caso for necessario 'atacar' ou 'defender'
        int[] diagonal;     //Coordenadas das diagonais, caso for necessario 'atacar'ou 'defender'
        
        linhas = jogadaHorizontal(tabuleiro, alvo);//Pega a linha
        
        if(linhas[0] != -1){
            
            inserirValor(tabuleiro,linhas[0]+1,linhas[1]+1, inserir,false);//tenta inserir
            return true;
        }
        
        colunas = jogadavertical(tabuleiro, alvo);//Pega a culuna
        if(colunas[0] != -1){
            
            inserirValor(tabuleiro,colunas[0]+1,colunas[1]+1, inserir,false);//tenta inserir
            return true;
        }  
        
        diagonal = jogadaDiagonal(tabuleiro, alvo);//Pega a diagonal
        if(diagonal[0] != -1){
            inserirValor(tabuleiro,diagonal[0]+1,diagonal[1]+1, inserir,false);//tenta inserir
            return true;
        } 
     
        return false;
        
    }
    
    /**
    * @brief                                                                    funcao para fazer jogadas
    * 
    * @param tabuleiro                                                          tabuleiro do jogo atual
    */
    public void fazerJogada(String[][] tabuleiro){
        
        System.out.println("\nBot fazendo sua jogada\n");
        
        
        if(jogarJogada(tabuleiro,this.simbolo,this.simbolo))    //Para ataque
            return;
        
        if(jogarJogada(tabuleiro,getSimboloAdv(),this.simbolo)) //Para defesa
            return;
        
        if(inserirValor(tabuleiro,2,2, this.simbolo,false))      //Inserir no meio melhor estrategia
            return;
        
        
        if(jogarSenario(tabuleiro))                                         //Para travar as jogas pelas laterias
            return;
        
        jogarRandon(tabuleiro);                                             //Alguma jogada aleatoria
        
    }
    
    /**
     * @brief                                                                   função para travar as jogadas pelas laterias
     * 
     * @param tabuleiro                                                         tabuleiro do jogo atual
     * @return                                                                  caso true ocorreu uma inserção
     */
   
    private boolean jogarSenario(String[][] tabuleiro){
        
        String bordas = tabuleiro[0][0]+tabuleiro[2][2] + tabuleiro[0][2] + tabuleiro[2][0];
        
        if(inserirValor(tabuleiro,1,1, getSimbolo(),false))//tenta inserir em um senario  esquerda superior
            return true;
        
        if(contCaracter(bordas, getSimboloAdv()) == 1 || contCaracter(bordas, getSimboloAdv()) == 2){ //Para senário onde as bosdas estao sendo usadas pelo adversario
            int[][] vetor = {{0,1},{1,0},{2,1},{1,2}};
            
            
            int position = bordas.indexOf(getSimboloAdv()); //Primeira index da posicao
            int tentativa = 1;                                  
            
            while(position < 4){
                
                
                if(inserirValor(tabuleiro,vetor[position][0],vetor[position][1], getSimbolo(),false))//tenta inserir
                    return true;
                
                tentativa += 1;
                
                position +=1;
                
                if(position > 3 && tentativa < 5){
                    position = 0;
                }
            }
       
        }
        
        return false;
        
    }
    
    /**
     * @brief                                                                   função para jogar aleatoriamente
     * 
     * @param tabuleiro                                                         tabuleiro do jogo atual
     */
    private void jogarRandon(String[][] tabuleiro){
        
        Random randor = new Random();
        int linha;
        int coluna;
        
        while(true){
            linha = 1 + randor.nextInt(3);
            coluna= 1 + randor.nextInt(3);
            if(inserirValor(tabuleiro,linha,coluna, this.simbolo,false)){ //tenta inserir o randon
                break;
            }
        }
        
    }
    
    /**
    * @brief                                                                    função que verifica se há uma jogada a ser feita pela horizontal
    *
    * @param tabuleiro                                                          tabuleiro do jogo atual
    * @param simboloAdversario                                                  simbolo usado pelo adversorio(ou o nosso simbolo caso for para um 'ataque')
    *
    * @return                                                                   vetor coordena x y, caso não ha jogadas return {-1,-1}
    */
    private int[] jogadaHorizontal(String[][] tabuleiro, String simboloAdversario){
        
        int[] posicao = {-1,-1};
        
        String[] horizontal = coletaLinhas(tabuleiro);                          //Pega todas a linhas
        
        for(int i = 0;i<3;i++){
            
            if(contCaracter(horizontal[i], simboloAdversario) == 2 && contCaracter(horizontal[i], " ") == 1){
                
               posicao[0] = i;
               posicao[1] = horizontal[i].indexOf(" ");
               
               return posicao;
            }
        }
  
        return posicao;
    }
    
      /**
    * @brief                                                                    função que verifica se há uma jogada a ser feita pela diagonal
    * 
    * @param tabuleiro                                                          tabuleiro do jogo atual
    * @param simboloAdversario                                                  simbolo usado pelo adversorio(ou o nosso simbolo caso for para um 'ataque')
    * 
    * @return                                                                   vetor coordena x y, caso não ha jogadas return {-1,-1}
    */
    private int[] jogadaDiagonal(String[][] tabuleiro, String simboloAdversario){
        int[] posicao = {-1,-1};
        
        String diagonalP = tabuleiro[0][0] + tabuleiro[1][1] +tabuleiro[2][2];
        String diagonalS = tabuleiro[0][2] + tabuleiro[1][1] +tabuleiro[2][0];  
        
        if(contCaracter(diagonalP, simboloAdversario) == 2 && contCaracter(diagonalP, " ") == 1){

            posicao[0] = diagonalP.indexOf(" ");
            posicao[1] = diagonalP.indexOf(" ");
            return posicao;
        }
        
        if(contCaracter(diagonalS, simboloAdversario) == 2 && contCaracter(diagonalS, " ") == 1){
            
            if(diagonalS.indexOf(" ") == 0){
               posicao[0] = 0;
               posicao[1] = 2; 
               return posicao;
            }
            if(diagonalS.indexOf(" ") == 1){
               posicao[0] = 1;
               posicao[1] = 1; 
               return posicao;
            }
            if(diagonalS.indexOf(" ") == 2){
               posicao[0] = 2;
               posicao[1] = 0; 
               return posicao;
            }
      
        }
        
        return posicao;
        
    }
    
      /**
    * @brief                                                                    função que verifica se há uma jogada a ser feita pela vertical
    * 
    * @param tabuleiro                                                          tabuleiro do jogo atual
    * @param simboloAdversario                                                  simbolo usado pelo adversorio(ou o nosso simbolo caso for para um 'ataque')
    * 
    * @return                                                                   vetor coordena x y, caso não ha jogadas return {-1,-1}
    */
    private int[] jogadavertical(String[][] tabuleiro, String simboloAdversario){
        int[] posicao = {-1,-1};
        
        String[] vertical = coletaColunas(tabuleiro);                           //Pega todas a colunas
        
        for(int i = 0;i<3;i++){
            
            if(contCaracter(vertical[i], simboloAdversario) == 2 && contCaracter(vertical[i], " ") == 1){
                
                posicao[1] = i;
                posicao[0] = vertical[i].indexOf(" ");
                
                return posicao;
                
            }
            
        }
        
        return posicao;
    }
    
    /**
     * @brief                                                                   Função para contar caracteres
     * 
     * @param entrada                                                           Palavra da verificação
     * @param simbolo                                                           String buscada
     * 
     */
    private int contCaracter(String entrada, String simbolo){
        char temp;
        int totalCharacters = 0;
        
        for (int i = 0; i < entrada.length(); i++) {

            temp = entrada.charAt(i);
            if (temp == simbolo.charAt(0))
                totalCharacters +=1;
        }
        return totalCharacters;
    }
    
    /**
    * @brief                                                                    Função que retorna o simblo do adversario
    * 
    * @return                                                                   simbolo do adversario
    */
    public String getSimboloAdv(){
        return this.simboloAdversario;
    }
    
    /**
    * @brief                                                                    Função que retorna o simblo nosso
    * 
    * @return                                                                   simbolo nosso
    */
    public String getSimbolo(){
        
        return this.simbolo;
    }
    
}
