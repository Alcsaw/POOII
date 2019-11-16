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

    public ArrayList<T> get(Class classe) throws SQLException {
        ArrayList<T> list = new ArrayList<>();
        
        String sql = "SELECT * FROM ";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        
        if (classe == Client.class) {
            sql += " cliente ";
            preparedStatement = connection.preparedStatement(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Client cli = new Client();
                cli.setId(resultSet.getInt("id"));
                cli.setName(resultSet.getString("nome"));
                list.add((T)cli);
            }
        } else if (classe == Category.class) {
            sql += " categoria ";
            preparedStatement = connection.preparedStatement(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Category cat = new Category();
                cat.setId(resultSet.getInt("id"));
                cat.setDescription(resultSet.getString("descricao"));
                list.add((T)cat);
            }
        } else if (classe == Product.class) {
            sql += " produto ";
            preparedStatement = connection.preparedStatement(sql);
            // busca todos os produtos
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // materializa produto
                Product prod = new Product();
                prod.setId(resultSet.getInt("id"));
                prod.setDescription(resultSet.getString("descricao"));
                prod.setPrice(resultSet.getDouble("preco"));
                
                // busca a categoria do produto
                PreparedStatement preparedStatementCateg = connection.preparedStatement("Select * from categoria where categoria.id="+resultSet.getInt("categoria_id"));
                ResultSet categSet = preparedStatementCateg.executeQuery();
                while(categSet.next()) {
                    // adiciona categoria encontrada ao produto
                    Category cat = new Category(categSet.getInt("id"), categSet.getString("descricao"));
                    prod.setCategory(cat);
                }
                
                list.add((T)prod);
            }
        } else if(classe == Order.class) {
            sql += " pedido ";
            preparedStatement = connection.preparedStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                
                Order ord = new Order();
                ord.setId(resultSet.getInt("id"));
                ord.setDate(resultSet.getString("data"));
                ord.setDone(resultSet.getInt("finalizado") == 1);
                ord.setDelivered(resultSet.getInt("entregue") == 1);
                
                // busca o cliente do pedido
                ArrayList<T> clients = this.get(Client.class);
                for(T c : clients) {
                    if(((Client)c).getId() == resultSet.getInt("cliente_id")) {
                        ord.setClient((Client)c);
                    }
                }
                
                // busca lista de todos os produtos com suas categorias
                ArrayList<T> prods = this.get(Product.class);
                
                // busca e materializa os order_products
                sql = "select * from pedido_item where pedido_item.pedido_id=?";
                PreparedStatement st2 = connection.preparedStatement(sql);
                st2.setInt(1, ord.getId());
                ResultSet set2 = st2.executeQuery();
                ArrayList<OrderProduct> ops = new ArrayList<OrderProduct>();
                
                while(set2.next()) {
                    OrderProduct op = new OrderProduct();
                    // pega id do produto contido nesta linah do pedido_item
                    int productId = set2.getInt("produto_id");
                    // encontra o produto pelo id na lista prods
                    Product auxProd = null;
                    for(T p : prods) {
                        if(((Product)p).getId() == productId) {
                            op.setProduct((Product)p);
                        }
                    }
                    op.setOrder(ord);
                    op.setQuantity(set2.getInt("quantidade"));
                    op.setComment(set2.getString("observacao"));
                    ops.add(op);
                }
                ord.setOrderProducts(ops);
                
                list.add((T)ord);
            }
        } else {
            throw new IllegalArgumentException("Classe não tratada no DAO");
        }

        resultSet.close(); // close do result set
        preparedStatement.close(); // close do statement
        
        return list;
    }
    
    public boolean insert(T obj) throws SQLException {

        String sql = "INSERT INTO";
        PreparedStatement st;
        
        if(obj instanceof Category){
            Category cat = (Category)obj;
            sql += " categoria (descricao) values (?) ";
            st = connection.preparedStatement(sql);
            st.setString(1, cat.getDescription());
        } else if(obj instanceof Product) {
            Product prod = (Product)obj;
            sql += " produto (categoria_id, descricao, preco) values (?, ?, ?) ";
            st = connection.preparedStatement(sql);
            st.setInt(1, prod.getCategory().getId());
            st.setString(2, prod.getDescription());
            st.setDouble(3, prod.getPrice());
        } else if(obj instanceof Client) {
            Client cli = (Client)obj;
            sql += " cliente (nome) values (?) ";
            st = connection.preparedStatement(sql);
            st.setString(1, cli.getName());
        } else if(obj instanceof Order) {
            Order order = (Order)obj;
            sql += " pedido (cliente_id, data, finalizado, entregue) values (?,NOW(),?,?) ";
            st = connection.preparedStatement(sql);
            st.setInt(1, order.getClient().getId());
            st.setInt(2, order.isDone() ? 1 : 0);
            st.setInt(3, order.isDelivered() ? 1 : 0);
        } else if(obj instanceof OrderProduct) {
            OrderProduct op = (OrderProduct)obj;
            sql += " pedido_item (pedido_id, produto_id, quantidade, preco, observacao) values (?,?,?,?,?) ";
            st = connection.preparedStatement(sql);
            st.setInt(1, op.getOrder().getId());
            st.setInt(2, op.getProduct().getId());
            st.setInt(3, op.getQuantity());
            st.setDouble(4, op.getTotalPrice());
            st.setString(5, op.getComment());
        }
        else {
            throw new IllegalArgumentException("Classe não tratada para acesso ao BD");
        }
              
        int ret = st.executeUpdate();//de qualquer forma executa
        st.close();
        if (ret > 0) {
            return true;//insert funcionou
        } else {
            return false; //insert não funcionou
        }
    }
    
    public boolean update(T obj) throws ClassNotFoundException, SQLException {
        PreparedStatement st;
        if(obj instanceof Category) {
            String sql = "UPDATE categoria SET descricao=? WHERE categoria.id=?";
            Category cat = (Category)obj;
            st = connection.preparedStatement(sql);
            st.setString(1, cat.getDescription());
            st.setInt(2, cat.getId());
        } else if(obj instanceof Product) {
            String sql = "UPDATE produto SET categoria_id=?, descricao=?, preco=? WHERE produto.id=?";
            Product prod = (Product)obj;
            st = connection.preparedStatement(sql);
            st.setInt(1, prod.getCategory().getId());
            st.setString(2, prod.getDescription());
            st.setDouble(3, prod.getPrice());
            st.setInt(4, prod.getId());
        } else if(obj instanceof Order) {
            String sql = "UPDATE pedido SET finalizado=?, entregue=? WHERE pedido.id=?";
            Order order = (Order)obj;
            st = connection.preparedStatement(sql);
            st.setInt(1, order.isDone() ? 1 : 0);
            st.setInt(2, order.isDelivered() ? 1 : 0);
            st.setInt(3, order.getId());
        } else {
            throw new IllegalArgumentException("Classe não tratada para acesso ao BD");
        }

        int ret = st.executeUpdate();
        st.close();
        return ret > 0; //update funcionou
    }

    public boolean delete(T obj) throws SQLException {
        PreparedStatement st;
        if(obj instanceof Product) {
            String sql = "DELETE FROM produto WHERE produto.id=?";
            st = connection.preparedStatement(sql);
            st.setInt(1, ((Product)obj).getId());
        } else if(obj instanceof Category) {
            String sql = "DELETE FROM categoria WHERE categoria.id=?";
            st = connection.preparedStatement(sql);
            st.setInt(1, ((Category)obj).getId());
        } else {
            throw new IllegalArgumentException("Classe não tratada para acesso ao BD");
        }
        int ret = st.executeUpdate();
        st.close();
        return ret > 0; //delete funcionou
    } 
    
    public ArrayList<Product> getProductsFromCategory(Category cat) throws SQLException {
        ArrayList<Product> prodList = new ArrayList<Product>();
        PreparedStatement st;
        String sql = "SELECT * FROM produto WHERE produto.categoria_id=?";
        st = connection.preparedStatement(sql);
        st.setInt(1, cat.getId());
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
            Product pr = new Product();
            pr.setId(rs.getInt("id"));
            pr.setDescription(rs.getString("descricao"));
            pr.setPrice(rs.getDouble("preco"));
            pr.setCategory(cat);
            prodList.add(pr);
        }
        rs.close(); // close do result set
        st.close(); // close do statement
        return prodList;
    }
}
