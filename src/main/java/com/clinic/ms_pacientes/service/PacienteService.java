package com.clinic.ms_pacientes.service;

import com.clinic.ms_pacientes.model.Paciente;
import com.clinic.ms_pacientes.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> getAllPacientes () {
        return pacienteRepository.getAllPacientes();
    }

    public ResponseEntity<Paciente> createPaciente(Paciente paciente) {
        pacienteRepository.createPaciente(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getApellidos(),
                new java.sql.Date(paciente.getFechaNacimiento().getTime()),
                paciente.getGenero()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
    }

}
