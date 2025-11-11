/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.imepac.clinica_medica.model;

/**
 *
 * @author Mateus
 */
import java.sql.Timestamp;

public class Prontuario {
    private int id;
    private int consultaId;
    private String queixaPrincipal;
    private String historicoDoenca;
    private String exameFisico;
    private String diagnostico;
    private String conduta;
    private Timestamp dataRegistro;
private String nomePaciente;

    public Prontuario() {}

    public Prontuario(int id, int consultaId, String queixaPrincipal, String historicoDoenca,
                      String exameFisico, String diagnostico, String conduta, Timestamp dataRegistro) {
        this.id = id;
        this.consultaId = consultaId;
        this.queixaPrincipal = queixaPrincipal;
        this.historicoDoenca = historicoDoenca;
        this.exameFisico = exameFisico;
        this.diagnostico = diagnostico;
        this.conduta = conduta;
        this.dataRegistro = dataRegistro;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getConsultaId() { return consultaId; }
    public void setConsultaId(int consultaId) { this.consultaId = consultaId; }

    public String getQueixaPrincipal() { return queixaPrincipal; }
    public void setQueixaPrincipal(String queixaPrincipal) { this.queixaPrincipal = queixaPrincipal; }

    public String getHistoricoDoenca() { return historicoDoenca; }
    public void setHistoricoDoenca(String historicoDoenca) { this.historicoDoenca = historicoDoenca; }

    public String getExameFisico() { return exameFisico; }
    public void setExameFisico(String exameFisico) { this.exameFisico = exameFisico; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getConduta() { return conduta; }
    public void setConduta(String conduta) { this.conduta = conduta; }

    public Timestamp getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(Timestamp dataRegistro) { this.dataRegistro = dataRegistro; }

    public String getNomePaciente() {
    return nomePaciente;
}

public void setNomePaciente(String nomePaciente) {
    this.nomePaciente = nomePaciente;
}
}

