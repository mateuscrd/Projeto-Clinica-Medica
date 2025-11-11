/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.imepac.clinica_medica.view.administrador;

import br.edu.imepac.clinica_medica.dao.UsuarioDao;
import br.edu.imepac.clinica_medica.model.Usuario;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mateus
 */
public class ListarUsuario extends javax.swing.JFrame {

    /**
     * Creates new form ListarUsuario
     */
    public ListarUsuario() {
    initComponents();
    setLocationRelativeTo(null); // centraliza a janela
}
 private void BuscarUsuario() {
        String login = txtLogin.getText().trim();
        if (login.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um login para buscar.");
            return;
        }

        UsuarioDao dao = new UsuarioDao();
        List<Usuario> usuarios = dao.buscarPorLogin(login);
        atualizarTabela(usuarios);
    }

    private void listarTodosUsuarios() {
        UsuarioDao dao = new UsuarioDao();
        List<Usuario> usuarios = dao.buscarTodos();
        atualizarTabela(usuarios);
    }

    private void EditarUsuario() {
        int linhaSelecionada = jTable1.getSelectedRow();
        if (linhaSelecionada >= 0) {
            int id = (int) jTable1.getValueAt(linhaSelecionada, 0);
            UsuarioDao dao = new UsuarioDao();
            Usuario usuario = dao.buscarPorId(id);

            if (usuario != null) {
                CadastrarUsuario editar = new CadastrarUsuario();
                editar.setVisible(true);
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para editar.");
        }
    }

    private void deletar() {
        int linhaSelecionada = jTable1.getSelectedRow();
        if (linhaSelecionada >= 0) {
            int id = (int) jTable1.getValueAt(linhaSelecionada, 0);
            int confirmacao = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja excluir este usuário?", "Confirmação", JOptionPane.YES_NO_OPTION);

            if (confirmacao == JOptionPane.YES_OPTION) {
                UsuarioDao dao = new UsuarioDao();
                boolean sucesso = dao.deletar(id);
                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Usuário excluído com sucesso.");
                    listarTodosUsuarios();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir o usuário.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para excluir.");
        }
    }

    private void atualizarTabela(List<Usuario> usuarios) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // limpa

        for (Usuario u : usuarios) {
            model.addRow(new Object[]{u.getId(), u.getLogin(), u.getPermissao()});
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        Buscar = new javax.swing.JButton();
        ListarTodos = new javax.swing.JButton();
        Editar = new javax.swing.JButton();
        Excluir = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Usuario", "Funcao"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Usuario");

        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });

        ListarTodos.setText("Listar");
        ListarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListarTodosActionPerformed(evt);
            }
        });

        Editar.setText("Editar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });

        Excluir.setText("Excluir");
        Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcluirActionPerformed(evt);
            }
        });

        jButton2.setText("Sair");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(Buscar)))
                        .addGap(0, 16, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(157, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Excluir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(Editar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ListarTodos)
                        .addGap(140, 140, 140))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Buscar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 224, Short.MAX_VALUE)
                        .addComponent(Editar)
                        .addGap(12, 12, 12)
                        .addComponent(Excluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(ListarTodos))))
                .addGap(12, 12, 12))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(47, 47, 47))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluirActionPerformed
    int linhaSelecionada = jTable1.getSelectedRow();

    if (linhaSelecionada == -1) {
        JOptionPane.showMessageDialog(this, "Selecione um usuário para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int confirmacao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o usuário selecionado?", "Confirmação", JOptionPane.YES_NO_OPTION);

    if (confirmacao == JOptionPane.YES_OPTION) {
        int id = (int) jTable1.getValueAt(linhaSelecionada, 0); // Supondo que a primeira coluna seja o ID

        UsuarioDao dao = new UsuarioDao();
        dao.deletar(id);

        // Atualiza tabela
        List<Usuario> usuarios = dao.buscarTodos();
        carregarTabela(usuarios);
    }        // TODO add your handling code here:
    }//GEN-LAST:event_ExcluirActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
    int linha = jTable1.getSelectedRow();
    if (linha >= 0) {
        int id = (int) jTable1.getValueAt(linha, 0); // ID está na primeira coluna
        UsuarioDao dao = new UsuarioDao();
        Usuario usuario = dao.buscarPorId(id);

        if (usuario != null) {
            CadastrarUsuario editar = new CadastrarUsuario();
            editar.setLocationRelativeTo(null); // opcional, centraliza
            editar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao carregar usuário.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecione um usuário na tabela.");
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_EditarActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        String login = txtLogin.getText().trim();

    if (login.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Digite um login para buscar.", "Atenção", JOptionPane.WARNING_MESSAGE);
        return;
    }

    UsuarioDao dao = new UsuarioDao();
    List<Usuario> usuarios = dao.buscarPorLogin(login);

    if (usuarios.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Nenhum usuário encontrado com esse login.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }
    carregarTabela(usuarios);

      // TODO add your handling code here:
    }//GEN-LAST:event_BuscarActionPerformed

    private void ListarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarTodosActionPerformed
         UsuarioDao dao = new UsuarioDao();
    List<Usuario> usuarios = dao.buscarTodos();
    carregarTabela(usuarios);
        // TODO add your handling code here:
    }//GEN-LAST:event_ListarTodosActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void carregarTabela(List<Usuario> usuarios) {
    DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
    modelo.setRowCount(0);

    for (Usuario u : usuarios) {
        modelo.addRow(new Object[]{
            u.getId(),
            u.getLogin(),
            u.getPermissao(),
            u.getFuncionarioId()
        });
    }
}

    
 





 public class TesteListarUsuarios {
    public static void main(String[] args) {
        // Garante aparência nativa do sistema operacional
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Executa a janela na thread da interface gráfica
        SwingUtilities.invokeLater(() -> {
            new ListarUsuario().setVisible(true);
        });
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JButton Editar;
    private javax.swing.JButton Excluir;
    private javax.swing.JButton ListarTodos;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtLogin;
    // End of variables declaration//GEN-END:variables
}
