/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula1;

import java.util.Date;

/**
 *
 * @author m98567
 */
public class Livro {
    private final int id;
    private String titulo;
    private String autor;
    private EnumCategoria categoria;
    private Date dataPublicacao;

    public Livro(int id, String titulo, String autor, EnumCategoria categoria,
                 Date dataPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.dataPublicacao = dataPublicacao;
    }

    public int getId() {
        return id;
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

    public EnumCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(EnumCategoria categoria) {
        this.categoria = categoria;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
    
    
}
