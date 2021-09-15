package br.com.pitagras.ui;

import br.com.pitagras.data.db.RegistrarAbastecimentoData;
import br.com.pitagras.domain.RegistroAbastecimento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TelaCadastro {
    public JPanel containerPrincipal;
    private JLabel jLabelData;
    private JFormattedTextField formattedTextFieldData;
    private JLabel jLabelTipoCombustivel;
    private JRadioButton gasolinaRadioButton;
    private JRadioButton etanolRadioButton;
    private JRadioButton gasRadioButton;
    private JLabel textFieldPosto;
    private JTextField textFieldNomePosto;
    private JLabel jLabelQuantidade;
    private JFormattedTextField formattedTextFieldQuantidade;
    private JFormattedTextField formattedTextFieldValorTotal;
    private JButton buttonSalvar;
    private JButton buttonAtualizar;
    private JButton buttonExcluir;
    private JTable table1;
    private DefaultTableModel modelo;

    private RegistrarAbastecimentoData bd = new RegistrarAbastecimentoData();

    public TelaCadastro() {
        configurarListeners();
        carregarDados();
    }

    private void configurarListeners() {
        configurarBotoes();
        configurarCliqueTabela();
    }

    private void configurarCliqueTabela() {
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 1) {
                    System.out.println("Entramos com duplo clique");
                    var dados = modelo.getDataVector().elementAt(table1.getSelectedRow());
                    String nomePosto = (String) dados.get(1);
                    String valorTotal = dados.get(2).toString();
                    String quantidade = dados.get(3).toString();
                    String dataAbastecimento = dados.get(4).toString();
                    textFieldNomePosto.setText(nomePosto);
                    formattedTextFieldQuantidade.setText(quantidade);
                    formattedTextFieldValorTotal.setText(valorTotal);
                    formattedTextFieldData.setText(dataAbastecimento);
                    containerPrincipal.repaint();

                }
            }
        });
    }

    private void carregarDados() {
        modelo.setNumRows(0);
        try {
            ResultSet dados = bd.carregarDados();
            while (dados.next()) {
                int id = dados.getInt("id");
                String nomePosto = dados.getString("nome_posto");
                double valorAbastecimento = dados.getDouble("valor_total");
                double quantidade = dados.getDouble("quantidade_combustivel");
                Date dataAbastecimento = dados.getDate("data_abastecimento");
                modelo.addRow(new Object[]{id, nomePosto, valorAbastecimento, quantidade, dataAbastecimento});
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void configurarBotoes() {
        buttonSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                RegistroAbastecimento data = new RegistroAbastecimento();
                try {
                    getData(data);
                    RegistrarAbastecimentoData registrarAbastecimentoData = new RegistrarAbastecimentoData();
                    if (registrarAbastecimentoData.cadastrarAbastecimento(data)) {
                        JOptionPane.showMessageDialog(null, "Dados Gravados com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Falha ao gravar dados, tente novamente mais tarde.");
                    }
                    carregarDados();
                    modelo.fireTableDataChanged();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        buttonAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        buttonExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        formattedTextFieldData = new JFormattedTextField(dateFormat);
        formattedTextFieldData.setValue(new Date());
        modelo = new DefaultTableModel();
        table1 = new JTable(modelo);
        modelo.addColumn("id");
        modelo.addColumn("Posto");
        modelo.addColumn("Valor Total");
        modelo.addColumn("Quantidade Combustível");
        modelo.addColumn("Data do Abastecimento");
        modelo.addColumn("Tipo Combustível");

    }

    public void setData(RegistroAbastecimento data) {
        formattedTextFieldData.setText(data.getDataAbastecimento().toString());
        textFieldNomePosto.setText(data.getNomePosto());
    }

    public void getData(RegistroAbastecimento data) throws ParseException {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        Date dataFormatada = formatador.parse(formattedTextFieldData.getText());
        data.setDataAbastecimento(dataFormatada);
        data.setNomePosto(textFieldNomePosto.getText());
        data.setQuantidade(Double.parseDouble(formattedTextFieldQuantidade.getText()));
        data.setValorTotal(Double.parseDouble(formattedTextFieldValorTotal.getText()));
    }

    public boolean isModified(RegistroAbastecimento data) {
        if (formattedTextFieldData.getText() != null ? !formattedTextFieldData.getText().equals(data.getDataAbastecimento().toString()) : data.getDataAbastecimento() != null)
            return true;
        if (textFieldNomePosto.getText() != null ? !textFieldNomePosto.getText().equals(data.getNomePosto()) : data.getNomePosto() != null)
            return true;
        return false;
    }
}
