/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.imepac.clinica_medica.view.medico;

import br.edu.imepac.clinica_medica.dao.ProntuarioDao;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mateus
 */
public class Prontuario extends javax.swing.JFrame {
private int medicoId; // deve ser inicializado com o médico logado
    private ProntuarioDao prontuarioDao;

    public Prontuario(int medicoId) {
        this.medicoId = medicoId;
        prontuarioDao = new ProntuarioDao();
initComponents();
 String[] colunas = {"ID", "Consulta ID", "Queixa Principal", "Data Registro"};
    DefaultTableModel model = new DefaultTableModel(colunas, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    tabelaProntuarios.setModel(model);

    carregarProntuarios();

    Visualizar.addActionListener(e -> visualizarProntuario());
    Sair.addActionListener(e -> sair());

        // initComponents é gerado pelo NetBeans, então não incluo aqui
        // initComponents();

        carregarProntuarios();

        Visualizar.addActionListener(e -> visualizarProntuario());
        Sair.addActionListener(e -> sair());
    }

    private void carregarProntuarios() {
        try {
            List<br.edu.imepac.clinica_medica.model.Prontuario> lista = prontuarioDao.buscarPorMedicoId(medicoId);

            DefaultTableModel model = (DefaultTableModel) tabelaProntuarios.getModel();
            model.setRowCount(0); // limpa tabela

            for (br.edu.imepac.clinica_medica.model.Prontuario p : lista) {
                model.addRow(new Object[]{
                    p.getId(),
                    p.getConsultaId(),
                    p.getQueixaPrincipal(),
                    p.getDataRegistro()
                });
            }

            lblTotalProntuarios.setText("Total de prontuários: " + lista.size());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar prontuários: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void visualizarProntuario() {
        int linhaSelecionada = tabelaProntuarios.getSelectedRow();
        if (linhaSelecionada < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um prontuário para visualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int prontuarioId = (int) tabelaProntuarios.getValueAt(linhaSelecionada, 0);

        try {
            br.edu.imepac.clinica_medica.model.Prontuario prontuario = prontuarioDao.buscarPorId(prontuarioId);
            if (prontuario != null) {
                // Aqui você pode abrir uma nova janela para mostrar os detalhes do prontuário
                // Exemplo simples só mostrando os dados num JOptionPane
                String mensagem = "Prontuário ID: " + prontuario.getId() + "\n" +
                        "Consulta ID: " + prontuario.getConsultaId() + "\n" +
                        "Queixa Principal: " + prontuario.getQueixaPrincipal() + "\n" +
                        "Histórico da Doença: " + prontuario.getHistoricoDoenca() + "\n" +
                        "Exame Físico: " + prontuario.getExameFisico() + "\n" +
                        "Diagnóstico: " + prontuario.getDiagnostico() + "\n" +
                        "Conduta: " + prontuario.getConduta() + "\n" +
                        "Data Registro: " + prontuario.getDataRegistro();

                JOptionPane.showMessageDialog(this, mensagem, "Detalhes do Prontuário", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(this, "Prontuário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar prontuário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sair() {
        this.dispose();
    } @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTotalProntuarios = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProntuarios = new javax.swing.JTable();
        Visualizar = new javax.swing.JButton();
        Sair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTotalProntuarios.setText("Total");

        tabelaProntuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelaProntuarios);

        Visualizar.setText("Visualizar");

        Sair.setText("Sair");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTotalProntuarios)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(Visualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Sair)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblTotalProntuarios)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Visualizar)
                    .addComponent(Sair))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Simulando que o médico logado tem ID 1
        int medicoId = 6;

        javax.swing.SwingUtilities.invokeLater(() -> {
            Prontuario tela = new Prontuario(medicoId);
            tela.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);{
            tela.setVisible(true);
            }
        });
      }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Sair;
    private javax.swing.JButton Visualizar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalProntuarios;
    private javax.swing.JTable tabelaProntuarios;
    // End of variables declaration//GEN-END:variables
}
