package banco;

import banco.dao.ProdutoDAO;
import banco.model.Produto;

import java.sql.*;

public class TestaInsercaoComProduto {
    public static void main(String[] args) throws SQLException {

        Produto comoda = new Produto("Cômoda", "Cômoda Vertical");

        try (Connection connection = new ConnectionFactory().recuperarConexao()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            produtoDAO.salvar(comoda);
            //Lista = produtoDAO.listar();
        }
    }
}
