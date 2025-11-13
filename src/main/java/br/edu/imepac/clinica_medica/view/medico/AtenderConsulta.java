/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.imepac.clinica_medica.view.medico;

import br.edu.imepac.clinica_medica.dao.ProntuarioDao;
import br.edu.imepac.clinica_medica.model.Consulta;
import br.edu.imepac.clinica_medica.model.Prontuario;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mateus
 */
public class AtenderConsulta extends javax.swing.JFrame {
private Consulta consulta;

    // Construtor Vazio CORRIGIDO
    public AtenderConsulta() {
        setTitle("Atendimento da Consulta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents(); 

        pack(); // <-- CORREÇÃO: Calcula o tamanho automático
        setLocationRelativeTo(null); // <-- CORREÇÃO: Centraliza após o cálculo
        // setSize(900, 700); // <-- Linha removida
    }

    // Construtor com Consulta CORRIGIDO
    public AtenderConsulta(Consulta consulta) {
        this.consulta = consulta;
        setTitle("Atendimento da Consulta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        preencherDadosConsulta(); // Este preenche os dados

        pack(); // <-- CORREÇÃO: Calcula o tamanho automático
        setLocationRelativeTo(null); // <-- CORREÇÃO: Centraliza após o cálculo
        // setSize(900, 700); // <-- Linha removida
    }

    private void preencherDadosConsulta() {
        if (consulta.getPaciente() != null) {
            lblPaciente.setText("Paciente: " + consulta.getPaciente().getNomeCompleto());
        } else {
            lblPaciente.setText("Paciente: [não informado]");
        }

        if (consulta.getData() != null) {
            lblData.setText("Data: " + consulta.getData().toString());
        } else {
            lblData.setText("Data: [não informada]");
        }

        if (consulta.getHorario() != null) {
            lblHorario.setText("Horário: " + consulta.getHorario());
        } else {
            lblHorario.setText("Horário: [não informado]");
        }

        if (consulta.getTipo() != null) {
            lblTipo.setText("Tipo: " + consulta.getTipo());
        } else {
            lblTipo.setText("Tipo: [não informado]");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblPaciente = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblHorario = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtQueixa = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtHistorico = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtExame = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDiagnostico = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtConduta = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        Salvar = new javax.swing.JButton();
        Limpar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblPaciente.setText("Paciente");

        lblData.setText("Data");

        lblHorario.setText("Horario");

        lblTipo.setText("Tipo");

        jLabel1.setText("Queixa");

        txtQueixa.setColumns(20);
        txtQueixa.setRows(5);
        jScrollPane1.setViewportView(txtQueixa);

        jLabel2.setText("Historico Medico");

        txtHistorico.setColumns(20);
        txtHistorico.setRows(5);
        jScrollPane2.setViewportView(txtHistorico);

        jLabel3.setText("Exame");

        txtExame.setColumns(20);
        txtExame.setRows(5);
        jScrollPane3.setViewportView(txtExame);

        jLabel4.setText("Diagnostico");

        txtDiagnostico.setColumns(20);
        txtDiagnostico.setRows(5);
        jScrollPane4.setViewportView(txtDiagnostico);

        txtConduta.setColumns(20);
        txtConduta.setRows(5);
        jScrollPane5.setViewportView(txtConduta);

        jLabel5.setText("Conduta");

        Salvar.setText("Salvar");
        Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarActionPerformed(evt);
            }
        });

        Limpar.setText("Limpar");
        Limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimparActionPerformed(evt);
            }
        });

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblPaciente)
                        .addGap(69, 69, 69))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(38, 38, 38))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Salvar)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblData)
                                .addGap(95, 95, 95)
                                .addComponent(lblHorario)
                                .addGap(63, 63, 63)
                                .addComponent(lblTipo))
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(Limpar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(46, 46, 46))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPaciente)
                    .addComponent(lblData)
                    .addComponent(lblHorario)
                    .addComponent(lblTipo))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel2)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel3)
                        .addGap(92, 92, 92)
                        .addComponent(jLabel4)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Salvar)
                    .addComponent(Limpar)
                    .addComponent(jButton1))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarActionPerformed
try {
            Prontuario prontuario = new Prontuario();
            prontuario.setConsultaId(consulta.getId());
            prontuario.setQueixaPrincipal(txtQueixa.getText().trim());
            prontuario.setHistoricoDoenca(txtHistorico.getText().trim());
            prontuario.setExameFisico(txtExame.getText().trim());
            prontuario.setDiagnostico(txtDiagnostico.getText().trim());
            prontuario.setConduta(txtConduta.getText().trim());
            prontuario.setDataRegistro(Timestamp.valueOf(LocalDateTime.now()));

            ProntuarioDao dao = new ProntuarioDao();
            dao.inserir(prontuario);

            JOptionPane.showMessageDialog(this, "Prontuário salvo com sucesso!");
            dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar prontuário: " + ex.getMessage());
        }         // TODO add your handling code here:
    }//GEN-LAST:event_SalvarActionPerformed

    private void LimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimparActionPerformed
    txtQueixa.setText("");
        txtHistorico.setText("");
        txtExame.setText("");
        txtDiagnostico.setText("");
        txtConduta.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_LimparActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    Consulta c = new Consulta(); // dummy para teste
    // configure os atributos se necessário
    SwingUtilities.invokeLater(() -> new AtenderConsulta(c).setVisible(true));
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Limpar;
    private javax.swing.JButton Salvar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblHorario;
    private javax.swing.JLabel lblPaciente;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JTextArea txtConduta;
    private javax.swing.JTextArea txtDiagnostico;
    private javax.swing.JTextArea txtExame;
    private javax.swing.JTextArea txtHistorico;
    private javax.swing.JTextArea txtQueixa;
    // End of variables declaration//GEN-END:variables
}
