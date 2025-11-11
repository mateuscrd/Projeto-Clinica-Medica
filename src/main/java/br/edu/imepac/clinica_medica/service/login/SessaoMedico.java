/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.imepac.clinica_medica.service.login;

import br.edu.imepac.clinica_medica.model.Medico;

/**
 *
 * @author Mateus
 */
public class SessaoMedico {

    private static Medico medicoLogado;

    public static Medico getMedicoLogado() {
        return medicoLogado;
    }

    public static void setMedicoLogado(Medico medico) {
        medicoLogado = medico;
    }

    public static void limpar() {
        medicoLogado = null;
    }
}
