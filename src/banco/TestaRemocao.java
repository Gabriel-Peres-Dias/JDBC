package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaRemocao {
    public static void main(String[] args) throws SQLException {

        Integer id = 15;

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexao();

        PreparedStatement stm = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");
        stm.setInt(1, id);
        stm.execute();

        Integer linhasAfetadas = stm.getUpdateCount();
        System.out.println("Linhas afetadas: " + linhasAfetadas);

    }
}
