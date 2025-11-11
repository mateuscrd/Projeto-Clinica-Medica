/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.imepac.clinica_medica.dao;

import br.edu.imepac.clinica_medica.model.Consulta;
import br.edu.imepac.clinica_medica.model.Convenio;
import br.edu.imepac.clinica_medica.model.Medico;
import br.edu.imepac.clinica_medica.model.Paciente;
import br.edu.imepac.clinica_medica.util.ConexaoUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateus
 */
public class ConsultaDao {

    public void inserir(Consulta consulta) {
    String sql = "INSERT INTO consultas (paciente_id, medico_id, convenio_id, data_consulta, horario, tipo, observacoes) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = ConexaoUtil.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, consulta.getPaciente().getId());
        stmt.setInt(2, consulta.getMedico().getId());

        if (consulta.getConvenio() != null) {
            System.out.println("Convenio ID: " + consulta.getConvenio().getId());
            stmt.setInt(3, consulta.getConvenio().getId());
        } else {
            System.out.println("Convenio é null");
            stmt.setNull(3, Types.INTEGER);
        }
        stmt.setDate(4, Date.valueOf(consulta.getData()));
        stmt.setTime(5, Time.valueOf(consulta.getHorario()));
        stmt.setString(6, consulta.getTipo());
        stmt.setString(7, consulta.getObservacoes());

        System.out.println("Data: " + consulta.getData());
        System.out.println("Hora: " + consulta.getHorario());
        System.out.println("Tipo: " + consulta.getTipo());
        System.out.println("Obs: " + consulta.getObservacoes());

        int linhas = stmt.executeUpdate();
        System.out.println("Linhas inseridas: " + linhas);

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public void atualizar(Consulta consulta) {
        String sql = "UPDATE consultas SET paciente_id=?, medico_id=?, convenio_id=?, data_consulta=?, horario=?, tipo=?, observacoes=? WHERE id=?";

        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, consulta.getPaciente().getId());
            stmt.setInt(2, consulta.getMedico().getId());

            if (consulta.getConvenio() != null) {
                stmt.setInt(3, consulta.getConvenio().getId());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }

            stmt.setDate(4, Date.valueOf(consulta.getData()));
            stmt.setTime(5, Time.valueOf(consulta.getHorario()));
            stmt.setString(6, consulta.getTipo());
            stmt.setString(7, consulta.getObservacoes());
            stmt.setInt(8, consulta.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Consulta> buscarConsultasProximas() {
    List<Consulta> lista = new ArrayList<>();
    String sql = "SELECT * FROM consultas WHERE data_consulta >= CURRENT_DATE ORDER BY data_consulta, horario LIMIT 10";

    try (Connection conn = ConexaoUtil.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Consulta c =  mapearConsulta(rs); // <-- AQUI
            lista.add(c);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lista;
}

    public void excluir(int id) {
        String sql = "DELETE FROM consultas WHERE id=?";

        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Consulta buscarPorId(int id) {
        String sql = "SELECT * FROM consultas WHERE id=?";
        Consulta consulta = null;

        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    consulta = mapearConsulta(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consulta;
    }

    public List<Consulta> buscarTodos() {
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM consultas";

        try (Connection conn = ConexaoUtil.obterConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Consulta consulta = mapearConsulta(rs);
                lista.add(consulta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Método auxiliar para mapear ResultSet para Consulta
    private Consulta mapearConsulta(ResultSet rs) throws SQLException {
        Consulta consulta = new Consulta();

        consulta.setId(rs.getInt("id"));

        int pacienteId = rs.getInt("paciente_id");
        int medicoId = rs.getInt("medico_id");
        int convenioId = rs.getInt("convenio_id");

        // Buscar os objetos relacionados usando os DAOs
        Paciente paciente = new PacienteDao().buscarPorId(pacienteId);
        Medico medico = new MedicoDao().buscarPorId(medicoId);

        Convenio convenio = null;
        if (!rs.wasNull() && convenioId > 0) {
            convenio = new ConvenioDao().buscarPorId(convenioId);
        }

        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setConvenio(convenio);

        consulta.setData(rs.getDate("data_consulta").toLocalDate());
        consulta.setHorario(rs.getTime("horario").toLocalTime());
        consulta.setTipo(rs.getString("tipo"));
        consulta.setObservacoes(rs.getString("observacoes"));

        return consulta;
    }
   public List<Consulta> buscarPorData(String data) throws Exception {
    List<Consulta> consultas = new ArrayList<>();
    SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");

    java.util.Date dataFormatada = formatoEntrada.parse(data);
    String dataSQL = formatoBanco.format(dataFormatada);

    String sql = "SELECT * FROM consultas WHERE data_consulta = ?";
    try (Connection conn = ConexaoUtil.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, dataSQL);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Consulta consulta = mapearConsulta(rs);  // USANDO O MÉTODO CENTRAL
            consultas.add(consulta);
        }

    } catch (Exception e) {
        throw new Exception("Erro ao buscar consultas por data: " + e.getMessage());
    }

    return consultas;
}
  public List<Consulta> buscarPorMedicoFuturas(int medicoId) {
    List<Consulta> lista = new ArrayList<>();

    String sql = "SELECT c.*, p.nome_completo FROM consultas c " +
                 "JOIN pacientes p ON c.paciente_id = p.id " +
                 "WHERE c.medico_id = ? AND c.data_consulta >= CURDATE()";

    try (Connection conn = ConexaoUtil.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, medicoId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Consulta consulta = new Consulta();

            // ID
            consulta.setId(rs.getInt("id"));

            // Data
            Date dataSql = rs.getDate("data_consulta");
            if (dataSql != null) {
                consulta.setData(dataSql.toLocalDate());
            }

            // Horário
            Time horaSql = rs.getTime("horario");
            if (horaSql != null) {
                consulta.setHorario(horaSql.toLocalTime());
            }

            // Tipo
            consulta.setTipo(rs.getString("tipo"));

            // Paciente (apenas nome completo nesta consulta)
            Paciente paciente = new Paciente();
            paciente.setNomeCompleto(rs.getString("nome_completo"));
            consulta.setPaciente(paciente);

            lista.add(consulta);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}
  public List<Consulta> buscarPorNomePaciente(String nome) {
    List<Consulta> lista = new ArrayList<>();

    String sql = "SELECT c.id, c.data_consulta, c.horario, c.tipo, " +
                 "p.nome_completo AS paciente_nome, " +
                 "m.nome AS medico_nome, " +
                 "cv.nome_empresa AS convenio_nome " +
                 "FROM consultas c " +
                 "JOIN pacientes p ON c.paciente_id = p.id " +
                 "JOIN medicos m ON c.medico_id = m.id " +
                 "LEFT JOIN convenios cv ON c.convenio_id = cv.id " +
                 "WHERE p.nome_completo LIKE ? " +
                 "ORDER BY c.data_consulta DESC, c.horario DESC";

    try (Connection conn = ConexaoUtil.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, "%" + nome + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Consulta consulta = new Consulta();
            consulta.setId(rs.getInt("id"));
            consulta.setData(rs.getDate("data_consulta").toLocalDate());

            Time horario = rs.getTime("horario");
            if (horario != null) {
                consulta.setHorario(horario.toLocalTime());
            }

            consulta.setTipo(rs.getString("tipo"));

            // Paciente
            Paciente paciente = new Paciente();
            paciente.setNomeCompleto(rs.getString("paciente_nome"));
            consulta.setPaciente(paciente);

            // Médico
            Medico medico = new Medico();
            medico.setNome(rs.getString("medico_nome"));
            consulta.setMedico(medico);

            // Convênio (pode ser null)
            String convenioNome = rs.getString("convenio_nome");
            if (convenioNome != null) {
                Convenio convenio = new Convenio();
                convenio.setNomeEmpresa(convenioNome);
                consulta.setConvenio(convenio);
            }

            lista.add(consulta);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}
}
