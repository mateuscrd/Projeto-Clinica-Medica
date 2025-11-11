/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.imepac.clinica_medica.dao;

import br.edu.imepac.clinica_medica.model.Usuario;
import br.edu.imepac.clinica_medica.util.ConexaoUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author Mateus
 */
public class UsuarioDao {

    public boolean inserir(Usuario usuario) {
        String sql = "INSERT INTO usuarios (id_funcionario, usuario, senha, permissao) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuario.getFuncionarioId());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getPermissao());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterar(Usuario usuario) {
        String sql = "UPDATE usuarios SET id_funcionario = ?, usuario = ?, senha = ?, permissao = ? WHERE id = ?";
        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuario.getFuncionarioId());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getPermissao());
            stmt.setInt(5, usuario.getId());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
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

    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setFuncionarioId(rs.getInt("id_funcionario"));
                usuario.setLogin(rs.getString("usuario"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setPerfil(rs.getString("permissao"));
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
  public Usuario validarLogin(String login, String senha) {
    String sql = "SELECT * FROM usuarios WHERE usuario = ?";

    try (Connection conn = ConexaoUtil.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, login);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String senhaBanco = rs.getString("senha");

            if (senhaBanco.equals(senha)) { // Comparação direta (sem hash)
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setFuncionarioId(rs.getInt("id_funcionario")); // nome correto no banco
                usuario.setLogin(rs.getString("usuario")); // nome da coluna no banco
                usuario.setSenha(senhaBanco);
                usuario.setPermissao(rs.getString("permissao")); // nome da coluna no banco
                return usuario;
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}
 public List<Usuario> buscarTodos() {
    List<Usuario> lista = new ArrayList<>();
    String sql = "SELECT * FROM usuarios";

    try (Connection conn = ConexaoUtil.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Usuario u = new Usuario();
            u.setId(rs.getInt("id"));
            u.setLogin(rs.getString("usuario"));
            u.setPermissao(rs.getString("permissao")); // ← atualizado aqui
            lista.add(u);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}

  public List<Usuario> buscarPorLogin(String login) {
    List<Usuario> lista = new ArrayList<>();
    String sql = "SELECT * FROM usuarios WHERE usuario LIKE ?";

    try (Connection conn = ConexaoUtil.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, "%" + login + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Usuario u = new Usuario();
            u.setId(rs.getInt("id"));
            u.setLogin(rs.getString("usuario"));
            u.setPermissao(rs.getString("permissao")); // ← atualizado aqui
            lista.add(u);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}
  public int contarUsuarios() {
    int total = 0;
    String sql = "SELECT COUNT(*) FROM usuarios";
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
  public void deletarPorFuncionarioId(int idFuncionario) {
    String sql = "DELETE FROM usuarios WHERE id_funcionario = ?";

    try (Connection conn = ConexaoUtil.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idFuncionario);
        stmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,
            "Erro ao deletar usuários vinculados ao funcionário: " + e.getMessage(),
            "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
}