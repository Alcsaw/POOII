/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Scanner;
import model.ConversaoTemperatura;

/**
 *
 * @author m98567
 */
public class MainConsole {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Informe a temperatura em Celsius: ");
        Scanner sc = new Scanner(System.in);
        
        ConversaoTemperatura converter = new ConversaoTemperatura(sc.nextDouble());
        System.out.printf("Temperatura em Fº: %.2f", converter.converterParaFahrenheit());
    }
    
}
