/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula1;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author m98567
 */
public class Aula1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Biblioteca biblio = new Biblioteca("Biblioteca Central - Unisc");
        
        biblio.addLivro("Sapiens", "HARARI, YUVAL NOAH", EnumCategoria.DIDATICO, new Date(2000, 12, 25));
        biblio.addLivro("Mundo Assombrado pelos Demônios", "Carl Sagan", EnumCategoria.DIDATICO, new Date(2000, 12, 4));
        biblio.addLivro("Contato", "Carl Sagan", EnumCategoria.FICCAO, new Date(2000, 12, 21));
        
        //biblio.removeLivro(1); //removendo o 1º livro
        
        System.out.println("Total de livros: " + biblio.totalLivros());
        
        System.out.println("Autores:");
        ArrayList<Autor> autores = biblio.getListaAutores();
        for (Autor autor : autores) {
            System.out.println("Nome: " + autor.getNome());
            System.out.println("Número de publicações: " + autor.getNumPublicacoes());
            System.out.println("-------------------------");
        }
        
        System.out.println("Lista de livros em String:\n" + biblio.toString());
        
        CadastroDeLivros c = new CadastroDeLivros();
        c.setVisible(true);
        
        /*
        Biblioteca b1 = new Biblioteca("b1 - TESTE");
        for (int i = 0; i < 5000; i++) {
            String titulo = "Título " + i;
            String autor = "Autor " + i;
            EnumCategoria categoria = EnumCategoria.FICCAO;
            Date dataPublicacao = new Date(2000, 01, 01);
            b1.addLivro(titulo, autor, categoria, dataPublicacao);
        }
        
        long tempoAnterior1 = System.currentTimeMillis();
        String s1 = b1.toString();
        long tempoPosterior1 = System.currentTimeMillis();
        
        System.out.println("Tempo Concatenação normal: " +
        (tempoPosterior1 - tempoAnterior1) + " milisegundos");
        
        long tempoAnterior2 = System.currentTimeMillis();
        String s2 = b1.toString(true);
        long tempoPosterior2 = System.currentTimeMillis();
        
        System.out.println("Tempo String Builder: " +
        (tempoPosterior2 - tempoAnterior2) + " milisegundos");
        */
    }
    
}
