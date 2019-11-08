package DataAccessLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import model.*;

/**
 *
 * @author m98567
 * 
 * Generic Class for Data Access Objects
 */
public class DAO<T> {
    
    private DatabaseConnection connection;
    
    public DAO() throws ClassNotFoundException, SQLException {
        this.connection = new DatabaseConnection();
    }
    
    /**
     * Consulta os livros no banco de dados e os retorna num ArayList
     * @return 
     * @throws java.lang.ClassNotFoundException 
     * @throws java.sql.SQLException 
     */
    public ArrayList<T> get(Class classe) throws SQLException {
        ArrayList<T> list = new ArrayList<>();
        
        String sql = "SELECT * FROM ";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        
        if (classe == UF.class) {
            sql += "unidade_federativa";
            preparedStatement = connection.preparedStatement(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                UF uf = new UF();
                uf.setUf(resultSet.getString("uf"));
                uf.setDescricao(resultSet.getString("descricao"));
                uf.setMediaSaude(resultSet.getDouble("media_saude"));
                uf.setMediaEducacao(resultSet.getDouble("media_educacao"));
                uf.setMediaRenda(resultSet.getDouble("media_renda"));
                uf.setMediaIfdm(resultSet.getDouble("media_ifdm"));
                
                list.add((T) uf);
            }
        } else if (classe == Cidade.class) {
            sql += "cidade";
            preparedStatement = connection.preparedStatement(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Cidade cidade = new Cidade();
                cidade.setId(resultSet.getInt("id"));
                cidade.setUf(resultSet.getString("uf"));
                cidade.setDescricao(resultSet.getString("descricao"));
                cidade.setSaude(resultSet.getDouble("saude"));
                cidade.setEducacao(resultSet.getDouble("educacao"));
                cidade.setRenda(resultSet.getDouble("renda"));
                cidade.setIfdm(resultSet.getDouble("ifdm"));
                
                list.add((T) cidade);
            }
        } else {
            throw new IllegalArgumentException("Classe não tratada no DAO");
        }

        resultSet.close(); // close do result set
        preparedStatement.close(); // close do statement
        
        return list;
    }
    
    public boolean inserir(T obj) throws SQLException {
        
        String sql = "INSERT INTO ";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        
        if (obj instanceof UF) {
            UF uf = (UF)obj;
            
            sql +=  "unidade_federativa (\n"
                    + "    uf,\n"
                    + "    descricao,\n"
                    + "    media_saude,\n"
                    + "    media_educacao,\n"
                    + "    media_renda,\n"
                    + "    media_ifdm\n"
                    + ")\n"
                    + "VALUES (\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?\n"
                    + ");";
            
            preparedStatement = connection.preparedStatement(sql);
            preparedStatement.setString(1, uf.getUf());
            preparedStatement.setString(2, uf.getDescricao());
            preparedStatement.setDouble(3, uf.getMediaSaude());
            preparedStatement.setDouble(4, uf.getMediaEducacao());
            preparedStatement.setDouble(5, uf.getMediaRenda());
            preparedStatement.setDouble(6, uf.getMediaIfdm());
            
        } else if (obj instanceof Cidade) {
            Cidade cidade = (Cidade)obj;
            
            sql +=  "cidade (\n"
                    + "    uf,\n"
                    + "    descricao,\n"
                    + "    saude,\n"
                    + "    educacao,\n"
                    + "    renda,\n"
                    + "    ifdm\n"
                    + ")\n"
                    + "VALUES (\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?\n"
                    + ");";
            
            preparedStatement = connection.preparedStatement(sql);
            preparedStatement.setString(1, cidade.getUf());
            preparedStatement.setString(2, cidade.getDescricao());
            preparedStatement.setDouble(3, cidade.getSaude());
            preparedStatement.setDouble(4, cidade.getEducacao());
            preparedStatement.setDouble(5, cidade.getRenda());
            preparedStatement.setDouble(6, cidade.getIfdm());
            
        } else {
            throw new IllegalArgumentException("Classe não tratada no DAO");
        }
        
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result > 0;
    }
}
