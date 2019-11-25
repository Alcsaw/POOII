/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author supti
 */
public class DAO<T> {

    private Conexao conn;

    public DAO() throws ClassNotFoundException, SQLException {
        conn = new Conexao();
    }

    public void inserir(T obj) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO";
        PreparedStatement st;

        //só foi feito tratamento de inserir atendimento, pois cliente e atendente
        //não precisam ser inseridos nesse programa
        if (obj instanceof Atendimento) {
            sql += " atendimento (cliente_id, atendente_id, descricao) VALUES (?, ?, ?)";
            Atendimento a = (Atendimento) obj;//fazer o cast

            st = conn.prepareStatement(sql);
            st.setInt(1, a.getIdCliente());
            st.setInt(2, a.getIdAtendente());
            st.setString(3, a.getDescricao());

        } else {
            throw new IllegalArgumentException("Esta classe não possui tratamento para inserir no BD");
        }

        st.executeUpdate();
        st.close();
    }

    public ArrayList<T> consultar(Class classe) throws SQLException, ClassNotFoundException {
        ArrayList<T> ret = new ArrayList<>();
        String sql = "SELECT * FROM";

        PreparedStatement st;
        ResultSet rs;

        if (classe == Atendente.class) {
            sql += " atendente";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {//enquanto não tiver chegado ao final do result set
                Atendente a = new Atendente();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                ret.add((T) a);
            }
        } else if (classe == Cliente.class) {
            sql += " cliente";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {//enquanto não tiver chegado ao final do result set
                Cliente a = new Cliente();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                ret.add((T) a);
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

        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, codCliente);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {//enquanto não tiver chegado ao final do result set
            Atendimento c = new Atendimento();

            c.setIdCliente(rs.getInt("cliente_id"));
            c.setIdAtendente(rs.getInt("atendente_id"));
            c.setNomeAtendente(rs.getString("nome"));
            c.setDescricao(rs.getString("descricao"));
            ret.add(c);
        }
        st.close();
        rs.close();

        return ret;
    }

}
