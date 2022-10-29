package banco;

import banco.dao.CategoriaDAO;
import banco.model.Categoria;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TesteListagemDeCategoria {

    public static void main(String[] args) throws SQLException {

        try(Connection connection = new ConnectionFactory().recuperarConexao()) {
            CategoriaDAO categoriaDAO= new CategoriaDAO(connection);
            List<Categoria> listaDeCategorias = categoriaDAO.listar();
            listaDeCategorias.forEach(cat -> System.out.println(cat.getNome()));
        }

    }

}
