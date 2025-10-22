/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.imepac.clinica_medica.dao.login;

import br.edu.imepac.clinica_medica.dao.ConexaoUtil;
import br.edu.imepac.clinica_medica.model.Login;
import br.edu.imepac.clinica_medica.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */

public class LoginDao { 
    
    public Usuario autenticar(Login login) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? and password = ? ";
        try (Connection conn = ConexaoUtil.obterConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login.getUsuario());
            stmt.setString(2, login.getSenha());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setLogin(rs.getString("usuario"));
                usuario.setSenha(rs.getString("senha"));
                /**CRIAR FIELD PERFIL USUARIO***/
                return usuario;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não é possivel autenticar no momento, tente mais tarde!", "Conexão", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
}
