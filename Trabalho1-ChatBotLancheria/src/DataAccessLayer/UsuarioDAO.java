package DataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author m98567
 */
public class UsuarioDAO {
    /**
     * Consulta os livros no banco de dados e os retorna num ArayList
     * @return 
     */
    public ArrayList<Usuario> getLivros() throws ClassNotFoundException, SQLException {
        ArrayList<Usuario> listaLivros = new ArrayList<>();
        
        Conexao connection = new Conexao();
        Connection databaseConnection = connection.getConnection();
        
        Statement statement = (Statement) databaseConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM livro");
        
        while (resultSet.next()) {
            Livro livro = new Livro();
            livro.setId(resultSet.getInt("id"));
            livro.setTitulo(resultSet.getString("titulo"));
            livro.setAutor(resultSet.getString("autor"));
            livro.setCategoria(EnumCat.valueOf(resultSet.getString("categoria")));
            livro.setDataPublicacao(resultSet.getDate("data_publicacao"));
            listaLivros.add(livro);
        }
        resultSet.close(); // close do result set
        statement.close(); // close do statement
        
        return listaLivros;
    }
    
    public boolean inserir(Livro livro) throws ClassNotFoundException, SQLException {
        Conexao connection = new Conexao();
        Connection databaseConnection = connection.getConnection();
        
        String sql = "INSERT INTO livro (\n" +
                    "	titulo,\n" +
                    "	autor,\n" +
                    "	categoria,\n" +
                    "	data_publicacao\n" +
                    ") VALUES (?, ?, ?, ?);";
        
        PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
        preparedStatement.setString(1, livro.getTitulo());
        preparedStatement.setString(2, livro.getAutor());
        preparedStatement.setString(3, livro.getCategoria().toString());
        preparedStatement.setDate(4, (java.sql.Date)livro.getDataPublicacao());
        
        int result = preparedStatement.executeUpdate();
        // executeUpdate() will return a ????????????
        return result > 0;
    }
}
