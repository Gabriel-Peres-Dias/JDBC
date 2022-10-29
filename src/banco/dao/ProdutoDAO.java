package banco.dao;

import banco.model.Categoria;
import banco.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Produto produto) throws SQLException {
        String sql = "INSERT INTO PRODUTO(NOME, DESCRICAO) VALUES (?, ?)";

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getDescricao());

            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    produto.setId(resultSet.getInt(1));
                }
            }
        }
    }

    public List<Produto> listar() throws SQLException {
        List<Produto> produtos = new ArrayList<Produto>();
        String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.execute();

            try (ResultSet rst = stm.getResultSet()) {
                while (rst.next()) {
                    Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
                    produtos.add(produto);
                }
            }
        }
        return produtos;
    }


    public List<Produto> buscar(Categoria cat) throws SQLException {
        List<Produto> produtos = new ArrayList<Produto>();
        String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO WHERE CATEGORIA_ID = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, cat.getId());
            stm.execute();

            try (ResultSet rst = stm.getResultSet()) {
                while (rst.next()) {
                    Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
                    produtos.add(produto);
                }
            }
        }
        return produtos;
    }
}
