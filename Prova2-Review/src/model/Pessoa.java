package model;

/**
 * Esta classe propriamente não será instanciada, somente as classes Atendente 
 * e CLiente que herdam dela
 * @author supti
 */
public abstract class Pessoa {
    private int id;
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    //retorna o nome para ser utilizado pelas combos
    @Override
    public String toString(){
        return this.nome;
    }
}
