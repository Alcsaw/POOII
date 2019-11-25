/*
 * Classe auxiliar para ser instanciada na View e encaminhar as solicitações de
 * persistência ao DAO, evitando acesso da 1ª camada diretamente à 3ª
 */
package model;

import DAL.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author supti
 */
public class Auxiliar {

    public ArrayList<Atendimento> retornaAtendimentosCliente(int codCliente) throws ClassNotFoundException, SQLException {
        DAO<Atendimento> dao = new DAO<>();

        return dao.retornaAtendimentosCliente(codCliente);
    }

    public ArrayList<Cliente> retornaClientes() throws ClassNotFoundException, SQLException {
        DAO<Cliente> dao = new DAO<>();

        return dao.consultar(Cliente.class);
    }

    public ArrayList<Atendente> retornaAtendentes() throws ClassNotFoundException, SQLException {
        DAO<Atendente> dao = new DAO<>();

        return dao.consultar(Atendente.class);
    }

    public void inserirAtendimento(Atendente a, Cliente c, String descricao) throws SQLException, ClassNotFoundException {

        //setar os dados recebidos da tela no objeto atendimento
        Atendimento obj = new Atendimento();
        obj.setAtendente_id(a.getId());
        obj.setCliente_id(c.getId());
        obj.setDescricao(descricao);

        //repassar o objeto à camada de acesso aos dados
        DAO<Atendimento> dao = new DAO<>();
        dao.inserir(obj);
    }
}
