/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.imepac.clinica_medica.view.medico;

import br.edu.imepac.clinica_medica.dao.ConsultaDao;
import br.edu.imepac.clinica_medica.model.Consulta;
import br.edu.imepac.clinica_medica.model.Medico;
import br.edu.imepac.clinica_medica.service.login.SessaoMedico;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mateus
 */
public class ListarConsulta extends javax.swing.JFrame {
private List<Consulta> consultas;

    public ListarConsulta() {
        initComponents();
        setLocationRelativeTo(null);
        configurarEventos(); // ← IMPORTANTE
        carregarConsultas();
        
    }

    private void configurarEventos() {
      
        Sair.addActionListener(e -> dispose());
       
        

    }

    private void carregarConsultas() {
    Medico medico = SessaoMedico.getMedicoLogado();

    if (medico == null) {
        JOptionPane.showMessageDialog(this, "Nenhum médico logado.");
        dispose();
        return;
    }

    ConsultaDao dao = new ConsultaDao();
    consultas = dao.buscarPorMedicoFuturas(medico.getId());

    // --- CORREÇÃO APLICADA ---

    // 1. Defina os nomes corretos para as 4 colunas
    String[] colunas = {"ID", "Paciente", "Data", "Hora"};

    // 2. Crie um NOVO modelo de tabela com esses cabeçalhos
    DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

    // 3. Preencha o NOVO modelo com os dados (removendo a coluna "Tipo")
    for (Consulta c : consultas) {
        modelo.addRow(new Object[]{
            c.getId(),
            c.getPaciente() != null ? c.getPaciente().getNomeCompleto() : "Paciente nulo",
            c.getData() != null ? c.getData().toString() : "",
            c.getHorario() != null ? c.getHorario().toString() : ""
            // Coluna "Tipo" removida daqui
        });
    }

    // 4. Defina este NOVO modelo na sua jTable1
    jTable1.setModel(modelo);
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Atender = new javax.swing.JButton();
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

        jLabel1.setText("Consultas Agendadas");

        Atender.setText("Atender");
        Atender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtenderActionPerformed(evt);
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(Atender)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Sair)
                .addGap(127, 127, 127))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Atender)
                    .addComponent(Sair))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AtenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtenderActionPerformed
        int linha = jTable1.getSelectedRow();
        if (linha >= 0) {
            Consulta consultaSelecionada = consultas.get(linha);
            new AtenderConsulta(consultaSelecionada).setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para atender.");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_AtenderActionPerformed

    private void SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SairActionPerformed
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_SairActionPerformed
public static void main(String[] args) {
    // Simula um médico logado para teste
    br.edu.imepac.clinica_medica.model.Medico medicoTeste = new br.edu.imepac.clinica_medica.model.Medico();
    medicoTeste.setId(6); // Substitua com um ID válido no seu banco
    medicoTeste.setNome("Dr. Teste");

    // Registra na sessão
    br.edu.imepac.clinica_medica.service.login.SessaoMedico.setMedicoLogado(medicoTeste);

    // Inicia a tela
    javax.swing.SwingUtilities.invokeLater(() -> {
        new ListarConsulta().setVisible(true);
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Atender;
    private javax.swing.JButton Sair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
