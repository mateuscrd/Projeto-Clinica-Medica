/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.imepac.clinica_medica.model;

/**
 *
 * @author Mateus
 */
public class Usuario {
    private int id;
    private int funcionarioId;  // referencia ao funcionário cadastrado
    private String login;
    private String senha;
    private String perfil;  // ex: "ATENDENTE", "MEDICO", "ADMIN"
    private String permissao;

    public Usuario() {}

    public Usuario(int id, int funcionarioId, String login, String senha, String perfil) {
        this.id = id;
        this.funcionarioId = funcionarioId;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(int funcionarioId) { this.funcionarioId = funcionarioId; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getPerfil() { return perfil; }
    public void setPerfil(String perfil) { this.perfil = perfil; }
    
    public String getPermissao() {
        return this.permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }
    
}
