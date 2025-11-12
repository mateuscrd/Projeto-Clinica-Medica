/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.imepac.clinica_medica.view.atendente;

import br.edu.imepac.clinica_medica.dao.ConsultaDao;
import br.edu.imepac.clinica_medica.model.Consulta;
import br.edu.imepac.clinica_medica.model.Convenio;
import br.edu.imepac.clinica_medica.model.Medico;
import br.edu.imepac.clinica_medica.model.Paciente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mateus
 */
public class ListarConsulta extends javax.swing.JFrame {
 private DefaultTableModel modeloTabela;
    
   public ListarConsulta() {
    initComponents();       // chama primeiro para garantir inicialização dos componentes
    setTitle("Listar Consultas");
    setSize(650, 510);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    configurarTabela();
    configurarEventos();
    carregarTodasConsultas();
}

    private void configurarTabela() {
        String[] colunas = {
                "ID", "Paciente", "Médico", "Data Consulta", "Hora",
                "Tipo", "Convênio", "Observações"
        };

        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jTable1.setModel(modeloTabela);
    }

    private void configurarEventos() {
        Buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarPorData();
            }
        });

        ListarTodos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                carregarTodasConsultas();
            }
        });

        Editar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarConsulta();
            }
        });

        Excluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirConsulta();
            }
        });

        Sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void carregarTodasConsultas() {
        try {
            ConsultaDao dao = new ConsultaDao();
            List<Consulta> consultas = dao.buscarTodos();
            preencherTabela(consultas);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar consultas: " + e.getMessage());
        }
    }

    private void buscarPorData() {
        String dataStr = txtDataFiltro.getText().trim();
        if (dataStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe uma data para buscar.");
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            sdf.parse(dataStr);

            ConsultaDao dao = new ConsultaDao();
            List<Consulta> consultas = dao.buscarPorData(dataStr);
            preencherTabela(consultas);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data inválida ou erro na busca: " + e.getMessage());
        }
    }

    private void preencherTabela(List<Consulta> consultas) {
        modeloTabela.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Consulta c : consultas) {
            Paciente paciente = c.getPaciente();
            Medico medico = c.getMedico();
            Convenio convenio = c.getConvenio();

            modeloTabela.addRow(new Object[]{
                    c.getId(),
                    paciente != null ? paciente.getNomeCompleto() : "N/A",
                    medico != null ? medico.getNome() : "N/A",
                    c.getData() != null ? sdf.format(java.sql.Date.valueOf(c.getData())) : "",
                    c.getHorario(),
                    c.getTipo(),
                    convenio != null ? convenio.getNomeEmpresa() : "Particular",
                    c.getObservacoes()
            });
        }
    }

    private void editarConsulta() {
        int linha = jTable1.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para editar.");
            return;
        }

        int idConsulta = (int) modeloTabela.getValueAt(linha, 0);
        try {
            ConsultaDao dao = new ConsultaDao();
            Consulta consulta = dao.buscarPorId(idConsulta);
            if (consulta != null) {
                AgendarConsultas tela = new AgendarConsultas(consulta);
                tela.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Consulta não encontrada.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar consulta para edição: " + e.getMessage());
        }
    }

    private void excluirConsulta() {
        int linha = jTable1.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para excluir.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Confirma exclusão da consulta?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        int idConsulta = (int) modeloTabela.getValueAt(linha, 0);
        try {
            ConsultaDao dao = new ConsultaDao();
            dao.excluir(idConsulta);
            JOptionPane.showMessageDialog(this, "Consulta excluída com sucesso!");
            carregarTodasConsultas();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir consulta: " + e.getMessage());
        }
    } @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtDataFiltro = new javax.swing.JTextField();
        Buscar = new javax.swing.JButton();
        ListarTodos = new javax.swing.JButton();
        Editar = new javax.swing.JButton();
        Excluir = new javax.swing.JButton();
        Sair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Data da Consulta");

        Buscar.setText("Buscar");

        ListarTodos.setText("Listar");

        Editar.setText("Editar");

        Excluir.setText("Excluir");

        Sair.setText("Sair");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Editar, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(Excluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Sair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtDataFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(Buscar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ListarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDataFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Buscar)
                        .addGap(260, 260, 260)
                        .addComponent(Editar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Excluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sair))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ListarTodos)))
                .addGap(9, 22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ListarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListarConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JButton Editar;
    private javax.swing.JButton Excluir;
    private javax.swing.JButton ListarTodos;
    private javax.swing.JButton Sair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtDataFiltro;
    // End of variables declaration//GEN-END:variables
}
