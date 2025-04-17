package com.clinic.ms_pacientes.controller;

import com.clinic.ms_pacientes.client.IPaciente;
import com.clinic.ms_pacientes.model.Paciente;
import com.clinic.ms_pacientes.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

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
    public ResponseEntity<Paciente> createPaciente(Paciente paciente) {
        Paciente p = pacienteService.createPaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }
}
