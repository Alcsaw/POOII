package model;

/**
 *
 * @author m98567
 */
public class Conta {

    private String nomeCliente;
    private EnumConta tipoConta;
    private float saldo;

    public Conta(){
        
    }
    
    public Conta(String nomeCliente, EnumConta tipoConta, float saldo) {
        this.nomeCliente = nomeCliente;
        this.tipoConta = tipoConta;
        this.saldo = saldo;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public EnumConta getTipoConta() {
        return tipoConta;
    }

    public float getSaldo() {
        return saldo;
    }
    
    public Boolean validarNomeCliente(String nomeCliente) {
        return nomeCliente.length() > 5;
    }
    
    public Boolean validarSaldo(String saldo) {
        try {
            Float.parseFloat(saldo);
        } catch (NumberFormatException notAFloat) {
            System.out.println("ERRO: O saldo inserido não é um número válido.");
            return false;
        } catch (NullPointerException emptyString) {
            System.out.println("ERRO: Saldo está vazio!");
            return false;
        }
        return saldo.length() > 0;
    }
    
    public static void sacar(float valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void depositar(float valor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
