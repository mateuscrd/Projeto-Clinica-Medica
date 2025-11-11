/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.imepac.clinica_medica.dao;

import br.edu.imepac.clinica_medica.model.Especialidade;
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
public class EspecialidadeDao {

    public boolean inserir(Especialidade especialidade) {
        String sql = "INSERT INTO especialidades (descricao) VALUES (?)";
        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, especialidade.getDescricao());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Especialidade> buscarTodos() {
        List<Especialidade> especialidades = new ArrayList<>();
        String sql = "SELECT * FROM especialidades";

        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Especialidade e = new Especialidade();
                e.setId(rs.getInt("id"));
                e.setDescricao(rs.getString("descricao"));
                especialidades.add(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return especialidades;
    }
    public int contarEspecialidades() {
    int total = 0;
    String sql = "SELECT COUNT(*) FROM especialidades";
    try (Connection con = ConexaoUtil.obterConexao();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
            total = rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return total;
}

    public boolean excluir(int id) {
        String sql = "DELETE FROM especialidades WHERE id = ?";
        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(Especialidade especialidade) {
        String sql = "UPDATE especialidades SET descricao = ? WHERE id = ?";
        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, especialidade.getDescricao());
            stmt.setInt(2, especialidade.getId());
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
  public Especialidade buscarPorDescricao(String descricao) {
    Especialidade especialidade = null;
    String sql = "SELECT * FROM especialidades WHERE descricao = ?";
    try (Connection conn = ConexaoUtil.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, descricao);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            especialidade = new Especialidade();
            especialidade.setId(rs.getInt("id"));
            especialidade.setDescricao(rs.getString("descricao"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return especialidade;
}
  public Especialidade buscarPorId(int id) {
    Especialidade especialidade = null;
    String sql = "SELECT * FROM especialidades WHERE id = ?";

    try (Connection conn = ConexaoUtil.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            especialidade = new Especialidade();
            especialidade.setId(rs.getInt("id"));
            especialidade.setDescricao(rs.getString("descricao"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return especialidade;
}

}
