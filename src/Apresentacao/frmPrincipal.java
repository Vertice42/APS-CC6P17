/*
 * Created by JFormDesigner on Wed Oct 09 16:27:57 BRT 2024
 */

package Apresentacao;

import Teste.MainTeste;

import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author DAVID
 */
public class frmPrincipal extends JFrame implements ActionListener {
    public frmPrincipal() {
        initComponents();
        btnCadastro.addActionListener(this);
        btnServico.addActionListener(this);
        btnOrcamento.addActionListener(this);
        btnAprovacao.addActionListener(this);
        btnRelatorio.addActionListener(this);

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - David Braz da Silva
        btnServico = new JButton();
        btnOrcamento = new JButton();
        btnAprovacao = new JButton();
        btnRelatorio = new JButton();
        btnCadastro = new JButton();

        //======== this ========
        setTitle("SERVI\u00c7O AMBIENTAL");
        var contentPane = getContentPane();

        //---- btnServico ----
        btnServico.setText("SERVI\u00c7O");

        //---- btnOrcamento ----
        btnOrcamento.setText("OR\u00c7AMENTO");

        //---- btnAprovacao ----
        btnAprovacao.setText("APROVA\u00c7\u00c3O");

        //---- btnRelatorio ----
        btnRelatorio.setText("RELATORIO");

        //---- btnCadastro ----
        btnCadastro.setText("CADASTRO");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(btnCadastro, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnServico, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnOrcamento, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAprovacao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRelatorio, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(354, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(btnCadastro, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnServico, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnOrcamento, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnAprovacao, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnRelatorio, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(45, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - David Braz da Silva
    private JButton btnServico;
    private JButton btnOrcamento;
    private JButton btnAprovacao;
    private JButton btnRelatorio;
    private JButton btnCadastro;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCadastro){
            frmCadastro frm = new frmCadastro();
            frm.setVisible(true);
        }
    }
}
