package model;

import java.util.ArrayList;
import java.util.Collections;
import java.sql.Date;

/**
 *
 * @author ivansuptitz
 * A classe Biblioteca utiliza VETOR, para controlar os livros
 * A classe Biblioteca1 utiliza ARRAYLIST, para controlar os livros 
 */
public class Biblioteca1 {
    private String nome;
    private ArrayList<Livro> lstLivros;
    private int id;

    public Biblioteca1(String nome) {
        this.nome = nome;
        lstLivros = new ArrayList<>(500);
    }

    public String getNome() {
        return nome;
    }
    
    public void addLivro(String titulo, String autor, EnumCat categoria, Date dataPub){               
        id++;
        Livro l = new Livro(id, titulo, autor, categoria, dataPub);
        lstLivros.add(l);
    }
    
    public void addLivro(Livro l){
        lstLivros.add(l);
    }
    
    public void removeLivro(int id){
        for (int i = 0; i < lstLivros.size(); i++) {
            if(lstLivros.get(i).getId() == id){
                lstLivros.remove(i);
                break;//posso sair pois já encontrei
            }
        }
    }
    
    public int totalLivros(){
        return lstLivros.size();
    }
    
    public ArrayList<Autor> getListaAutores(){
        ArrayList<Autor> ret = new ArrayList<>();
        //1º percorrer a lista de livros
        for (int i = 0; i < lstLivros.size(); i++) {
            Livro l = lstLivros.get(i);
            Autor a = null;
            
            //percorrer a lista de autores (já está na minha lista?)
            for (int j = 0; j < ret.size(); j++) {                
                if(ret.get(j).getNome().equals(l.getAutor())){
                    //encontrei o autor
                    a = ret.get(j);
                    break;
                }
            }
            
            if(a == null){//não encontrei                
                a = new Autor(l.getAutor()); //então vamos criar a instância
                ret.add(a);//e adicionar na lista
            }
            
            //de qualquer forma, incrementa o nº de publicações
            a.incrementaPublicacao();                
        }        
        //2º ordenar a lista
        Collections.sort(ret);
        
        return ret;
    }
    
    @Override
    public Biblioteca1 clone(){
        //ainda é uma cópia rasa
        Biblioteca1 b2 = new Biblioteca1(this.nome);
        for(int i=0; i<lstLivros.size(); i++){
            Livro l = lstLivros.get(i);
            Livro copia = l.clone();
            b2.addLivro(copia);
        
        }
        return b2;
    }
    
    public Livro getLivro(int pos){
        return lstLivros.get(pos);//retorna o livro da posição pos
    }
    
    public ArrayList<Livro> getLstLivros(){
        return lstLivros;
    }
    
}
