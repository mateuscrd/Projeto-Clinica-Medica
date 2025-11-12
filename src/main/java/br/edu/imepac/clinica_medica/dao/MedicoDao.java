/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.imepac.clinica_medica.dao;

import br.edu.imepac.clinica_medica.model.Especialidade;
import br.edu.imepac.clinica_medica.model.Medico;
import br.edu.imepac.clinica_medica.util.ConexaoUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateus
 */
public class MedicoDao {

    public void inserir(Medico medico) {
        String sql = "INSERT INTO medicos (nome, crm, id_especialidade) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getCrm());
            stmt.setInt(3, medico.getEspecialidade().getId());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir médico: " + ex.getMessage(), ex);
        }
    }


    public void atualizar(Medico medico) {
        String sql = "UPDATE medicos SET nome = ?, crm = ?, id_especialidade = ? WHERE id = ?";

        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getCrm());
            stmt.setInt(3, medico.getEspecialidade().getId());
            stmt.setInt(4, medico.getId());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar médico: " + ex.getMessage(), ex);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM medicos WHERE id = ?";

        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao excluir médico: " + ex.getMessage(), ex);
        }
    }

    public Medico buscarPorId(int id) {
        String sql = "SELECT m.id, m.nome, m.crm, e.id AS esp_id, e.descricao " +
                     "FROM medicos m JOIN especialidades e ON m.id_especialidade = e.id WHERE m.id = ?";

        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Especialidade esp = new Especialidade(rs.getInt("esp_id"), rs.getString("descricao"));
                    return new Medico(rs.getInt("id"), rs.getString("nome"), rs.getString("crm"), esp);
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar médico por ID: " + ex.getMessage(), ex);
        }
        return null;
    }

    public List<Medico> buscarTodos() {
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT m.id, m.nome, m.crm, e.id AS esp_id, e.descricao " +
                     "FROM medicos m JOIN especialidades e ON m.id_especialidade = e.id";

        try (Connection conn = ConexaoUtil.obterConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Especialidade esp = new Especialidade(rs.getInt("esp_id"), rs.getString("descricao"));
                Medico medico = new Medico(rs.getInt("id"), rs.getString("nome"), rs.getString("crm"), esp);
                lista.add(medico);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar médicos: " + ex.getMessage(), ex);
        }

        return lista;
    }
    
    public List<Medico> buscarPorNome(String nome) {
        List<Medico> lista = new ArrayList<>();
        
        // SQL corrigido para ser igual ao buscarTodos, mas com o filtro de nome
        String sql = "SELECT m.id, m.nome, m.crm, e.id AS esp_id, e.descricao " +
                     "FROM medicos m JOIN especialidades e ON m.id_especialidade = e.id " +
                     "WHERE m.nome LIKE ?"; // <-- Cláusula de busca

        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define o parâmetro do PreparedStatement
            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                // Lógica de criação de objeto igual ao seu buscarTodos
                while (rs.next()) {
                    Especialidade esp = new Especialidade(rs.getInt("esp_id"), rs.getString("descricao"));
                    Medico medico = new Medico(rs.getInt("id"), rs.getString("nome"), rs.getString("crm"), esp);
                    lista.add(medico);
                }
            }

        } catch (SQLException ex) {
            // Tratamento de erro corrigido, igual ao resto da classe
            throw new RuntimeException("Erro ao buscar médicos por nome: " + ex.getMessage(), ex);
        }

        return lista;
    }
    public Medico buscarPorUsuarioId(int usuarioId) {
    String sql = "SELECT m.id, m.nome, m.crm, e.id AS esp_id, e.descricao " +
                 "FROM medicos m " +
                 "JOIN especialidades e ON m.id_especialidade = e.id " +
                 "WHERE m.usuario_id = ?";

    try (Connection conn = ConexaoUtil.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, usuarioId);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Especialidade especialidade = new Especialidade(
                    rs.getInt("esp_id"),
                    rs.getString("descricao")
                );

                Medico medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                medico.setCrm(rs.getString("crm"));
                medico.setEspecialidade(especialidade);

                return medico;
            }
        }

    } catch (SQLException ex) {
        throw new RuntimeException("Erro ao buscar médico por usuário_id: " + ex.getMessage(), ex);
    }

    return null;
}
}

