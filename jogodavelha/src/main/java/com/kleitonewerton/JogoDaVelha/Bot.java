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
    
    public Bot(String simboloAdversario){
        
        if(simboloAdversario.toUpperCase().equals("X"))          
            this.simbolo = "O";
        else 
            this.simbolo = "X";
        
        System.out.println("Bot usando o simbolo" + this.simbolo);
    }
    
    public void fazerJogada(String[][] tabuleiro){
        System.out.println("\nBot fazendo sua jogada\n");
        Random randor = new Random();
        int linha;
        int coluna;
        
        while(true){
            linha = 1 + randor.nextInt(3);
            coluna= 1 + randor.nextInt(3);
            if(inserirValor(tabuleiro,linha,coluna, this.simbolo)){
                break;
            }
        }
        
    }
    public String getSimbolo(){
        
        return this.simbolo;
    }
    
}
