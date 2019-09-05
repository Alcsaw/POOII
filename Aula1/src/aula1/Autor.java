/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula1;

/**
 *
 * @author m98567
 */
class Autor implements Comparable {
    private String nome;
    private int numPublicacoes;

    public Autor(String nome) {
        this.nome = nome;
        this.numPublicacoes = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getNumPublicacoes() {
        return numPublicacoes;
    }

    public void incrementaNumPublicacoes() {
        this.numPublicacoes = numPublicacoes + 1;
    }

    @Override
    public int compareTo(Object o) {
        // compara apenas o valor de numPublicacoes para ver quem tem o maior
        Autor comparar = (Autor)o;
        // Converter o objeto para tipo Autor para poder comparar
        
        return comparar.getNumPublicacoes() - this.getNumPublicacoes();
    }
    
    
}
