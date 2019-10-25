package model;

/**
 *
 * @author ivansuptitz
 */
public class Autor implements Comparable {
    private String nome;
    private int numeroPublicacoes;

    public Autor(String nome) {
        this.nome = nome;
    }
    
    public void incrementaPublicacao(){
        numeroPublicacoes++;
    }

    public String getNome() {
        return nome;
    }

    public int getNumeroPublicacoes() {
        return numeroPublicacoes;
    }

    @Override
    public int compareTo(Object o) {
        Autor comparar = (Autor)o;//converter o objeto passado para comparar
        int x = comparar.getNumeroPublicacoes() - this.numeroPublicacoes;
        if(x==0)//significa que ambos tem mesmo nº de publicações
            x = comparar.getNome().compareTo(nome);//então vai por odem alfabética do nome
        return x;
    }
    
    
}
