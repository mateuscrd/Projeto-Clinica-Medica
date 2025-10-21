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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateus
 */

public class LoginDao {
    
    public Usuario autenticar (Login login){
        try {
            Connection connection = ConexaoUtil.obterConexao();
        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "SELECT * FROM usuarios WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement (sql)){
      
            stmt.setInt(1, id);
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
    
}
