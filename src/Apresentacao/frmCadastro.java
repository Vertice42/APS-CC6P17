/*
 * Created by JFormDesigner on Tue Oct 08 16:04:00 BRT 2024
 */

package Apresentacao;

import Modelo.OpenCan;
import jdk.jfr.Label;

import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.UUID;

/**
 * @author DAVID
 */
public class frmCadastro extends JFrame implements ActionListener {
    private OpenCan openCan;
    public frmCadastro() {
        initComponents();
        openCan = new OpenCan();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - David Braz da Silva
        lblImagem = new JLabel();
        lblNome = new JLabel();
        lblDepartamento = new JLabel();
        lblNivelAcesso = new JLabel();
        txfNome = new JTextField();
        cbxDepartamento = new JComboBox();
        cbxAcesso = new JComboBox();
        btnCancelar = new JButton();
        btnPrimeiro = new JButton();
        btnVoltar = new JButton();
        btnAvancar = new JButton();
        btnUltimo = new JButton();
        btnSalvar = new JButton();
        btnIniciar = new JButton();
        btnParar = new JButton();
        btnCapturar = new JButton();

        //======== this ========
        setTitle("CADASTRO");
        var contentPane = getContentPane();

        //---- lblNome ----
        lblNome.setText("NOME");

        //---- lblDepartamento ----
        lblDepartamento.setText("DEPARTAMENTO");

        //---- lblNivelAcesso ----
        lblNivelAcesso.setText("NIVEL DE ACESSO");

        //---- btnCancelar ----
        btnCancelar.setText("text");

        //---- btnPrimeiro ----
        btnPrimeiro.setText("Prim.");

        //---- btnVoltar ----
        btnVoltar.setText("Volt.");

        //---- btnAvancar ----
        btnAvancar.setText("Avan.");

        //---- btnUltimo ----
        btnUltimo.setText("Ulti.");

        //---- btnSalvar ----
        btnSalvar.setText("Salv.");

        //---- btnIniciar ----
        btnIniciar.setText("Ini. Camera");

        //---- btnParar ----
        btnParar.setText("Para. Camera");

        //---- btnCapturar ----
        btnCapturar.setText("Capturar Foto");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(btnIniciar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                            .addGap(60, 60, 60)
                            .addComponent(btnParar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                            .addGap(61, 61, 61)
                            .addComponent(btnCapturar, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(btnPrimeiro, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAvancar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUltimo, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 442, GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 64, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createParallelGroup()
                            .addComponent(cbxAcesso, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txfNome, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDepartamento, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxDepartamento, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNivelAcesso, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)))
                    .addGap(20, 20, 20))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnIniciar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCapturar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnParar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                    .addGap(32, 32, 32)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(lblNome)
                            .addGap(18, 18, 18)
                            .addComponent(txfNome, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblDepartamento)
                            .addGap(18, 18, 18)
                            .addComponent(cbxDepartamento, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblNivelAcesso)
                            .addGap(18, 18, 18)
                            .addComponent(cbxAcesso, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUltimo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAvancar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPrimeiro, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(87, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        btnAvancar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnPrimeiro.addActionListener(this);
        btnSalvar.addActionListener(this);
        btnUltimo.addActionListener(this);
        btnVoltar.addActionListener(this);
        btnIniciar.addActionListener(this);
        btnParar.addActionListener(this);
        btnCapturar.addActionListener(this);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - David Braz da Silva
    private JLabel lblImagem;
    private JLabel lblNome;
    private JLabel lblDepartamento;
    private JLabel lblNivelAcesso;
    private JTextField txfNome;
    private JComboBox cbxDepartamento;
    private JComboBox cbxAcesso;
    private JButton btnCancelar;
    private JButton btnPrimeiro;
    private JButton btnVoltar;
    private JButton btnAvancar;
    private JButton btnUltimo;
    private JButton btnSalvar;
    private JButton btnIniciar;
    private JButton btnParar;
    private JButton btnCapturar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIniciar) {

            new Thread(() -> openCan.openWebcam(lblImagem)).start();
            btnIniciar.setEnabled(false);
            btnParar.setEnabled(true);
            btnCapturar.setEnabled(true);

        }
        if (e.getSource() == btnParar) {
            openCan.stopWebcam();
            btnIniciar.setEnabled(true);
            btnParar.setEnabled(false);

        }
        if (e.getSource() == btnCapturar) {
            openCan.stopWebcam();
            openCan.saveImage("captured_image_"+ UUID.randomUUID() +".png");
        }
        if (e.getSource() == btnCancelar) {
            this.dispose();
        }

    }
}
