package model;

import java.util.ArrayList;

/**
 *
 * @author m98567
 */
public class Banco {
    
    private ArrayList<Conta> lstContas = new ArrayList<>();
    
    public float getSaldoContasCorrente() {
        System.out.println("DENTRO DE getSaldoContasCorrente:");
        listaContas();
        float total = 0;
        
        for (Conta conta : lstContas) {
            if (conta.getTipoConta().equals(EnumConta.CORRENTE)) {
                total += conta.getSaldo();
            }
        }
        
        return total;
    }
    
    
    public float getSaldoContasPoupanca() {
        System.out.println("DENTRO DE getSaldoContasPoupanca:");
        listaContas();
        float total = 0;
        
        for (Conta conta : lstContas) {
            if (conta.getTipoConta().equals(EnumConta.POUPANÇA)) {
                total += conta.getSaldo();
            }
        }
        
        return total;
    }
    
    
    public void addConta(String nome, EnumConta tipo, float saldo) {
        Conta conta = new Conta(nome, tipo, saldo);
        
        this.lstContas.add(conta);
        listaContas();
    }
    
    /**
     * @throws IndexOutOfBoundsException
     * @param nome
     * @return Conta
     */
    public Conta buscarConta(String nome) {
        for (Conta conta : lstContas) {
            if (conta.getNomeCliente().equals(nome))
                return conta;
        }
        
        throw new IndexOutOfBoundsException("Conta inexistente.");
    }
    
    public void listaContas() {
        System.out.println("CONTAS:");
        for (Conta conta : lstContas) {
            System.out.println(conta.getNomeCliente());
            System.out.println(conta.getTipoConta());
            System.out.println(conta.getSaldo());
            System.out.println("---------------");
        }
    }
}
