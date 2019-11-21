/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Atendimento;
import model.Atendente;
import model.Cliente;

/**
 *
 * @author alcsaw
 */
public class DAO<T> {

    private Conexao conexao;

    public DAO() throws ClassNotFoundException, SQLException {
        conexao = new Conexao();
    }

    public void inserir(T obj) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO";
        PreparedStatement preparedStatement;

        if (obj instanceof Atendimento) {
            sql += " atendimento (cliente_id, atendente_id, descricao) VALUES (?, ?, ?)";
            Atendimento modelAtendimento = (Atendimento) obj;//fazer o cast

            preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, modelAtendimento.getCliente_id());
            preparedStatement.setInt(2, modelAtendimento.getAtendente_id());
            preparedStatement.setString(3, modelAtendimento.getDescricao());

        } else {
            //Precisa apenas inserir atendimentos
            throw new IllegalArgumentException("Esta classe não possui tratamento para inserir no BD");
        }

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public ArrayList<T> consultar(Class classe) throws SQLException, ClassNotFoundException {
        ArrayList<T> ret = new ArrayList<>();
        String sql = "SELECT * FROM";

        PreparedStatement st;
        ResultSet rs;

        if (classe == Atendente.class) {
            sql += " atendente";
            st = conexao.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {//enquanto não tiver chegado ao final do result set
                Atendente modelAtendente = new Atendente();
                modelAtendente.setId(rs.getInt("id"));
                modelAtendente.setNome(rs.getString("nome"));
                ret.add((T) modelAtendente);
            }
        } else if (classe == Cliente.class) {
            sql += " cliente";
            st = conexao.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {//enquanto não tiver chegado ao final do result set
                Cliente modelCliente = new Cliente();
                modelCliente.setId(rs.getInt("id"));
                modelCliente.setNome(rs.getString("nome"));
                ret.add((T) modelCliente);
            }

        } else {
            throw new IllegalArgumentException("Esta classe não possui tratamento para consultar no BD");
        }

        rs.close();
        st.close();
        return ret;
    }

    //métodos excluir() e atualizar() não foram implementados pois não são necessários nesse problema
    
    //retorna uma lista com todos os atendimentos do cliente passado por parâmetro
    public ArrayList<Atendimento> retornaAtendimentosCliente(int codCliente) throws SQLException {
        ArrayList<Atendimento> ret = new ArrayList<>();
        String sql = "SELECT x.atendente_id, x.cliente_id, x.descricao, a.nome"
                + " FROM atendimento x"
                + " INNER JOIN atendente a ON x.atendente_id=a.id"
                + " WHERE x.cliente_id=?";

        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, codCliente);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {//enquanto não tiver chegado ao final do result set
            Atendimento modelAtendimento = new Atendimento();

            modelAtendimento.setCliente_id(rs.getInt("cliente_id"));
            modelAtendimento.setAtendente_id(rs.getInt("atendente_id"));
            modelAtendimento.setNomeAtendente(rs.getString("nome"));
            modelAtendimento.setDescricao(rs.getString("descricao"));
            ret.add(modelAtendimento);
        }
        preparedStatement.close();
        rs.close();

        return ret;
    }

}
