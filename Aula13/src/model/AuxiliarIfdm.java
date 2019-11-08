package model;

import DataAccessLayer.*;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ivansuptitz
 */
public class AuxiliarIfdm {

    private final DAO<Cidade> daoCidades;
    private final DAO<UF> daoUfs;
    
    private ArrayList<UF> lstTodasUFs = null;
    private ArrayList<Cidade> lstTodasCidades = null;

    public AuxiliarIfdm() throws ClassNotFoundException, SQLException {
        this.daoUfs = new DAO<>();
        this.daoCidades = new DAO<>();
    }

    public void importaArqUF(String arq) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {
        lstTodasUFs = new ArrayList<>();
        FileReader fr = new FileReader(arq);
        BufferedReader br = new BufferedReader(fr);
        br.readLine();//ler a primeira linha que não tem dados
        String linha = br.readLine();
        while (linha != null) {
            String[] cells = linha.split(";");
            if (cells.length == 2) {
                UF uf = new UF();
                uf.setUf(cells[0]);
                uf.setDescricao(cells[1]);

                daoUfs.inserir(uf);
                lstTodasUFs.add(uf);
            }
            linha = br.readLine();
        }
        br.close();
        fr.close();
    }

    public void importaArqCidades(String arq) throws IOException, SQLException, ClassNotFoundException {
        FileReader fr = new FileReader(arq);
        BufferedReader br = new BufferedReader(fr);
        br.readLine();
        String linha = br.readLine();
        while (linha != null) {
            String[] cells = linha.split(";");
            if (cells.length == 6) {
                Cidade c = new Cidade();

                c.setUf(cells[0]);
                c.setDescricao(cells[1]);
                c.setIfdm(converter(cells[2]));
                c.setRenda(converter(cells[3]));
                c.setEducacao(converter(cells[4]));
                c.setSaude(converter(cells[5]));
                daoCidades.inserir(c);
            }
            linha = br.readLine();
        }
        br.close();
        fr.close();
    }

    ///converte a string passada por parâmetro para double
    ///vírgula será substituída por ponto
    ///se ocorrer erro (exemplo string ND) então retorna zero
    private double converter(String s) {
        double ret;
        try {
            ret = Double.parseDouble(s.replace(',', '.'));
        } catch (NumberFormatException ex) {
            ret = 0;
        }
        return ret;
    }
    
    public void limparBanco() throws ClassNotFoundException, SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        String sql = "DELETE FROM cidade";
        PreparedStatement preparedStatement = connection.preparedStatement(sql);
        preparedStatement.executeUpdate();
        
        sql = "DELETE FROM unidade_federativa";
        preparedStatement = connection.preparedStatement(sql);
        preparedStatement.executeUpdate();
    }

    public ArrayList<Cidade> getCidades(String uf) throws SQLException, ClassNotFoundException {
        return daoCidades.get(Cidade.class);
    }


    public ArrayList<UF> getLstTodasUFs() throws SQLException, ClassNotFoundException {
        if(lstTodasUFs == null)
            lstTodasUFs = daoUfs.get(UF.class);//retornar todas
        
        return lstTodasUFs;
    }

        /*private UF estado;
        private ArrayList<Cidade> lstCid; //somente as cidades deste estado

        public ThreadApurar(UF estado, ArrayList<Cidade> lstCid) {
            this.estado = estado;
            this.lstCid = lstCid;
        }

        @Override
        public void run() {
            try {
                lstCid.sort(new ComparadorCidade());//ordenar pelo IFDM 
                double somaEducacao = 0, somaSaude = 0, somaRenda = 0, somaIfdm = 0;
                for (int j = 0; j < lstCid.size(); j++) {
                    //1º para cada cidade (na ordem certa) vou setar seu ranking nesse estado
                    Cidade c = lstCid.get(j);
                    c.setRankingEstadual(j + 1);
                    lstTodasCidades.add(c);

                    //acumular os somatórios para fazer as médias para a uf
                    somaEducacao += c.getEducacao();
                    somaSaude += c.getSaude();
                    somaRenda += c.getRenda();
                    somaIfdm += c.getIfdm();
                    
                    daoCidades.atualizar(c);//atualiza esta cidade no BD
                }   
                //atualizar as médias da UF
                estado.setMediaEducacao(somaEducacao / lstCid.size());
                estado.setMediaSaude(somaSaude / lstCid.size());
                estado.setMediaRenda(somaRenda / lstCid.size());
                estado.setMediaIfdm(somaIfdm / lstCid.size());
                
                daoUfs.atualizar(estado);//atualiza esta UF no BD
                

            } catch (SQLException ex) {
                Logger.getLogger(AuxiliarIfdm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AuxiliarIfdm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }*/
}