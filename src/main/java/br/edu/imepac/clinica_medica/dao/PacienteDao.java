/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.imepac.clinica_medica.dao;

import br.edu.imepac.clinica_medica.model.Paciente;
import br.edu.imepac.clinica_medica.util.ConexaoUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */
public class PacienteDao {

    public void inserir(Paciente paciente) {
        String sql = "INSERT INTO pacientes (nome_completo, rg, cpf, data_nascimento, telefone, email, rua, numero, complemento, bairro, cidade, estado, cep) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNomeCompleto());
            stmt.setString(2, paciente.getRg());
            stmt.setString(3, paciente.getCpf());
            stmt.setString(4, paciente.getDataNascimento());
            stmt.setString(5, paciente.getTelefone());
            stmt.setString(6, paciente.getEmail());
            stmt.setString(7, paciente.getRua());
            stmt.setString(8, paciente.getNumero());
            stmt.setString(9, paciente.getComplemento());
            stmt.setString(10, paciente.getBairro());
            stmt.setString(11, paciente.getCidade());
            stmt.setString(12, paciente.getEstado());
            stmt.setString(13, paciente.getCep());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Paciente> buscarTodos() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM pacientes";

        try (Connection conn = ConexaoUtil.obterConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setId(rs.getInt("id"));
                p.setNomeCompleto(rs.getString("nome_completo"));
                p.setRg(rs.getString("rg"));
                p.setCpf(rs.getString("cpf"));
                p.setDataNascimento(rs.getString("data_nascimento"));
                p.setTelefone(rs.getString("telefone"));
                p.setEmail(rs.getString("email"));
                p.setRua(rs.getString("rua"));
                p.setNumero(rs.getString("numero"));
                p.setComplemento(rs.getString("complemento"));
                p.setBairro(rs.getString("bairro"));
                p.setCidade(rs.getString("cidade"));
                p.setEstado(rs.getString("estado"));
                p.setCep(rs.getString("cep"));
                lista.add(p);
            }

        } catch (SQLException e) {
            // CORREÇÃO: Lançar o erro em vez de engolir
            throw new RuntimeException("Erro ao buscar todos os pacientes: " + e.getMessage(), e);
        }
        return lista;
    }

    public void atualizar(Paciente paciente) {
        String sql = "UPDATE pacientes SET nome_completo=?, rg=?, cpf=?, data_nascimento=?, telefone=?, email=?, rua=?, numero=?, complemento=?, bairro=?, cidade=?, estado=?, cep=? WHERE id=?";

        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNomeCompleto());
            stmt.setString(2, paciente.getRg());
            stmt.setString(3, paciente.getCpf());
            stmt.setString(4, paciente.getDataNascimento());
            stmt.setString(5, paciente.getTelefone());
            stmt.setString(6, paciente.getEmail());
            stmt.setString(7, paciente.getRua());
            stmt.setString(8, paciente.getNumero());
            stmt.setString(9, paciente.getComplemento());
            stmt.setString(10, paciente.getBairro());
            stmt.setString(11, paciente.getCidade());
            stmt.setString(12, paciente.getEstado());
            stmt.setString(13, paciente.getCep());
            stmt.setInt(14, paciente.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM pacientes WHERE id=?";

        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Paciente buscarPorId(int id) {
        String sql = "SELECT * FROM pacientes WHERE id=?";
        Paciente p = null;

        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                p = new Paciente();
                p.setId(rs.getInt("id"));
                p.setNomeCompleto(rs.getString("nome_completo"));
                p.setRg(rs.getString("rg"));
                p.setCpf(rs.getString("cpf"));
                p.setDataNascimento(rs.getString("data_nascimento"));
                p.setTelefone(rs.getString("telefone"));
                p.setEmail(rs.getString("email"));
                p.setRua(rs.getString("rua"));
                p.setNumero(rs.getString("numero"));
                p.setComplemento(rs.getString("complemento"));
                p.setBairro(rs.getString("bairro"));
                p.setCidade(rs.getString("cidade"));
                p.setEstado(rs.getString("estado"));
                p.setCep(rs.getString("cep"));
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }
    
    public List<Paciente> buscarPorNome(String nome) {
    List<Paciente> lista = new ArrayList<>();

    String sql = "SELECT * FROM pacientes WHERE nome_completo LIKE ? ORDER BY nome_completo";

    try (Connection conn = ConexaoUtil.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, "%" + nome + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Paciente paciente = new Paciente();
            paciente.setId(rs.getInt("id"));
            paciente.setNomeCompleto(rs.getString("nome_completo"));
            paciente.setRg(rs.getString("rg"));
            paciente.setCpf(rs.getString("cpf"));
            paciente.setDataNascimento(rs.getString("data_nascimento"));
            paciente.setTelefone(rs.getString("telefone"));
            paciente.setEmail(rs.getString("email"));
            paciente.setRua(rs.getString("rua"));
            paciente.setNumero(rs.getString("numero"));
            paciente.setComplemento(rs.getString("complemento"));
            paciente.setBairro(rs.getString("bairro"));
            paciente.setCidade(rs.getString("cidade"));
            paciente.setEstado(rs.getString("estado"));
            paciente.setCep(rs.getString("cep"));

            lista.add(paciente);
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erro ao buscar pacientes por nome: " + e.getMessage());
    }

    return lista;
}
    
}

