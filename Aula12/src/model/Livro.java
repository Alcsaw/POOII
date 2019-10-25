package model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author ivansuptitz
 */
public class Livro implements Serializable {
    private int id;
    private String titulo;
    private String autor;
    private EnumCat categoria;
    private Date dataPublicacao;

    public Livro() {
    }
    
    public Livro(int id, String titulo, String autor, EnumCat categoria, Date dataPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.dataPublicacao = dataPublicacao;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public EnumCat getCategoria() {
        return categoria;
    }

    public void setCategoria(EnumCat categoria) {
        this.categoria = categoria;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
    
    public Livro clone(){
        Livro l = new Livro(this.id, this.titulo, this.autor, this.categoria, this.dataPublicacao);
        
        return l;
    }
    
}
