package DAL;

import java.sql.*;

/**
 *
 * @author ivansuptitz
 */
public class Conexao {

    private final String driver = "org.gjt.mm.mysql.Driver";
    private final String url = "jdbc:mysql://localhost/" + "modelo_prova2";
    private final String usuario = "root";
    private final String senha = "";//aqui no lab, "" (VAZIO)
    private final Connection conexao;

    public Conexao() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        conexao = DriverManager.getConnection(url, usuario, senha);
    }

    public Connection getConexao() {
        return conexao;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return conexao.prepareStatement(sql);
    }

    public void fechar() throws SQLException {
        conexao.close();
    }
}
