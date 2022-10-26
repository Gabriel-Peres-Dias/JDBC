package banco;

import java.sql.*;

public class TestaInsercaoComParametros {

    public static void main(String[] args) throws SQLException {
        String nome = "Ryzen 5 5600x";
        String descricao = "Procesador AMD 6/12";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexao();

        PreparedStatement stm =
                connection.prepareStatement("INSERT INTO PRODUTO(nome, descricao) VALUES (?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, nome);
        stm.setString(2, descricao);
        stm.execute();

        ResultSet rst = stm.getGeneratedKeys();
        while (rst.next()) {
            Integer id = rst.getInt(1);
            System.out.println("ID: " + id);
        }
    }

}
