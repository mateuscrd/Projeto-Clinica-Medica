/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.imepac.clinica_medica.model;

/**
 *
 * @author Mateus
 */
public class Especialidade {
    private int id;
    private String descricao;
    
    public Especialidade() {}

    public Especialidade( String descricao) {
        this.descricao = descricao;
    }

    public Especialidade(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;}
    public void setDescricao(String descricao) {
        this.descricao = descricao;}
    
    public String toString() {
    return descricao;
}
}