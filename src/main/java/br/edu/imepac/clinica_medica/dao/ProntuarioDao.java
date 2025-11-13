/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.imepac.clinica_medica.dao;

import br.edu.imepac.clinica_medica.model.Prontuario;
import br.edu.imepac.clinica_medica.util.ConexaoUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateus
 */
public class ProntuarioDao {

    public void inserir(Prontuario prontuario) throws SQLException {
        // CORRIGIDO: consulta_id -> id_consulta
        String sql = "INSERT INTO prontuarios (id_consulta, queixa_principal, historico_doenca, exame_fisico, diagnostico, conduta, data_registro) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexaoUtil.obterConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, prontuario.getConsultaId());
            stmt.setString(2, prontuario.getQueixaPrincipal());
            stmt.setString(3, prontuario.getHistoricoDoenca());
            stmt.setString(4, prontuario.getExameFisico());
            stmt.setString(5, prontuario.getDiagnostico());
            stmt.setString(6, prontuario.getConduta());
            stmt.setTimestamp(7, prontuario.getDataRegistro());

            stmt.executeUpdate();
        }
    }

    public void alterar(Prontuario prontuario) throws SQLException {
        // CORRIGIDO: consulta_id -> id_consulta
        String sql = "UPDATE prontuarios SET id_consulta = ?, queixa_principal = ?, historico_doenca = ?, exame_fisico = ?, diagnostico = ?, conduta = ?, data_registro = ? WHERE id = ?";
        try (Connection con = ConexaoUtil.obterConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, prontuario.getConsultaId());
            stmt.setString(2, prontuario.getQueixaPrincipal());
            stmt.setString(3, prontuario.getHistoricoDoenca());
            stmt.setString(4, prontuario.getExameFisico());
            stmt.setString(5, prontuario.getDiagnostico());
            stmt.setString(6, prontuario.getConduta());
            stmt.setTimestamp(7, prontuario.getDataRegistro());
            stmt.setInt(8, prontuario.getId());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM prontuarios WHERE id = ?";
        try (Connection con = ConexaoUtil.obterConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Prontuario buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM prontuarios WHERE id = ?";
        try (Connection con = ConexaoUtil.obterConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Prontuario prontuario = new Prontuario();
                    prontuario.setId(rs.getInt("id"));
                    prontuario.setConsultaId(rs.getInt("id_consulta")); // <-- CORRIGIDO
                    prontuario.setQueixaPrincipal(rs.getString("queixa_principal"));
                    prontuario.setHistoricoDoenca(rs.getString("historico_doenca"));
                    prontuario.setExameFisico(rs.getString("exame_fisico"));
                    prontuario.setDiagnostico(rs.getString("diagnostico"));
                    prontuario.setConduta(rs.getString("conduta"));
                    prontuario.setDataRegistro(rs.getTimestamp("data_registro"));
                    return prontuario;
                }
            }
        }
        return null;
    }

    public List<Prontuario> buscarTodos() throws SQLException {
        List<Prontuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM prontuarios";
        try (Connection con = ConexaoUtil.obterConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Prontuario prontuario = new Prontuario();
                prontuario.setId(rs.getInt("id"));
                prontuario.setConsultaId(rs.getInt("id_consulta")); // <-- CORRIGIDO
                prontuario.setQueixaPrincipal(rs.getString("queixa_principal"));
                prontuario.setHistoricoDoenca(rs.getString("historico_doenca"));
                prontuario.setExameFisico(rs.getString("exame_fisico"));
                prontuario.setDiagnostico(rs.getString("diagnostico"));
                prontuario.setConduta(rs.getString("conduta"));
                prontuario.setDataRegistro(rs.getTimestamp("data_registro"));
                lista.add(prontuario);
            }
        }
        return lista;
    }
    public List<Prontuario> buscarPorMedicoId(int medicoId) throws SQLException {
        List<Prontuario> lista = new ArrayList<>();
        // CORRIGIDO: p.consulta_id -> p.id_consulta
        String sql = "SELECT p.* FROM prontuarios p " +
                     "JOIN consultas c ON p.id_consulta = c.id " +
                     "WHERE c.id_medico = ?"; 
        try (Connection con = ConexaoUtil.obterConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, medicoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Prontuario prontuario = new Prontuario();
                    prontuario.setId(rs.getInt("id"));
                    prontuario.setConsultaId(rs.getInt("id_consulta")); // <-- CORRIGIDO
                    prontuario.setQueixaPrincipal(rs.getString("queixa_principal"));
                    prontuario.setHistoricoDoenca(rs.getString("historico_doenca"));
                    prontuario.setExameFisico(rs.getString("exame_fisico"));
                    prontuario.setDiagnostico(rs.getString("diagnostico"));
                    prontuario.setConduta(rs.getString("conduta"));
                    prontuario.setDataRegistro(rs.getTimestamp("data_registro"));
                    lista.add(prontuario);
                }
            }
        }
        return lista;
    }
 public List<Prontuario> buscarPorNomePaciente(String nomePaciente) throws SQLException {
        List<Prontuario> lista = new ArrayList<>();

        // CORRIGIDO: p.consulta_id -> p.id_consulta
        String sql = "SELECT p.*, pa.nome_completo AS nome_paciente " +
                     "FROM prontuarios p " +
                     "JOIN consultas c ON p.id_consulta = c.id " +
                     "JOIN pacientes pa ON c.id_paciente = pa.id " + 
                     "WHERE pa.nome_completo LIKE ?";

        try (Connection con = ConexaoUtil.obterConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, "%" + nomePaciente + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Prontuario prontuario = new Prontuario();
                    prontuario.setId(rs.getInt("id"));
                    prontuario.setConsultaId(rs.getInt("id_consulta")); // <-- CORRIGIDO
                    prontuario.setQueixaPrincipal(rs.getString("queixa_principal"));
                    prontuario.setHistoricoDoenca(rs.getString("historico_doenca"));
                    prontuario.setExameFisico(rs.getString("exame_fisico"));
                    prontuario.setDiagnostico(rs.getString("diagnostico"));
                    prontuario.setConduta(rs.getString("conduta"));
                    prontuario.setDataRegistro(rs.getTimestamp("data_registro"));
                    prontuario.setNomePaciente(rs.getString("nome_paciente")); 
                    lista.add(prontuario);
                }
            }
        }
            return lista;
      }
}