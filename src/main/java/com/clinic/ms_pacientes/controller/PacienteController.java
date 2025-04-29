package com.clinic.ms_pacientes.controller;

import com.clinic.ms_pacientes.client.IPaciente;
import com.clinic.ms_pacientes.model.Paciente;
import com.clinic.ms_pacientes.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class PacienteController implements IPaciente {

    @Autowired
    private PacienteService pacienteService;

    @Override
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        List<Paciente> pacientes = pacienteService.getAllPacientes();

        return ResponseEntity.status(HttpStatus.OK).body(pacientes);
    }

    @Override
    public ResponseEntity<Paciente> getPacienteById(UUID id) {
        Paciente paciente = pacienteService.getPacienteById(id);
        if (paciente != null) {
            return ResponseEntity.status(HttpStatus.OK).body(paciente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Paciente> createPaciente(Paciente paciente) {
        Paciente p = pacienteService.createPaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }


    @Override
    public ResponseEntity<List<Paciente>> findPacientesByFilter(
            String nombre,
            String apellidos,
            Date fechaNacimiento,
            String genero,
            String estadoPaciente,
            String ciudadNacimiento,
            String nacionalidad,
            String provinciaNacimiento,
            String tipoDocumento,
            String numeroDocumento
    ) {
        List<Paciente> pacientes = pacienteService.findPacientesByFilter(
                nombre, apellidos, fechaNacimiento, genero, estadoPaciente, ciudadNacimiento,
                nacionalidad, provinciaNacimiento, tipoDocumento, numeroDocumento
        );

        return ResponseEntity.status(HttpStatus.OK).body(pacientes);
    }

    @Override
    public ResponseEntity<List<Paciente>> findPacientesByFiltro(
            String trackingId,
            int page,
            int size,
            Map<String, Object> filtros
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.findPacientesByFiltro(
                trackingId,
                page,
                size,
                filtros
        ));
    }

    @Override
    public ResponseEntity<Long> getTotalFiltros(String trackingId, Map<String, Object> filtros) {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.getTotalFiltros(trackingId, filtros));
    }
}