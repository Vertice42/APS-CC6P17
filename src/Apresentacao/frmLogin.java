/*
 * Created by JFormDesigner on Tue Oct 22 08:25:05 BRT 2024
 */

package Apresentacao;

import Modelo.CompareImages;
import Modelo.OpenCan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author DAVID
 */
public class frmLogin extends JFrame implements ActionListener {
    private OpenCan openCan;

    public frmLogin() {
        initComponents();
        openCan = new OpenCan();
        new Thread(() -> openCan.openWebcam(lblImg)).start();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - David Braz da Silva
        lblImg = new JLabel();
        btnLogin = new JButton();
        btnCancelar = new JButton();
        btnCadastrar = new JButton();

        //======== this ========
        setTitle("LOGIN");
        var contentPane = getContentPane();

        //---- btnLogin ----
        btnLogin.setText("LOGIN");
        btnLogin.setBackground(Color.green);

        //---- btnCancelar ----
        btnCancelar.setText("CANCELAR");
        btnCancelar.setBackground(Color.red);

        //---- btnCadastrar ----
        btnCadastrar.setText("CADASTRAR");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblImg, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addComponent(btnCadastrar, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                    .addContainerGap(11, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(lblImg, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(75, 75, 75)
                            .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(62, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        btnCadastrar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnLogin.addActionListener(this);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - David Braz da Silva
    private JLabel lblImg;
    private JButton btnLogin;
    private JButton btnCancelar;
    private JButton btnCadastrar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            OpenCan openCan = new OpenCan();
            String name = "captured_image_" + UUID.randomUUID() + ".png";
            openCan.stopWebcam();
            Boolean salvo = openCan.saveImage(name);
            if(salvo == true) {
                System.out.print("Imagem Salva");
//                CompareImages compareImages = new CompareImages();
//                compareImages.CompararImagem("src/resources/" + name);
            } else {
                System.out.println("Erro ao Salvar Imagem");
            }

        }
        if (e.getSource() == btnCancelar) {
            System.exit(0);
        }
        if (e.getSource() == btnCadastrar) {

        }
    }
}
