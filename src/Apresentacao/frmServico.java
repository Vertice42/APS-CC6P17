/*
 * Created by JFormDesigner on Wed Oct 09 14:58:37 BRT 2024
 */

package Apresentacao;

import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author DAVID
 */
public class frmServico extends JFrame {
    public frmServico() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - David Braz da Silva
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField3 = new JTextField();
        label3 = new JLabel();
        label4 = new JLabel();
        comboBox1 = new JComboBox();
        label5 = new JLabel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        btnPrimeiro = new JButton();
        btnVoltar = new JButton();
        btnAvancar = new JButton();
        btnUltimo = new JButton();
        btnSalvar = new JButton();
        btnAlterar = new JButton();
        btnDeletar = new JButton();
        btnCancelar = new JButton();
        cbxSolicitante = new JComboBox();

        //======== this ========
        setTitle("Servi\u00e7o");
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Cod. Servi\u00e7o:");

        //---- label2 ----
        label2.setText("Nome do Solicitante:");

        //---- label3 ----
        label3.setText("Local do servi\u00e7o:");

        //---- label4 ----
        label4.setText("Tipo de Servi\u00e7o:");

        //---- label5 ----
        label5.setText("Oberva\u00e7\u00e3o sobre a Solicita\u00e7\u00e3o:");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textArea1);
        }

        //---- btnPrimeiro ----
        btnPrimeiro.setText("Prim.");

        //---- btnVoltar ----
        btnVoltar.setText("Volt.");

        //---- btnAvancar ----
        btnAvancar.setText("Avan.");

        //---- btnUltimo ----
        btnUltimo.setText("Ult.");

        //---- btnSalvar ----
        btnSalvar.setText("Salv.");

        //---- btnAlterar ----
        btnAlterar.setText("Alt.");

        //---- btnDeletar ----
        btnDeletar.setText("Del.");

        //---- btnCancelar ----
        btnCancelar.setText("Canc.");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(btnPrimeiro, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnVoltar)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAvancar)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnUltimo)
                            .addGap(41, 41, 41)
                            .addComponent(btnSalvar)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAlterar)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnDeletar)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCancelar))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(46, 46, 46)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
                                    .addGap(95, 95, 95)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbxSolicitante, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
                                    .addGap(49, 49, 49)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(textField3, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))))
                    .addGap(15, 15, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(48, 48, 48)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxSolicitante, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(39, 39, 39)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(41, 41, 41)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(textField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPrimeiro)
                        .addComponent(btnVoltar)
                        .addComponent(btnAvancar)
                        .addComponent(btnUltimo)
                        .addComponent(btnSalvar)
                        .addComponent(btnAlterar)
                        .addComponent(btnDeletar)
                        .addComponent(btnCancelar))
                    .addGap(58, 58, 58))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - David Braz da Silva
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField3;
    private JLabel label3;
    private JLabel label4;
    private JComboBox comboBox1;
    private JLabel label5;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JButton btnPrimeiro;
    private JButton btnVoltar;
    private JButton btnAvancar;
    private JButton btnUltimo;
    private JButton btnSalvar;
    private JButton btnAlterar;
    private JButton btnDeletar;
    private JButton btnCancelar;
    private JComboBox cbxSolicitante;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
