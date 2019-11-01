package DataAccessLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;

/**
 *
 * @author m98567
 */
public class UsuarioDAO {
    
    /**
     * 
     * @param nome
     * @param senha
     * @return Usuario or null
     * @throws java.lang.ClassNotFoundException 
     * @throws java.sql.SQLException 
     */
    public Usuario validarUsuario(String nome, String senha) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM usuario WHERE nome = ? and senha = md5(?)";
        
        Conexao conexao = new Conexao();
        PreparedStatement preparedStatement = conexao.preparedStatement(sql);
        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, senha);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            //Achou um usuário
            Usuario usuario = new Usuario();
            usuario.setNome(resultSet.getString("nome"));
            usuario.setAdmin(resultSet.getBoolean("administrador"));
            
            return usuario;
        } else {
            return null;
        }
        
    }
}
