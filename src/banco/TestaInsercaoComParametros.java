package banco;

import java.sql.*;

public class TestaInsercaoComParametros {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexao();
        connection.setAutoCommit(false);

        try {
            PreparedStatement stm =
                    connection.prepareStatement("INSERT INTO PRODUTO(nome, descricao) VALUES (?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            adicionarVariavel("SmartTV", "45 Polegadas 144hz", stm);
            adicionarVariavel("Garrafa", "Garrafa preta fosca ecológica", stm);

            connection.commit();

            stm.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Rollback executado!!");
            connection.rollback();
        }

    }

    private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
        stm.setString(1, nome);
        stm.setString(2, descricao);
        stm.execute();

        ResultSet rst = stm.getGeneratedKeys();
        while (rst.next()) {
            Integer id = rst.getInt(1);
            System.out.println("ID: " + id);
        }
        rst.close();
    }

}
