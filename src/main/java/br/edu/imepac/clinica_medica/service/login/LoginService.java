/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.imepac.clinica_medica.service.login;

import br.edu.imepac.clinica_medica.dao.login.LoginDao;
import br.edu.imepac.clinica_medica.model.Login;
import br.edu.imepac.clinica_medica.model.Usuario;

/**
 *
 * @author Mateus
 */

public class LoginService {
    
    public Usuario autenticar (Login login) {
        LoginDao loginDao = new LoginDao ();
        Usuario logado = loginDao.autenticar(login);
        return logado;
    }
            
}
