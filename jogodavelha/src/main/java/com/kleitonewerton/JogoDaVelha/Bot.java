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
    
    public Bot(String simboloAdversario){
        
        this.simboloAdversario = simboloAdversario;
        if(simboloAdversario.toUpperCase().equals("X"))          
            this.simbolo = "O";
        else 
            this.simbolo = "X";
        
        System.out.println("Bot usando o simbolo" + this.simbolo);
    }
    
    private boolean jogarJogada(String[][] tabuleiro, String alvo, String inserir){
   
        int[] linhas;
        int[] colunas;
        int[] diagonal;
        
        linhas = jogadaHorizontal(tabuleiro, alvo);
        
        if(linhas[0] != -1){
            
            inserirValor(tabuleiro,linhas[0]+1,linhas[1]+1, inserir,false);
            return true;
        }
        
        colunas = jogadavertical(tabuleiro, alvo);
        if(colunas[0] != -1){
            
            inserirValor(tabuleiro,colunas[0]+1,colunas[1]+1, inserir,false);
            return true;
        }  
        
        diagonal = jogadaDiagonal(tabuleiro, alvo);
        if(diagonal[0] != -1){
            inserirValor(tabuleiro,diagonal[0]+1,diagonal[1]+1, inserir,false);
            return true;
        } 
     
        return false;
        
        
    }
    
    public void fazerJogada(String[][] tabuleiro){
        
        System.out.println("\nBot fazendo sua jogada\n");
        
        
        if(jogarJogada(tabuleiro,this.simbolo,this.simbolo))    //Para ataque
            return;
        
        if(jogarJogada(tabuleiro,getSimboloAdv(),this.simbolo)) //Para defesa
            return;
        
        if(inserirValor(tabuleiro,2,2, this.simbolo,false))      //Inserir no meio melhor estrategia
            return;
        
        
        Random randor = new Random();
        int linha;
        int coluna;
        
        while(true){
            linha = 1 + randor.nextInt(3);
            coluna= 1 + randor.nextInt(3);
            if(inserirValor(tabuleiro,linha,coluna, this.simbolo,false)){
                break;
            }
        }
        
    }
    
    private int[] jogadaHorizontal(String[][] tabuleiro, String simboloAdversario){
        
        int[] posicao = {-1,-1};
        
        String[] horizontal = new String[3];
        horizontal = coletaLinhas(tabuleiro);
        
        for(int i = 0;i<3;i++){
            
            if(contCaracter(horizontal[i], simboloAdversario) == 2 && contCaracter(horizontal[i], " ") == 1){
                
               posicao[0] = i;
               posicao[1] = horizontal[i].indexOf(" ");
               
               return posicao;
            }
        }
  
        return posicao;
    }
    
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
    
    private int[] jogadavertical(String[][] tabuleiro, String simboloAdversario){
        int[] posicao = {-1,-1};
        
        String[] vertical = new String[3];
        vertical = coletaColunas(tabuleiro);
        
        for(int i = 0;i<3;i++){
            
            if(contCaracter(vertical[i], simboloAdversario) == 2 && contCaracter(vertical[i], " ") == 1){
                
                posicao[1] = i;
                posicao[0] = vertical[i].indexOf(" ");
                
                return posicao;
                
            }
            
        }
        
        return posicao;
    }
    
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
    
    public String getSimboloAdv(){
        return this.simboloAdversario;
    }
    
    public String getSimbolo(){
        
        return this.simbolo;
    }
    
}
