/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.imepac.clinica_medica.view.medico;

import br.edu.imepac.clinica_medica.dao.ConsultaDao;
import br.edu.imepac.clinica_medica.dao.MedicoDao;
import br.edu.imepac.clinica_medica.model.Consulta;
import br.edu.imepac.clinica_medica.model.Medico;
import br.edu.imepac.clinica_medica.model.Usuario;
import br.edu.imepac.clinica_medica.service.login.SessaoMedico;
import br.edu.imepac.clinica_medica.service.login.SessaoUsuario;
import br.edu.imepac.clinica_medica.view.administrador.EditarPerfil;
import br.edu.imepac.clinica_medica.view.login.TelaLogin;
import br.edu.imepac.clinica_medica.view.medico.AtenderConsulta;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mateus
 */
public class MenuMedico extends javax.swing.JFrame {
 
     private Usuario usuarioLogado;

  public MenuMedico(Usuario usuario) {
    this.usuarioLogado = usuario;
    initComponents();
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    iniciarRelogio();

    if (usuario != null) {
        lblBoasVindas.setText("Bem-vindo, Dr(a). " + usuario.getLogin() + "!");

        // ⚠ Aqui é onde carregamos o médico associado ao usuário
        MedicoDao medicoDao = new MedicoDao();
        Medico medico = medicoDao.buscarPorUsuarioId(usuario.getId());

        if (medico != null) {
            SessaoMedico.setMedicoLogado(medico);
            carregarConsultasDoMedico();// Define o médico logado
        } else {
            JOptionPane.showMessageDialog(this,
                    "Médico não encontrado para este usuário.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

public void carregarConsultasDoMedico() {
    Medico medico = SessaoMedico.getMedicoLogado();
    if (medico == null) {
        JOptionPane.showMessageDialog(this, "Médico não está logado!", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
    }

    ConsultaDao consultaDAO = new ConsultaDao();
    List<Consulta> consultas = consultaDAO.buscarPorMedicoFuturas(medico.getId());

    String[] colunas = {"ID", "Paciente", "Data", "Hora", "Tipo"};

    DefaultTableModel model = new DefaultTableModel(colunas, 0) {
    
        public boolean isCellEditable(int row, int column) {
            return false; // para deixar as células não editáveis
        }
    };

    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");

    for (Consulta c : consultas) {
        Object[] linha = {
            c.getId(),
            c.getPaciente() != null ? c.getPaciente().getNomeCompleto() : "",
            c.getData() != null ? c.getData().format(formatterData) : "",
            c.getHorario() != null ? c.getHorario().format(formatterHora) : "",
            c.getTipo() != null ? c.getTipo() : ""
        };
        model.addRow(linha);
    }

    jTable1.setModel(model);
}
    private void iniciarRelogio() {
        Timer timer = new Timer(1000, (ActionEvent e) -> {
            String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            lblDataHora.setText(dataHora);
        });
        timer.start();
    }

    // Métodos para futuras telas
    private void abrirMinhasConsultas() {
        JOptionPane.showMessageDialog(this, "Abrir lista de consultas do dia...");
    }

    private void abrirAtendimento() {
        JOptionPane.showMessageDialog(this, "Abrir tela de atendimento...");
    }

    private void abrirHistorico() {
        JOptionPane.showMessageDialog(this, "Abrir histórico de consultas...");
    }

    private void abrirNovoProntuario() {
        JOptionPane.showMessageDialog(this, "Abrir preenchimento de prontuário...");
    }

    private void abrirVisualizarProntuarios() {
        JOptionPane.showMessageDialog(this, "Abrir lista de prontuários...");
    }

    private void abrirEditarPerfil() {
        JOptionPane.showMessageDialog(this, "Abrir edição de perfil...");
    }

    private void logout() {
        SessaoUsuario.limpar();
        dispose();
        new TelaLogin().setVisible(true);
    }

    private void mostrarSobre() {
        JOptionPane.showMessageDialog(this, "Sistema Clínica Médica\nVersão 1.0\nDesenvolvido por Kevin");
    }@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblDataHora = new javax.swing.JLabel();
        lblBoasVindas = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

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

        lblDataHora.setText("Hora");

        lblBoasVindas.setText("Mensagem");

        jMenu1.setText("Consultas");

        jMenuItem1.setText("Agendas de Consultas");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Historico de Consultas");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Prontuario");

        jMenuItem6.setText("Meus Prontuarios");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Buscar Paciente");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Perfil");

        jMenuItem3.setText("Editar Perfil");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Sistema");

        jMenuItem4.setText("Logout");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuItem5.setText("Sair");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblBoasVindas)
                .addGap(267, 267, 267)
                .addComponent(lblDataHora)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblDataHora))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblBoasVindas)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
   ListarConsulta ListarConsultasAgendadasTela = new ListarConsulta();
  ListarConsultasAgendadasTela.setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    HistoricoConsulta tela = new HistoricoConsulta();
    tela.setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
     Medico medicoLogado = SessaoMedico.getMedicoLogado();
    if (medicoLogado != null) {
        Prontuario telaProntuarios = new Prontuario(medicoLogado.getId());
        telaProntuarios.setLocationRelativeTo(this);
        telaProntuarios.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Médico logado não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
    } 
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        BuscaPaciente tela = new BuscaPaciente();
        tela.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new EditarPerfil().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        int confirmar = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Logout", JOptionPane.YES_NO_OPTION);
    if (confirmar == JOptionPane.YES_OPTION) {
        SessaoUsuario.limpar(); // Limpa o usuário logado
        this.dispose(); // Fecha a janela atual

        // Retorna para a tela de login
        TelaLogin login = new TelaLogin();
        login.setVisible(true);
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
    System.exit(0); 
    // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Usuario u = new Usuario();
        u.setLogin("Dr. João");
        new MenuMedico(u).setVisible(true);
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblBoasVindas;
    private javax.swing.JLabel lblDataHora;
    // End of variables declaration//GEN-END:variables

}
