/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.imepac.clinica_medica.dao;

import br.edu.imepac.clinica_medica.model.Funcionario;
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
public class FuncionarioDao {

    public boolean inserir(Funcionario funcionario) {
        String sql = "INSERT INTO funcionarios (nome_completo, rg, cpf, rua, numero, complemento, bairro, cidade, estado, cep, telefone_fixo, telefone_celular, ctps, pis) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNomeCompleto());
            stmt.setString(2, funcionario.getRg());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getRua());
            stmt.setString(5, funcionario.getNumero());
            stmt.setString(6, funcionario.getComplemento());
            stmt.setString(7, funcionario.getBairro());
            stmt.setString(8, funcionario.getCidade());
            stmt.setString(9, funcionario.getEstado());
            stmt.setString(10, funcionario.getCep());
            stmt.setString(11, funcionario.getTelefoneFixo());
            stmt.setString(12, funcionario.getTelefoneCelular());
            stmt.setString(13, funcionario.getCtps());
            stmt.setString(14, funcionario.getPis());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterar(Funcionario funcionario) {
        String sql = "UPDATE funcionarios SET nome_completo = ?, rg = ?, cpf = ?, rua = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ?, cep = ?, telefone_fixo = ?, telefone_celular = ?, ctps = ?, pis = ? WHERE id = ?";
        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNomeCompleto());
            stmt.setString(2, funcionario.getRg());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getRua());
            stmt.setString(5, funcionario.getNumero());
            stmt.setString(6, funcionario.getComplemento());
            stmt.setString(7, funcionario.getBairro());
            stmt.setString(8, funcionario.getCidade());
            stmt.setString(9, funcionario.getEstado());
            stmt.setString(10, funcionario.getCep());
            stmt.setString(11, funcionario.getTelefoneFixo());
            stmt.setString(12, funcionario.getTelefoneCelular());
            stmt.setString(13, funcionario.getCtps());
            stmt.setString(14, funcionario.getPis());
            stmt.setInt(15, funcionario.getId());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

   public void deletar(int id) {
    Connection conn = null;
    PreparedStatement stmtUsuario = null;
    PreparedStatement stmtFuncionario = null;

    try {
        conn = ConexaoUtil.obterConexao();
        conn.setAutoCommit(false); // Inicia transação

        // 1. Excluir usuários vinculados ao funcionário
        String sqlUsuario = "DELETE FROM usuarios WHERE id_funcionario = ?";
        stmtUsuario = conn.prepareStatement(sqlUsuario);
        stmtUsuario.setInt(1, id);
        stmtUsuario.executeUpdate();

        // 2. Excluir funcionário
        String sqlFuncionario = "DELETE FROM funcionarios WHERE id = ?";
        stmtFuncionario = conn.prepareStatement(sqlFuncionario);
        stmtFuncionario.setInt(1, id);
        stmtFuncionario.executeUpdate();

        conn.commit(); // Confirma as exclusões
        JOptionPane.showMessageDialog(null, "Funcionário deletado com sucesso!");

    } catch (SQLException e) {
        try {
            if (conn != null) conn.rollback(); // Desfaz se der erro
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erro ao deletar funcionário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

    } finally {
        try {
            if (stmtUsuario != null) stmtUsuario.close();
            if (stmtFuncionario != null) stmtFuncionario.close();
            if (conn != null) conn.setAutoCommit(true); conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

   public Funcionario buscarPorCpf(String cpf) {
    Funcionario funcionario = null;
    String sql = "SELECT * FROM funcionarios WHERE cpf = ?";

    try (Connection conn = ConexaoUtil.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, cpf);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            funcionario = new Funcionario();
            funcionario.setId(rs.getInt("id"));
            funcionario.setNomeCompleto(rs.getString("nome_completo"));
            funcionario.setRg(rs.getString("rg"));
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setRua(rs.getString("rua"));
            funcionario.setNumero(rs.getString("numero"));
            funcionario.setComplemento(rs.getString("complemento"));
            funcionario.setBairro(rs.getString("bairro"));
            funcionario.setCidade(rs.getString("cidade"));
            funcionario.setEstado(rs.getString("estado"));
            funcionario.setCep(rs.getString("cep"));
            funcionario.setTelefoneFixo(rs.getString("telefone_fixo"));
            funcionario.setTelefoneCelular(rs.getString("telefone_celular"));
            funcionario.setCtps(rs.getString("ctps"));
            funcionario.setPis(rs.getString("pis"));
            // adicione mais campos se precisar
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return funcionario;
}
   
  
public Funcionario buscarPorId(int id) {
    Funcionario func = null;
    String sql = "SELECT * FROM funcionarios WHERE id = ?";

    try (Connection con = ConexaoUtil.obterConexao(); // ou sua forma de obter conexão
         PreparedStatement pst = con.prepareStatement(sql)) {
        
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            func = new Funcionario();
            func.setId(rs.getInt("id"));
            func.setNomeCompleto(rs.getString("nome_completo"));
            func.setRg(rs.getString("rg"));
            func.setCpf(rs.getString("cpf"));
            func.setRua(rs.getString("rua"));
            func.setNumero(rs.getString("numero"));
            func.setComplemento(rs.getString("complemento"));
            func.setBairro(rs.getString("bairro"));
            func.setCidade(rs.getString("cidade"));
            func.setEstado(rs.getString("estado"));
            func.setCep(rs.getString("cep"));
            func.setTelefoneFixo(rs.getString("telefone_fixo"));
            func.setTelefoneCelular(rs.getString("telefone_celular"));
            func.setCtps(rs.getString("ctps"));
            func.setPis(rs.getString("pis"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return func;
}




public List<Funcionario> buscarTodos() {
    List<Funcionario> lista = new ArrayList<>();
    String sql = "SELECT * FROM funcionarios";

    try (Connection conn = ConexaoUtil.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setId(rs.getInt("id"));
            funcionario.setNomeCompleto(rs.getString("nome_completo"));
            funcionario.setRg(rs.getString("rg"));
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setRua(rs.getString("rua"));
            funcionario.setNumero(rs.getString("numero"));
            funcionario.setComplemento(rs.getString("complemento"));
            funcionario.setBairro(rs.getString("bairro"));
            funcionario.setCidade(rs.getString("cidade"));
            funcionario.setEstado(rs.getString("estado"));
            funcionario.setCep(rs.getString("cep"));
            funcionario.setTelefoneFixo(rs.getString("telefone_fixo"));
            funcionario.setTelefoneCelular(rs.getString("telefone_celular"));
            funcionario.setCtps(rs.getString("ctps"));
            funcionario.setPis(rs.getString("pis"));
            // adicione os campos restantes
            lista.add(funcionario);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
 }
public int contarFuncionarios() {
    int total = 0;
    String sql = "SELECT COUNT(*) FROM funcionarios";
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
}

