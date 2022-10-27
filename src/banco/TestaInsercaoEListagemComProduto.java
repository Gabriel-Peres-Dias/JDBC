package banco;

import banco.dao.ProdutoDAO;
import banco.model.Produto;

import java.sql.*;
import java.util.List;

public class TestaInsercaoEListagemComProduto {
    public static void main(String[] args) throws SQLException {

        Produto comoda = new Produto("Placa-mãe AM4", "B550 MSI MAG TOMAHAWK");

        try (Connection connection = new ConnectionFactory().recuperarConexao()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            produtoDAO.salvar(comoda);
            List<Produto> listaProduto = produtoDAO.listar();
            listaProduto.forEach(System.out::println);
        }
    }
}
