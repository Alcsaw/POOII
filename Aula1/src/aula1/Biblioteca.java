/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 *
 * @author m98567
 */
public class Biblioteca {
    private final String nome;
    private ArrayList<Livro> livros = new ArrayList<>();
    private int id_controller = 1;

    public Biblioteca(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void addLivro(String titulo, String autor,
                            EnumCategoria categoria, Date dataPublicacao) {
        Livro l = new Livro(id_controller, titulo, autor, categoria, dataPublicacao);
        this.id_controller += 1;
        this.livros.add(l);
    }
    
    public boolean removeLivro(int id) {
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                return this.livros.remove(livro);
            }
        }
        return false;
    }
    
    public Livro getLivro(int index) {
        return livros.get(index);
    }
    
    public int totalLivros() {
        return this.livros.size();
    }
    
    public ArrayList<Autor> getListaAutores() {
        ArrayList<Autor> listaAutores = new ArrayList<>();
        
        for (Livro livro : livros) {
            String autorLivroAtual = livro.getAutor();
            
            found: {
                for (Autor autor : listaAutores) {
                    if (autor.getNome().equals(autorLivroAtual)) {
                        autor.incrementaNumPublicacoes();
                        break found;
                    }
                }   //else
                Autor novoAutor = new Autor(autorLivroAtual);
                novoAutor.incrementaNumPublicacoes();
                listaAutores.add(novoAutor);
            }
            
        }
        
        Collections.sort(listaAutores);
        
        return listaAutores;
    }
    
    @Override
    public String toString() {
        String stringLivros = "";
        
        for (Livro livro : livros) {
            String linhaLivro = "";
            linhaLivro += "ID: " + livro.getId() +
                          "; Título: " + livro.getTitulo() +
                          "; Autor: " + livro.getAutor() +
                          "; Categoria: " + livro.getCategoria() +
                          "; Data Publicação: " + livro.getDataPublicacao() +
                          "\n";
            
            stringLivros += linhaLivro;
        }
        
        return stringLivros;
    }
    
    public String toString(boolean turbo) {
        if (turbo) {
            StringBuilder stringLivros = new StringBuilder();
        
            for (Livro livro : livros) {
                StringBuilder linhaLivro = new StringBuilder();
                linhaLivro.append("ID: ").append(livro.getId());
                linhaLivro.append("; Título: ").append(livro.getTitulo());
                linhaLivro.append("; Autor: ").append(livro.getAutor());
                linhaLivro.append("; Categoria: ").append(livro.getCategoria());
                linhaLivro.append("; Data Publicação: ").append(livro.getDataPublicacao());
                linhaLivro.append("\n");

                stringLivros.append(linhaLivro);        
            }
        
        return stringLivros.toString();
        
        } else {
            return toString();
        }
    }
}
