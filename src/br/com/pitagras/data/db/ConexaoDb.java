package br.com.pitagras.data.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDb {
    private Connection conexao;
    private String caminhoDb = "jdbc:mysql://172.17.0.2:3306/controle_combustivel?useSSL=false&serverTimezone=UTC";
    private String usuario="fabricio";
    private String senha="P0llyanna";

    public ConexaoDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(caminhoDb, usuario, senha);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public  Connection getConexao() {
        return conexao;
    }
}
