package models;

/**
 *
 * @author m98567
 */
public class Candidato {
    private String nome;
    private int legenda;
    private int quantidadeVotos;
    
    public Candidato(String nome, int legenda) {
        this.nome = nome;
        this.legenda = legenda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLegenda() {
        return legenda;
    }

    public void setLegenda(int legenda) {
        this.legenda = legenda;
    }

    public int getQuantidadeVotos() {
        return quantidadeVotos;
    }

    public void setQuantidadeVotos(int quantidadeVotos) {
        this.quantidadeVotos = quantidadeVotos;
    }
    
}
