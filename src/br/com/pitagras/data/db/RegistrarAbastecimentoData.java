package br.com.pitagras.data.db;

import br.com.pitagras.domain.RegistroAbastecimento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrarAbastecimentoData {
    private ConexaoDb conexao;

    public RegistrarAbastecimentoData() {
        conexao = new ConexaoDb();
    }

    // Inserir dados (create)
    public boolean cadastrarAbastecimento(RegistroAbastecimento registroAbastecimento) {
        try {
            try (PreparedStatement comando = conexao.getConexao()
                    .prepareStatement(
                            "INSERT INTO combustivel(nome_posto, valor_total, quantidade_combustivel, data_abastecimento) VALUES (?,?,?,?)"
                    )) {
                comando.setString(1, registroAbastecimento.getNomePosto());
                comando.setDouble(2, registroAbastecimento.getValorTotal());
                comando.setDouble(3, registroAbastecimento.getQuantidade());
                comando.setDate(4, new java.sql.Date(registroAbastecimento.getDataAbastecimento().getTime()));
                comando.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet carregarDados() {
        ResultSet dados = null;

        try {
            PreparedStatement comando = conexao.getConexao().prepareStatement("SELECT * FROM combustivel");
            dados = comando.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dados;
    }
}
