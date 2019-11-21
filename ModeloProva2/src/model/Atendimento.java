package model;

/**
 * Classe com todos os campos que da tabela atendimento para gravar no BD
 *
 * @author supti
 */
public class Atendimento {

    private int idCliente;
    private int idAtendente;
    private String descricao;

    //este atributo não existe na tabela atendimento, mas eu preciso
    //para mostar na JTable
    private String nomeAtendente;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdAtendente() {
        return idAtendente;
    }

    public void setIdAtendente(int idAtendente) {
        this.idAtendente = idAtendente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeAtendente() {
        return nomeAtendente;
    }

    public void setNomeAtendente(String nomeAtendente) {
        this.nomeAtendente = nomeAtendente;
    }
}
