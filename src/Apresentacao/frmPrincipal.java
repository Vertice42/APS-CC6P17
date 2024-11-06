/*
 * Created by JFormDesigner on Wed Oct 09 16:27:57 BRT 2024
 */

package Apresentacao;

import DAL.CadUsers;
import Modelo.Estatico;

import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author DAVID
 */
public class frmPrincipal extends JFrame implements ActionListener {
    public frmPrincipal(int Usuario) {
        System.out.print(Usuario);
        initComponents();
        btnServico.addActionListener(this);
        btnOrcamento.addActionListener(this);
        btnAprovacao.addActionListener(this);
        btnRelatorio.addActionListener(this);
        CadUsers cad = new CadUsers();
        lblUser.setText("Usuário: " + cad.BuscarUserId(Usuario));
        if(Estatico.Priv == 1){
            btnAprovacao.setEnabled(false);
            btnOrcamento.setEnabled(false);
            btnRelatorio.setEnabled(false);
        }
        if(Estatico.Priv == 2){
            btnAprovacao.setEnabled(false);
            btnServico.setEnabled(false);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - David Braz da Silva
        btnServico = new JButton();
        btnOrcamento = new JButton();
        btnAprovacao = new JButton();
        btnRelatorio = new JButton();
        lblUser = new JLabel();
        separator1 = new JSeparator();

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

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(btnServico, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addGap(348, 348, 348))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(btnOrcamento, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(separator1, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblUser, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                .addComponent(btnAprovacao, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnRelatorio, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(19, Short.MAX_VALUE))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(lblUser, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(btnServico, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnOrcamento, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnAprovacao, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnRelatorio, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                            .addGap(36, 36, 36)
                            .addComponent(separator1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(58, Short.MAX_VALUE))
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
    private JLabel lblUser;
    private JSeparator separator1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
