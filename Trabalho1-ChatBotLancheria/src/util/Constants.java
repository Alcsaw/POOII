/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import DataAccessLayer.DAO;
import java.sql.SQLException;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author erico
 */
public final class Constants {
    public static final String GREETING_MSG = "Olá";
    public static final String QUANTITY_MSG = "Quantas unidades?";
    public static final String COMMENT_MSG = "Possui alguma observação quanto à este produto?";
    public static final String ADD_PROD_MSG = "Deseja adicionar mais algum produto?";
    public static final String THANK_MSG = "Seu pedido ficará pronto em 40 minutos, obrigado pela preferência";
    public static final String DIDNT_UNDERSTAND_CATEGORY = "Não entendi, por favor, digite o número da categoria de novo";
    public static final String DIDNT_UNDERSTAND_PRODUCT = "Não entendi, por favor, digite o número do produto novamente";
    
    public static final String CategoryMsg() throws ClassNotFoundException, SQLException {
        DAO<Category> dao = new DAO<Category>();
        ArrayList<Category> categs = dao.get(Category.class);
        
        String str = "Por favor, escolha a categoria:\n";
        for (int i = 0; i < categs.size(); i++) {
            str += categs.get(i).getId() + " - " + categs.get(i).getDescription() + "\n";
        }
        return str;
    }
    
    public static final String ProductsMsg(Category selectedCateg) throws ClassNotFoundException, SQLException {
        DAO<Product> dao = new DAO<Product>();
        ArrayList<Product> prods = dao.getProductsFromCategory(selectedCateg);
        
        String str = "Por favor, escolha o produto:\n";
        for (int i = 0; i < prods.size(); i++) {
            str += prods.get(i).getId() + " - " + prods.get(i).getDescription() + "\n";
        }
        return str;
    }
}
