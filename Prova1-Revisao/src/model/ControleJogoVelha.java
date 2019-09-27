package model;

import java.sql.Array;
import java.sql.Time;

/**
 *
 * @author m98567
 */
public class ControleJogoVelha extends ControleJogo {

    @Override
    public void iniciaPartida() {
        tempoInicio = System.currentTimeMillis();
        tabuleiro = "123456789".toCharArray();
    }

    @Override
    public void adicionaMovimento(Character letra, int pos) {
        tabuleiro[pos] = letra;
    }

    @Override
    public long getDuracaoPartida() {
        return (System.currentTimeMillis() - tempoInicio)/1000;
    }

    /**
     * status possíveis:
     * A = Em andamento
     * X = Vitória do Jogador X
     * O = Vitória do Jogador O
     * @return status
     */
    @Override
    public char avaliaPartida() {
        char status = 'A';
        
        for (int i = 0; i <= tabuleiro.length - 1; i = i + 3) {
            if ( (tabuleiro[i] == tabuleiro[i+ 1]) &&
                    (tabuleiro[i] == tabuleiro[i+ 2]) ) { //sequência em linha
                return status = tabuleiro[i];
            }
        }
        
        for (int i = 0; i < 3; i++) {
            if ( (tabuleiro[i] == tabuleiro[i+ 3]) &&
                    (tabuleiro[i] == tabuleiro[i+ 6]) ) { //sequência em coluna
                return status = tabuleiro[i];
            }
        }
        
        if ( (tabuleiro[0] == tabuleiro[4]) &&
                (tabuleiro[0] == tabuleiro[8]) ) {  // sequência na diagonal principal
            return status = tabuleiro[0];
        } else if ( (tabuleiro[2] == tabuleiro[4]) &&
                (tabuleiro[2] == tabuleiro[6]) ) {  // sequência na outra diagonal
            return status = tabuleiro[2];
        }
        
        return status;
    }
    
}
