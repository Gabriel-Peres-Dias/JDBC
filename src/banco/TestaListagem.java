package banco;

import java.sql.*;

public class TestaListagem {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexao();

        PreparedStatement stm = connection.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
        stm.execute();
        ResultSet result = stm.getResultSet();

        while (result.next()) {
            Integer id = result.getInt("ID");
            String nome = result.getString("NOME");
            String descricao = result.getString("DESCRICAO");
            System.out.println("ID: " + id + " , Nome: " + nome + " , Descrição: " + descricao);
        }

        connection.close();
    }
}

