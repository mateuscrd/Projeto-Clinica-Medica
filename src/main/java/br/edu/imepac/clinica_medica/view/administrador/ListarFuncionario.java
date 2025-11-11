/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.imepac.clinica_medica.view.administrador;

import br.edu.imepac.clinica_medica.dao.FuncionarioDao;
import br.edu.imepac.clinica_medica.model.Funcionario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mateus
 */
public class ListarFuncionario extends javax.swing.JFrame {

    private CadastrarUsuario telaCadastrarUsuario;
    private boolean modoSelecao = false;
    private Funcionario funcionarioSelecionado;

    public ListarFuncionario() {
        initComponents();
        carregarFuncionarios();
        Selecionar.setVisible(false); // Oculta o botão por padrão
    }

    public ListarFuncionario(CadastrarUsuario telaCadastrarUsuario) {
        this();
        this.telaCadastrarUsuario = telaCadastrarUsuario;
        this.modoSelecao = true;
        Selecionar.setVisible(true);
    }

    public Funcionario getFuncionarioSelecionado() {
        return funcionarioSelecionado;
    }

    private void carregarFuncionarios() {
        FuncionarioDao dao = new FuncionarioDao();
        List<Funcionario> lista = dao.buscarTodos();
        carregarTabela(lista);
    }

    private void carregarTabela(List<Funcionario> funcionarios) {
        Collections.sort(funcionarios, Comparator.comparing(Funcionario::getNomeCompleto));

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nome Completo");
        modelo.addColumn("RG");
        modelo.addColumn("CPF");
        modelo.addColumn("Rua");
        modelo.addColumn("Número");
        modelo.addColumn("Complemento");
        modelo.addColumn("Bairro");
        modelo.addColumn("Cidade");
        modelo.addColumn("Estado");
        modelo.addColumn("CEP");
        modelo.addColumn("Telefone");
        modelo.addColumn("CTPS");
        modelo.addColumn("PIS");

        for (Funcionario f : funcionarios) {
            modelo.addRow(new Object[]{
                f.getId(),
                f.getNomeCompleto(),
                f.getRg(),
                f.getCpf(),
                f.getRua(),
                f.getNumero(),
                f.getComplemento(),
                f.getBairro(),
                f.getCidade(),
                f.getEstado(),
                f.getCep(),
                f.getTelefoneFixo(),
                f.getTelefoneCelular(),
                f.getCtps(),
                f.getPis()
            });
        }

        tabelaFuncionarios.setModel(modelo);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtCPF = new javax.swing.JTextField();
        Buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaFuncionarios = new javax.swing.JTable();
        listarTodos = new javax.swing.JButton();
        Selecionar = new javax.swing.JButton();
        Sair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("CPF");

        txtCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPFActionPerformed(evt);
            }
        });

        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });

        tabelaFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "RG", "CPF", "Rua", "Numero", "Complemento", "Bairro", "Cidade", "Estado", "CEP", "Telefone", "CTPS", "PIS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaFuncionarios.setName(""); // NOI18N
        jScrollPane1.setViewportView(tabelaFuncionarios);

        listarTodos.setText("Listar Todos");
        listarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarTodosActionPerformed(evt);
            }
        });

        Selecionar.setText("Selecionar");
        Selecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelecionarActionPerformed(evt);
            }
        });

        Sair.setText("Sair");
        Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Buscar)
                        .addGap(75, 75, 75))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(Selecionar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(listarTodos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(Sair, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Buscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addComponent(listarTodos)
                .addGap(18, 18, 18)
                .addComponent(Selecionar)
                .addGap(18, 18, 18)
                .addComponent(Sair)
                .addGap(7, 7, 7))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPFActionPerformed

    private void listarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarTodosActionPerformed
        FuncionarioDao dao = new FuncionarioDao();
        List<Funcionario> lista = dao.buscarTodos();
        carregarTabela(lista); // TODO add your handling code here:
    }//GEN-LAST:event_listarTodosActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
       String cpf = txtCPF.getText().trim();
    if (!cpf.isEmpty()) {
        FuncionarioDao dao = new FuncionarioDao();
        Funcionario funcionario = dao.buscarPorCpf(cpf);
        if (funcionario != null) {
            List<Funcionario> lista = new ArrayList<>();
            lista.add(funcionario);
            carregarTabela(lista);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Funcionário não encontrado.");
        }
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Digite um CPF.");
}        // TODO add your handling code here:
    }//GEN-LAST:event_BuscarActionPerformed

    private void SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SairActionPerformed
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_SairActionPerformed

    private void SelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelecionarActionPerformed
    int linhaSelecionada = tabelaFuncionarios.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um funcionário na tabela.");
            return;
        }

        try {
            int id = (int) tabelaFuncionarios.getValueAt(linhaSelecionada, 0);
            FuncionarioDao dao = new FuncionarioDao();
            Funcionario funcionario = dao.buscarPorId(id);

            if (funcionario != null && telaCadastrarUsuario != null) {
                telaCadastrarUsuario.definirFuncionarioSelecionado(funcionario);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Funcionário não encontrado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao selecionar funcionário: " + e.getMessage());
            e.printStackTrace();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_SelecionarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListarFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListarFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListarFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListarFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListarFuncionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JButton Sair;
    private javax.swing.JButton Selecionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton listarTodos;
    private javax.swing.JTable tabelaFuncionarios;
    private javax.swing.JTextField txtCPF;
    // End of variables declaration//GEN-END:variables
}
