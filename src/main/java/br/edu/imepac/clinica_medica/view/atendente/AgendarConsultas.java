/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.imepac.clinica_medica.view.atendente;

import br.edu.imepac.clinica_medica.dao.ConsultaDao;
import br.edu.imepac.clinica_medica.dao.ConvenioDao;
import br.edu.imepac.clinica_medica.dao.MedicoDao;
import br.edu.imepac.clinica_medica.dao.PacienteDao;
import br.edu.imepac.clinica_medica.model.Consulta;
import br.edu.imepac.clinica_medica.model.Convenio;
import br.edu.imepac.clinica_medica.model.Medico;
import br.edu.imepac.clinica_medica.model.Paciente;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */
public class AgendarConsultas extends javax.swing.JFrame {
private Consulta consulta;

    public AgendarConsultas(Consulta consulta) {
        this.consulta = consulta;
        initComponents();
        preencherCampos();
        carregarPacientes();
        carregarMedicos();
        carregarConvenios();
        carregarHoras();
        carregarTiposConsulta();
    }

  private void preencherCampos() {
    if (consulta == null) return;

    comboPaciente.setSelectedItem(consulta.getPaciente());
    comboMedico.setSelectedItem(consulta.getMedico());
    txtData.setText(consulta.getData().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    comboHora.setSelectedItem(consulta.getHorario().toString());
    comboTipoConsulta.setSelectedItem(consulta.getTipo());

    if ("Convênio".equalsIgnoreCase(consulta.getTipo())) {
        comboConvenio.setEnabled(true);
        comboConvenio.setSelectedItem(consulta.getConvenio());
    } else {
        comboConvenio.setEnabled(false);
        comboConvenio.setSelectedItem(null);
    }

    txtObservacao.setText(consulta.getObservacoes());
}

    public AgendarConsultas() {
        setTitle("Agendamento de Consulta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        carregarPacientes();
        carregarMedicos();
        carregarConvenios();
        carregarHoras();
        carregarTiposConsulta();
        
    }

    private void carregarPacientes() {
        try {
            PacienteDao dao = new PacienteDao();
            List<Paciente> pacientes = dao.buscarTodos();
            comboPaciente.removeAllItems();
            for (Paciente p : pacientes) {
                comboPaciente.addItem(p.getNomeCompleto());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar pacientes: " + e.getMessage());
        }
    }

    private void carregarMedicos() {
        try {
            MedicoDao dao = new MedicoDao();
            List<Medico> medicos = dao.buscarTodos();
            comboMedico.removeAllItems();
            for (Medico m : medicos) {
                comboMedico.addItem(m.getNome());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar médicos: " + e.getMessage());
        }
    }

    private void carregarConvenios() {
        try {
            ConvenioDao dao = new ConvenioDao();
            List<Convenio> convenios = dao.buscarTodos();
            comboConvenio.removeAllItems();
            for (Convenio c : convenios) {
                comboConvenio.addItem(c.getNomeEmpresa());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar convênios: " + e.getMessage());
        }
    }

    private void carregarHoras() {
        comboHora.removeAllItems();
        String[] horas = {
            "08:00", "08:30", "09:00", "09:30",
            "10:00", "10:30", "11:00", "11:30",
            "13:00", "13:30", "14:00", "14:30",
            "15:00", "15:30", "16:00", "16:30",
            "17:00", "17:30", 
        };
        for (String h : horas) {
            comboHora.addItem(h);
        }
    }

    private void carregarTiposConsulta() {
        comboTipoConsulta.removeAllItems();
        comboTipoConsulta.addItem("Particular");
        comboTipoConsulta.addItem("Convênio");
        comboTipoConsulta.addActionListener((ActionEvent e) -> {
    String tipoSelecionado = (String) comboTipoConsulta.getSelectedItem();
    if ("Particular".equalsIgnoreCase(tipoSelecionado)) {
        comboConvenio.setEnabled(false);
        comboConvenio.setSelectedItem(null); // opcional: limpa seleção
    } else {
        comboConvenio.setEnabled(true);
    }
});
    }
    private void limparCampos() {
    comboPaciente.setSelectedIndex(-1);
    comboMedico.setSelectedIndex(-1);
    txtData.setText("");
    comboHora.setSelectedIndex(-1);
    comboTipoConsulta.setSelectedIndex(0); // "Particular"
    comboConvenio.setSelectedIndex(-1);
    comboConvenio.setEnabled(false); // Desabilita por padrão (já que tipo é Particular)
    txtObservacao.setText("");
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        comboPaciente = new javax.swing.JComboBox<>();
        comboMedico = new javax.swing.JComboBox<>();
        txtData = new javax.swing.JTextField();
        comboHora = new javax.swing.JComboBox<>();
        comboTipoConsulta = new javax.swing.JComboBox<>();
        comboConvenio = new javax.swing.JComboBox<>();
        txtObservacao = new javax.swing.JTextField();
        Salvar = new javax.swing.JButton();
        Limpar = new javax.swing.JButton();
        Sair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Paciente");

        jLabel2.setText("Médico");

        jLabel3.setText("Data");

        jLabel4.setText("Hora");

        jLabel5.setText("Tipo de Consulta");

        jLabel6.setText("Convênio");

        jLabel7.setText("Observação");

        comboPaciente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboMedico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboHora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboTipoConsulta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboConvenio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(comboMedico, javax.swing.GroupLayout.Alignment.LEADING, 0, 120, Short.MAX_VALUE)
                            .addComponent(txtData, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboPaciente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboHora, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboTipoConsulta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboConvenio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(Salvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Limpar)
                .addGap(72, 72, 72)
                .addComponent(Sair)
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(comboHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboTipoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(comboConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Salvar)
                    .addComponent(Limpar)
                    .addComponent(Sair))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimparActionPerformed
        limparCampos();
        // TODO add your handling code here:
    }//GEN-LAST:event_LimparActionPerformed

    private void SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarActionPerformed
     // Dentro do seu SalvarAction
try {
    String dataStr = txtData.getText().trim();
    String horaStr = (String) comboHora.getSelectedItem();
    String tipo = (String) comboTipoConsulta.getSelectedItem();
    String observacao = txtObservacao.getText();
    
    // --- INÍCIO DA CORREÇÃO ---
    
    // 1. Pegar os NOMES (Strings) selecionados
    String nomePaciente = (String) comboPaciente.getSelectedItem();
    String nomeMedico = (String) comboMedico.getSelectedItem();
    String nomeConvenio = (String) comboConvenio.getSelectedItem();
    
    // 2. Usar os NOMES para buscar os OBJETOS completos no banco
    
    // Buscar Paciente
    PacienteDao pacDao = new PacienteDao();
    List<Paciente> pacientes = pacDao.buscarPorNome(nomePaciente);
    Paciente paciente = (pacientes.isEmpty()) ? null : pacientes.get(0); // Pega o primeiro da lista
    
    // Buscar Medico (VOCÊ PRECISA CRIAR O 'buscarPorNome' NO MEDICO DAO)
    MedicoDao medDao = new MedicoDao();
    List<Medico> medicos = medDao.buscarPorNome(nomeMedico); // <-- CRIE ESTE MÉTODO
    Medico medico = (medicos.isEmpty()) ? null : medicos.get(0); // Pega o primeiro da lista
    
    // Buscar Convenio (Apenas se o tipo for "Convênio")
    Convenio convenio = null;
    if ("Convênio".equalsIgnoreCase(tipo)) {
        ConvenioDao conDao = new ConvenioDao();
        List<Convenio> convenios = conDao.buscarPorNomeEmpresa(nomeConvenio);
        convenio = (convenios.isEmpty()) ? null : convenios.get(0);
    }
    
    // 3. Validar se os objetos foram encontrados
    if (paciente == null || medico == null) {
        JOptionPane.showMessageDialog(this, "Erro: Paciente ou Médico não encontrado no banco.", "Erro de Dados", JOptionPane.ERROR_MESSAGE);
        return; // Para a execução aqui
    }
    if ("Convênio".equalsIgnoreCase(tipo) && convenio == null) {
        JOptionPane.showMessageDialog(this, "Erro: Convênio selecionado não foi encontrado no banco.", "Erro de Dados", JOptionPane.ERROR_MESSAGE);
        return; // Para a execução aqui
    }
    
    // --- FIM DA CORREÇÃO ---

    // 4. Continuar com seu código original (que já estava correto)
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setLenient(false);
    
    java.util.Date dataUtil = sdf.parse(dataStr);
    java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());

    java.time.LocalDate data = dataSql.toLocalDate();
    java.time.LocalTime hora = java.time.LocalTime.parse(horaStr);

    if (consulta == null) {
        consulta = new Consulta(); // nova consulta
    }

    consulta.setPaciente(paciente); // Agora 'paciente' é o objeto completo
    consulta.setMedico(medico);     // Agora 'medico' é o objeto completo
    consulta.setData(data);
    consulta.setHorario(hora);
    consulta.setTipo(tipo);
    consulta.setConvenio("Convênio".equalsIgnoreCase(tipo) ? convenio : null);
    consulta.setObservacoes(observacao);

    ConsultaDao dao = new ConsultaDao();

    if (consulta.getId() == 0) {
        dao.inserir(consulta); // INSERIR NOVA
        JOptionPane.showMessageDialog(this, "Consulta agendada com sucesso!");
    } else {
        dao.atualizar(consulta); // EDITAR EXISTENTE
        JOptionPane.showMessageDialog(this, "Consulta atualizada com sucesso!");
    }

    dispose(); // Fecha a janela após salvar
    
} catch (ParseException ex) {
    JOptionPane.showMessageDialog(this, "Data inválida. Use o formato dd/MM/yyyy", "Erro", JOptionPane.ERROR_MESSAGE);
} catch (Exception ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(this, "Erro ao salvar consulta: " + ex.getMessage());
}        // TODO add your handling code here:
    }//GEN-LAST:event_SalvarActionPerformed

    private void SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SairActionPerformed
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_SairActionPerformed

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
            java.util.logging.Logger.getLogger(AgendarConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgendarConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgendarConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgendarConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgendarConsultas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Limpar;
    private javax.swing.JButton Sair;
    private javax.swing.JButton Salvar;
    private javax.swing.JComboBox<String> comboConvenio;
    private javax.swing.JComboBox<String> comboHora;
    private javax.swing.JComboBox<String> comboMedico;
    private javax.swing.JComboBox<String> comboPaciente;
    private javax.swing.JComboBox<String> comboTipoConsulta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtObservacao;
    // End of variables declaration//GEN-END:variables
}
