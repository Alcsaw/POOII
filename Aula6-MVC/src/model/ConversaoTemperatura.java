/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Na camada MODEL, ficam as regras de negócio (eerrhh...)
 * @author m98567
 */
public class ConversaoTemperatura {
    private double tempCelsius;
    
    public ConversaoTemperatura(double temperature) {
        tempCelsius = temperature;
    }
    
    public double converterParaFahrenheit() {
        return tempCelsius * 1.8 + 32;
    }
}
