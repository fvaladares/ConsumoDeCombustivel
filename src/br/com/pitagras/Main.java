package br.com.pitagras;

import br.com.pitagras.ui.TelaCadastro;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Consumo de combustível");
                frame.setContentPane(new TelaCadastro().containerPrincipal);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
